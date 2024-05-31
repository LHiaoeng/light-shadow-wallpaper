package com.breadj.lightshadowwallpaper.admin.wallpaper.service;

import com.breadj.lightshadowwallpaper.admin.wallpaper.model.entity.Wallpaper;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.qo.BingWallpaperQO;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.vo.WallpaperRestVO;

import java.util.List;

public interface BingWallpaperService {
	/**
	 * 获取必应壁纸
	 */
	void getBingWallpaper();

	/**
	 * 通过API获取今日Bing壁纸
	 *
	 * @return
	 */
	List<WallpaperRestVO> getBingWallpaperByQO(BingWallpaperQO qo);
}
