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
import com.breadj.lightshadowwallpaper.admin.wallpaper.util.BingUtil;
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
public class BingWallpaperServiceImpl extends ExtendServiceImpl<WallpaperMapper, Wallpaper>
		implements BingWallpaperService {

	private final WallpaperService wallpaperService;

	/**
	 * 调用API获取必应壁纸
	 * @return
	 */
	private static Map<String, List<BingWallpaper.Images>> getBingWallpaperMap() {
		BingWallpaperQO qo = new BingWallpaperQO();
		qo.setUhdwidth(ResolutionEnum.PreviewSize.getWidth());
		qo.setUhdheight(ResolutionEnum.PreviewSize.getHeight());

		List<String> allCountryCodeList = BingWallpaperRegionEnum.getAllCodeList();
		Integer[] idxArr = { 0, 8 };

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
				.collect(Collectors.toMap(BingWallpaper.Images::getUrlbase, image -> image,
						(existing, replacement) -> existing))
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

		// 国内壁纸去重
		wallpaperList = removeDuplicateWallpapers(wallpaperList);

		// 获取中国壁纸ID集合
		List<String> idCollect = wallpaperList.stream()
			.map(wallpaper -> BingUtil.extractIdentifiers(wallpaper.getUrlBase()))
			.collect(Collectors.toList());

		log.info("中国壁纸ID集合：{}", idCollect);

		// 获取本次跟国内不同的外国壁纸
		List<Wallpaper> foreignWallpapers = addNonChineseWallpapers(map, replacements, idCollect);

		// 去掉国外重复的壁纸
		List<Wallpaper> uniqueForeignWallpapers = removeDuplicateWallpapers(foreignWallpapers);

		// 保存
		saveIfNotExistBingWallpaper(wallpaperList, uniqueForeignWallpapers);
	}

	/**
	 * 先获取中国的壁纸
	 * @param map
	 * @param replacements
	 * @return
	 */
	private List<Wallpaper> createWallpaperList(Map<String, List<BingWallpaper.Images>> map,
			Map<String, String> replacements) {
		List<Wallpaper> wallpaperList = new ArrayList<>();

		map.get(BingWallpaperRegionEnum.ZH_CN.getCode())
			.forEach(image -> wallpaperList
				.add(createWallpaper(image, replacements, BingWallpaperRegionEnum.ZH_CN.getCode())));

		return wallpaperList;
	}

	/**
	 * 添加非中国的壁纸
	 * @param map
	 * @param replacements
	 * @param idCollect
	 */
	private List<Wallpaper> addNonChineseWallpapers(Map<String, List<BingWallpaper.Images>> map,
			Map<String, String> replacements, List<String> idCollect) {

		List<Wallpaper> list = new ArrayList<>();

		map.forEach((countryCode, images) -> {
			if (Objects.equals(countryCode, BingWallpaperRegionEnum.ZH_CN.getCode())) {
				return;
			}

			images.forEach(image -> {
				if (!idCollect.contains(BingUtil.extractIdentifiers(image.getUrlbase()))) {
					list.add(createWallpaper(image, replacements, countryCode));
				}
			});
		});

		return list;
	}

	/**
	 * Bing壁纸转换实体
	 * @param image
	 * @param replacements
	 * @param countryCode
	 * @return
	 */
	private Wallpaper createWallpaper(BingWallpaper.Images image, Map<String, String> replacements,
			String countryCode) {
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

		LocalDate localDate = LocalDate.parse(image.getEnddate(), DateTimeFormatter.BASIC_ISO_DATE);
		wallpaper.setLaunchTime(localDate.atStartOfDay());

		return wallpaper;
	}

	/**
	 * 去掉重复的壁纸
	 * @param wallpaperList
	 * @return
	 */
	private List<Wallpaper> removeDuplicateWallpapers(List<Wallpaper> wallpaperList) {
		return new ArrayList<>(wallpaperList.stream()
			.collect(Collectors.toMap(wallpaper -> BingUtil.extractIdentifiers(wallpaper.getUrlBase()),
					wallpaper -> wallpaper, (existing, replacement) -> existing))
			.values());
	}

	private List<Wallpaper> getNewWallpapers(List<Wallpaper> wallpapers) {
		// 获取壁纸的id
		Set<String> urlBases = wallpapers.stream()
			.map(wallpaper -> BingUtil.extractIdentifiers(wallpaper.getUrlBase()))
			.collect(Collectors.toSet());

		LambdaQueryWrapper<Wallpaper> queryWrapper = Wrappers.lambdaQuery(Wallpaper.class);
		queryWrapper.select(Wallpaper::getUrlBase);
		queryWrapper.eq(Wallpaper::getSource, 2);
		queryWrapper.eq(Wallpaper::getBingCountry, "中国");
		queryWrapper.and(wrapper -> {
			for (String urlBase : urlBases) {
				wrapper.or().like(Wallpaper::getUrlBase, urlBase);
			}
		});

		List<Wallpaper> existingWallpapers = baseMapper.selectList(queryWrapper);
		Set<String> existingUrlBases = existingWallpapers.stream()
			.map(wallpaper -> BingUtil.extractIdentifiers(wallpaper.getUrlBase()))
			.collect(Collectors.toSet());

		return wallpapers.stream()
			.filter(wallpaper -> !existingUrlBases.contains(BingUtil.extractIdentifiers(wallpaper.getUrlBase())))
			.collect(Collectors.toList());
	}

	private List<Wallpaper> getNewForeignWallpapers(List<Wallpaper> wallpapers) {
		// 获取壁纸的id
		Set<String> urlBases = wallpapers.stream()
			.map(wallpaper -> BingUtil.extractIdentifiers(wallpaper.getUrlBase()))
			.collect(Collectors.toSet());

		LambdaQueryWrapper<Wallpaper> queryWrapper = Wrappers.lambdaQuery(Wallpaper.class);
		queryWrapper.select(Wallpaper::getUrlBase);
		queryWrapper.eq(Wallpaper::getSource, 2);
		queryWrapper.and(wrapper -> {
			for (String urlBase : urlBases) {
				wrapper.or().like(Wallpaper::getUrlBase, urlBase);
			}
		});

		List<Wallpaper> existingWallpapers = baseMapper.selectList(queryWrapper);
		Set<String> existingUrlBases = existingWallpapers.stream()
			.map(wallpaper -> BingUtil.extractIdentifiers(wallpaper.getUrlBase()))
			.collect(Collectors.toSet());

		return wallpapers.stream()
			.filter(wallpaper -> !existingUrlBases.contains(BingUtil.extractIdentifiers(wallpaper.getUrlBase())))
			.collect(Collectors.toList());
	}

	/**
	 * 保存必应壁纸
	 * @param wallpapers 国内壁纸
	 * @param foreignWallpapers 国外壁纸
	 */
	public void saveIfNotExistBingWallpaper(List<Wallpaper> wallpapers, List<Wallpaper> foreignWallpapers) {
		wallpapers = getNewWallpapers(wallpapers);
		foreignWallpapers = getNewForeignWallpapers(foreignWallpapers);

		wallpapers.addAll(foreignWallpapers);

		if (!wallpapers.isEmpty()) {
			// 根据上架时间升序排序
			wallpapers.sort(Comparator.comparing(Wallpaper::getLaunchTime));
			wallpaperService.saveBatch(wallpapers);
		}
	}

	/**
	 * 通过API获取今日Bing壁纸
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
