package com.breadj.lightshadowwallpaper.admin.wallpaper.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.breadj.lightshadowwallpaper.admin.wallpaper.converter.WallpaperConverter;
import com.breadj.lightshadowwallpaper.admin.wallpaper.enums.BingWallpaperRegionEnum;
import com.breadj.lightshadowwallpaper.admin.wallpaper.enums.ResolutionEnum;
import com.breadj.lightshadowwallpaper.admin.wallpaper.mapper.WallpaperMapper;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.entity.BingWallpaper;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.entity.Wallpaper;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.qo.BingWallpaperQO;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.vo.WallpaperRestVO;
import com.breadj.lightshadowwallpaper.admin.wallpaper.service.BingWallpaperService;
import com.breadj.lightshadowwallpaper.admin.wallpaper.service.WallpaperService;
import com.breadj.lightshadowwallpaper.admin.wallpaper.utils.BingUtil;
import com.hccake.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class BingWallpaperServiceImpl extends ExtendServiceImpl<WallpaperMapper, Wallpaper> implements BingWallpaperService {

	private final WallpaperService wallpaperService;

	/**
	 * 调用API获取必应壁纸
	 *
	 * @return
	 */
	private static Map<String, List<BingWallpaper.Images>> getBingWallpaperMap() {
		BingWallpaperQO qo = new BingWallpaperQO();
		qo.setUhdwidth(ResolutionEnum.PreviewSize.getWidth());
		qo.setUhdheight(ResolutionEnum.PreviewSize.getHeight());

		List<String> allCountryCodeList = BingWallpaperRegionEnum.getAllCodeList();
		Integer[] idxArr = {0, 8};

		Map<String, List<BingWallpaper.Images>> map = new HashMap<>();

		allCountryCodeList.forEach(countryCode -> {
			qo.setMkt(countryCode);
			List<BingWallpaper.Images> imageList = new ArrayList<>();

			for (Integer idx : idxArr) {
				qo.setIdx(idx);
				BingWallpaper bingWallpaper = BingUtil.fetchBingWallpaperAPI(qo);
				imageList.addAll(bingWallpaper.getImages());
			}

			List<BingWallpaper.Images> values = new ArrayList<>(imageList.stream()
					.collect(Collectors.toMap(
							BingWallpaper.Images::getStartdate,
							image -> image,
							(existing, replacement) -> existing
					))
					.values());

			map.put(countryCode, values);
		});

		return map;
	}

	/**
	 * 获取必应壁纸
	 */
	@Override
	public void getBingWallpaper() {
		Map<String, List<BingWallpaper.Images>> map = getBingWallpaperMap();

		Map<String, String> replacements = new HashMap<>();
		replacements.put("w=" + ResolutionEnum.PreviewSize.getWidth(), "w=" + ResolutionEnum._4K.getWidth());
		replacements.put("h=" + ResolutionEnum.PreviewSize.getHeight(), "h=" + ResolutionEnum._4K.getHeight());

		List<Wallpaper> wallpaperList = createWallpaperList(map, replacements);

		//获取中国壁纸ID集合
		List<String> idCollect = wallpaperList.stream()
				.map(wallpaper -> BingUtil.extractIdentifiers(wallpaper.getUrlBase()))
				.collect(Collectors.toList());

		addNonChineseWallpapers(map, replacements, wallpaperList, idCollect);

		//去掉跟中国重复的壁纸
		List<Wallpaper> uniqueWallpapers = removeDuplicateWallpapers(wallpaperList);

		//根据上架时间倒序排序
		uniqueWallpapers.sort(Comparator.comparing(Wallpaper::getLaunchTime).reversed());

		//保存
		saveIfNotExistBingWallpaper(uniqueWallpapers);
	}

	/**
	 * 先获取中国的壁纸
	 *
	 * @param map
	 * @param replacements
	 * @return
	 */
	private List<Wallpaper> createWallpaperList(Map<String, List<BingWallpaper.Images>> map, Map<String, String> replacements) {
		List<Wallpaper> wallpaperList = new ArrayList<>();

		map.get(BingWallpaperRegionEnum.ZH_CN.getCode()).forEach(image -> {
			wallpaperList.add(createWallpaper(image, replacements, BingWallpaperRegionEnum.ZH_CN.getCode()));
		});

		return wallpaperList;
	}

	/**
	 * 添加非中国的壁纸
	 *
	 * @param map
	 * @param replacements
	 * @param wallpaperList
	 * @param idCollect
	 */
	private void addNonChineseWallpapers(Map<String, List<BingWallpaper.Images>> map, Map<String, String> replacements, List<Wallpaper> wallpaperList, List<String> idCollect) {
		map.forEach((countryCode, images) -> {
			if (Objects.equals(countryCode, BingWallpaperRegionEnum.ZH_CN.getCode())) {
				return;
			}

			images.forEach(image -> {
				if (!idCollect.contains(BingUtil.extractIdentifiers(image.getUrlbase()))) {
					wallpaperList.add(createWallpaper(image, replacements, countryCode));
				}
			});
		});
	}

	/**
	 * Bing壁纸转换实体
	 *
	 * @param image
	 * @param replacements
	 * @param countryCode
	 * @return
	 */
	private Wallpaper createWallpaper(BingWallpaper.Images image, Map<String, String> replacements, String countryCode) {
		Wallpaper wallpaper = new Wallpaper();
		String url = image.getUrl();
		String uhdUrl = BingUtil.replaceUrlParameters(url, replacements);

		wallpaper.setUrl(BingUtil.BING_URL + uhdUrl);
		wallpaper.setPoster(BingUtil.BING_URL + url);
		wallpaper.setUrlBase(image.getUrlbase());
		wallpaper.setTitle(image.getTitle());
		String[] content = BingUtil.extractContent(image.getCopyright());
		wallpaper.setCopyright(content[1]);
		wallpaper.setDescription(content[0]);
		wallpaper.setCopyrightLink(image.getCopyrightlink());
		wallpaper.setTitleLink(BingUtil.BING_URL + image.getQuiz());
		wallpaper.setSource(2);
		wallpaper.setType(1);
		wallpaper.setBingCountry(BingWallpaperRegionEnum.getCountryByCode(countryCode));
		wallpaper.setStatus(0);
		wallpaper.setCreateBy(1L);
		wallpaper.setCreateTime(LocalDateTime.now());
		wallpaper.setUpdateBy(1L);
		wallpaper.setUpdateTime(LocalDateTime.now());

		LocalDate localDate = LocalDate.parse(image.getStartdate(), DateTimeFormatter.BASIC_ISO_DATE);
		wallpaper.setLaunchTime(localDate.atStartOfDay());

		return wallpaper;
	}

	/**
	 * 去掉重复的壁纸
	 *
	 * @param wallpaperList
	 * @return
	 */
	private List<Wallpaper> removeDuplicateWallpapers(List<Wallpaper> wallpaperList) {
		return new ArrayList<>(wallpaperList.stream()
				.collect(Collectors.toMap(
						wallpaper -> BingUtil.extractIdentifiers(wallpaper.getUrlBase()),
						wallpaper -> wallpaper,
						(existing, replacement) -> existing
				))
				.values());
	}

	/**
	 * 保存必应壁纸
	 *
	 * @param wallpapers
	 */
	public void saveIfNotExistBingWallpaper(List<Wallpaper> wallpapers) {
		Set<String> urlBases = wallpapers.stream().map(Wallpaper::getUrlBase).collect(Collectors.toSet());

		LambdaQueryWrapper<Wallpaper> queryWrapper = Wrappers.lambdaQuery(Wallpaper.class);
		queryWrapper.select(Wallpaper::getUrlBase).in(Wallpaper::getUrlBase, urlBases);
		//查询launchTime近十五天的数据
//		queryWrapper.and(wrapper -> wrapper.lt(Wallpaper::getLaunchTime, LocalDateTime.now().minusDays(15)));
		queryWrapper.eq(Wallpaper::getSource, 2);
		List<Wallpaper> existingWallpapers = baseMapper.selectList(queryWrapper);
		Set<String> existingUrlBases = existingWallpapers.stream().map(Wallpaper::getUrlBase).collect(Collectors.toSet());

		List<Wallpaper> newWallpapers = wallpapers.stream()
				.filter(wallpaper -> !existingUrlBases.contains(wallpaper.getUrlBase()))
				.collect(Collectors.toList());

		if (!newWallpapers.isEmpty()) {
			wallpaperService.saveBatch(newWallpapers);
		}
	}

	/**
	 * 通过API获取今日Bing壁纸
	 *
	 * @return
	 */
	@Override
	public List<WallpaperRestVO> getBingWallpaperByQO(BingWallpaperQO qo) {
		if (null == qo) {
			qo = new BingWallpaperQO();
			qo.setIdx(0);
			qo.setN(1);
			qo.setUhd(1);
		}

		if (null == qo.getUhdwidth()) {
			qo.setUhdwidth(ResolutionEnum._4K.getWidth());
		}
		if (null == qo.getUhdheight()) {
			qo.setUhdheight(ResolutionEnum._4K.getHeight());
		}

		BingWallpaper bingWallpaper = BingUtil.fetchBingWallpaperAPI(qo);

		if (null == bingWallpaper || CollectionUtil.isEmpty(bingWallpaper.getImages())) {
			return Collections.emptyList();
		}

		List<Wallpaper> wallpaperList = new ArrayList<>();
		BingWallpaperQO finalQo = qo;

		bingWallpaper.getImages().forEach(image -> {
			Wallpaper wallpaper = createWallpaper(image, null, finalQo.getMkt());
			wallpaperList.add(wallpaper);
		});

		return wallpaperList.stream().map(WallpaperConverter.INSTANCE::poToRestVo).collect(Collectors.toList());
	}
}
