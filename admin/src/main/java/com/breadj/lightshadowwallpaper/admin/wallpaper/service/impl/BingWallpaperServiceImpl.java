package com.breadj.lightshadowwallpaper.admin.wallpaper.service.impl;

import cn.hutool.http.HttpUtil;
import com.breadj.lightshadowwallpaper.admin.wallpaper.service.BingWallpaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BingWallpaperServiceImpl implements BingWallpaperService {

	private static final String BING_URL = "https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=zh-CN";

	/**
	 * 获取必应壁纸
	 */
	@Override
	public void getBingWallpaper() {
		String res = HttpUtil.get(BING_URL);
		log.info("res: {}", res);
	}
}
