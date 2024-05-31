package com.breadj.lightshadowwallpaper.admin.wallpaper.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.entity.BingWallpaper;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.qo.BingWallpaperQO;
import com.hccake.ballcat.common.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liaoheng
 * @version 1.0
 * @date 2024/5/31 17:09
 */
@Slf4j
public class BingUtil {
	private static final String BING_API = "https://global.bing.com/HPImageArchive.aspx";
	public static final String BING_URL = "https://bing.com";

	/**
	 * 获取必应壁纸API
	 *
	 * @param qo
	 * @return
	 */
	public static BingWallpaper fetchBingWallpaperAPI(BingWallpaperQO qo) {
		if (null == qo) {
			throw new RuntimeException("必应壁纸查询参数不能为空");
		}

		String res = HttpUtil.get(BING_API, BeanUtil.beanToMap(qo));

		BingWallpaper bingWallpaper;
		try {
			bingWallpaper = JsonUtils.toObj(res, BingWallpaper.class);
		} catch (Exception e) {
			log.error("调用必应壁纸API响应参数：{}", res);
			log.error("必应壁纸API响应参数转实体异常：", e);
			throw new RuntimeException("调用必应壁纸API异常。原因：" + res);
		}

		return bingWallpaper;
	}

	/**
	 * 提取输入字符串中最后一个括号内外的内容
	 *
	 * @param input
	 * @return
	 */
	public static String[] extractContent(String input) {
		String outsideContent = input;
		String insideContent = "";

		// 使用正则表达式找到所有括号中的内容
		Pattern pattern = Pattern.compile("\\((.*?)\\)");
		Matcher matcher = pattern.matcher(input);

		int lastMatchStart = -1;
		int lastMatchEnd = -1;

		// 遍历所有匹配项，找到最后一个匹配项
		while (matcher.find()) {
			lastMatchStart = matcher.start();
			lastMatchEnd = matcher.end();
			insideContent = matcher.group(1);
		}

		// 如果找到了最后一个匹配项，移除括号和其中的内容
		if (lastMatchStart != -1 && lastMatchEnd != -1) {
			outsideContent = input.substring(0, lastMatchStart).trim() + " " + input.substring(lastMatchEnd).trim();
			outsideContent = outsideContent.trim();
		}

		return new String[]{outsideContent, insideContent};
	}

	/**
	 *	替换URL中的参数
	 *
	 * @param url
	 * @param replacements
	 * @return
	 */
	public static String replaceUrlParameters(String url, Map<String, String> replacements) {
		if (replacements == null || replacements.isEmpty()) {
			return url;
		}
		for (Map.Entry<String, String> entry : replacements.entrySet()) {
			url = url.replace(entry.getKey(), entry.getValue());
		}
		return url;
	}

	/**
	 * 获取Bing壁纸ID
	 *
	 * @param url
	 * @return
	 */
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
