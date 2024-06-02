package com.breadj.lightshadowwallpaper.admin.lol.jobhandler;

import com.breadj.lightshadowwallpaper.admin.lol.service.LolWallpaperService;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LolWallpaperJob {

	@Autowired
	private LolWallpaperService lolWallpaperService;

	@XxlJob("lolWallpaperJobHandler")
	public void lolWallpaperJobHandler() throws Exception {
		lolWallpaperService.getAndSaveLolSkinWallpaper();
	}

}
