package com.breadj.lightshadowwallpaper.admin.wallpaper.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.breadj.lightshadowwallpaper.admin.wallpaper.enums.BingWallpaperRegionEnum;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.entity.BingWallpaper;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.entity.Wallpaper;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.qo.BingWallpaperQO;
import com.breadj.lightshadowwallpaper.admin.wallpaper.service.BingWallpaperService;
import com.breadj.lightshadowwallpaper.admin.wallpaper.service.WallpaperService;
import com.hccake.ballcat.common.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class BingWallpaperServiceImpl implements BingWallpaperService {

	private final WallpaperService wallpaperService;

	private static final String BING_API = "https://global.bing.com/HPImageArchive.aspx";
	private static final String BING_URL = "https://bing.com";


	/**
	 * 获取必应壁纸
	 */
	@Override
	public void getBingWallpaper() {
		Map<String, List<BingWallpaper.Images>> map = getBingWallpaperMap();

		Map<String, String> replacements = new HashMap<>();
		replacements.put("w=320", "w=3840");
		replacements.put("h=180", "h=2160");

		List<Wallpaper> wallpaperList = new ArrayList<>();

		List<BingWallpaper.Images> chinaList = map.get(BingWallpaperRegionEnum.ZH_CN.getCode());

		chinaList.forEach(image -> {
			Wallpaper wallpaper = new Wallpaper();
			String url = image.getUrl();
			String uhdUrl = replaceUrlParameters(url, replacements);

			wallpaper.setUrl(BING_URL + uhdUrl);
			wallpaper.setPoster(BING_URL + url);
			wallpaper.setUrlBase(image.getUrlbase());
			wallpaper.setTitle(image.getTitle());
			String copyright = image.getCopyright();
			String[] content = extractContent(copyright);
			wallpaper.setCopyright(content[1]);
			wallpaper.setDescription(content[0]);
			wallpaper.setCopyrightLink(image.getCopyrightlink());
			wallpaper.setTitleLink(BING_URL + image.getQuiz());
			wallpaper.setSource(2);
			wallpaper.setType(1);
			wallpaper.setBingCountry(BingWallpaperRegionEnum.getCountryByCode(BingWallpaperRegionEnum.ZH_CN.getCode()));
			wallpaper.setStatus(0);
			wallpaper.setCreateBy(1L);
			wallpaper.setCreateTime(LocalDateTime.now());
			wallpaper.setUpdateBy(1L);
			wallpaper.setUpdateTime(LocalDateTime.now());

			LocalDate localDate = LocalDate.parse(image.getStartdate(), DateTimeFormatter.BASIC_ISO_DATE);
			wallpaper.setLaunchTime(localDate.atStartOfDay());
			wallpaperList.add(wallpaper);
		});

		List<String> idCollect = wallpaperList.stream().map(wallpaper -> extractIdentifiers(wallpaper.getUrlBase())).collect(Collectors.toList());

		map.forEach((countryCode, images) -> images.forEach(image -> {
			if (Objects.equals(countryCode, BingWallpaperRegionEnum.ZH_CN.getCode())) {
				return;
			}

			if (idCollect.contains(extractIdentifiers(image.getUrlbase()))) {
				return;
			}

			Wallpaper wallpaper = new Wallpaper();
			String url = image.getUrl();
			String uhdUrl = replaceUrlParameters(url, replacements);

			wallpaper.setUrl(BING_URL + uhdUrl);
			wallpaper.setPoster(BING_URL + url);
			wallpaper.setUrlBase(image.getUrlbase());
			wallpaper.setTitle(image.getTitle());
			String copyright = image.getCopyright();
			String[] content = extractContent(copyright);
			wallpaper.setCopyright(content[1]);
			wallpaper.setDescription(content[0]);
			wallpaper.setCopyrightLink(image.getCopyrightlink());
			wallpaper.setTitleLink(BING_URL + image.getQuiz());
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
			wallpaperList.add(wallpaper);
		}));

		List<Wallpaper> values = new ArrayList<>(wallpaperList.stream()
				.collect(Collectors.toMap(
						wallpaper -> extractIdentifiers(wallpaper.getUrlBase()),
						wallpaper -> wallpaper,
						(existing, replacement) -> existing
				))
				.values());

		//按launchTime从大到小排序
		values.sort(Comparator.comparing(Wallpaper::getLaunchTime).reversed());

		log.info("wallpaperList:{}", JSONArray.toJSONString(values));

		wallpaperService.saveBatch(values);
	}

	private static Map<String, List<BingWallpaper.Images>> getBingWallpaperMap() {
		BingWallpaperQO qo = new BingWallpaperQO();

		List<String> allCountryCodeList = BingWallpaperRegionEnum.getAllCodeList();

		Integer[] idxArr = new Integer[]{0, 8};

		Map<String, List<BingWallpaper.Images>> map = new HashMap<>();

		allCountryCodeList.forEach(countryCode -> {
			qo.setMkt(countryCode);
			log.info("countryCode:{}", countryCode);

			List<BingWallpaper.Images> imageList = new ArrayList<>();

			for (Integer integer : idxArr) {
				qo.setIdx(integer);
				String res = HttpUtil.get(BING_API, BeanUtil.beanToMap(qo));

				BingWallpaper bingWallpaper = JsonUtils.toObj(res, BingWallpaper.class);
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

	public static String[] extractContent(String input) {
		String outsideContent = input.replaceAll("\\s*\\(.*?\\)", "").trim();
		String insideContent = "";

		Pattern pattern = Pattern.compile("\\((.*?)\\)");
		Matcher matcher = pattern.matcher(input);

		if (matcher.find()) {
			insideContent = matcher.group(1);
		}

		return new String[] { outsideContent, insideContent };
	}

	public static String replaceUrlParameters(String url, Map<String, String> replacements) {
		for (Map.Entry<String, String> entry : replacements.entrySet()) {
			url = url.replace(entry.getKey(), entry.getValue());
		}
		return url;
	}

	public static String extractIdentifiers(String url) {
		String regex = "/th\\?id=(OHR\\.[^_]+)";
		Pattern pattern = Pattern.compile(regex);
		String id = "";

		Matcher matcher = pattern.matcher(url);
		if (matcher.find()) {
			id = matcher.group(1);
		}

		return id;
	}

}
