package com.breadj.lightshadowwallpaper.admin.wallpaper.service.jobhandler;

import com.breadj.lightshadowwallpaper.admin.wallpaper.service.BingWallpaperService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class BingWallpaperJob {

	@Autowired
	private BingWallpaperService bingWallpaperService;

	@XxlJob("bingWallpaperJobHandler")
	public void bingWallpaperJobHandler() throws Exception {
		bingWallpaperService.getBingWallpaper();
	}
}
