package com.breadj.lightshadowwallpaper.admin.wallpaper.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum BingWallpaperRegionEnum {
	ZH_CN("zh-CN", "中国"),
	EN_US("en-US", "美国"),
	JA_JP("ja-JP", "日本"),
	EN_IN("en-IN", "印度"),
	PT_BR("pt-BR", "巴西"),
	FR_FR("fr-FR", "法国"),
	DE_DE("de-DE", "德国"),
	EN_CA("en-CA", "加拿大"),
	EN_GB("en-GB", "英国"),
	IT_IT("it-IT", "意大利"),
	ES_ES("es-ES", "西班牙"),
	FR_CA("fr-CA", "加拿大");

	private final String code;
	private final String country;

	BingWallpaperRegionEnum(String code, String country) {
		this.code = code;
		this.country = country;
	}

	public static List<String> getAllCodeList() {
		return Arrays.stream(BingWallpaperRegionEnum.values())
				.map(BingWallpaperRegionEnum::getCode)
				.collect(Collectors.toList());
	}

	public static String getCountryByCode(String code) {
		return Arrays.stream(BingWallpaperRegionEnum.values())
				.filter(lang -> lang.getCode().equals(code))
				.map(BingWallpaperRegionEnum::getCountry)
				.findFirst()
				.orElse(null);
	}
}
