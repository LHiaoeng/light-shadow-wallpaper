package com.breadj.lightshadowwallpaper.admin.lol.jobhandler;

import com.breadj.lightshadowwallpaper.admin.lol.skinline.service.SkinlineService;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LolPbeJob {

	@Autowired
	private SkinlineService skinlineService;

	@XxlJob("lolSkinlineJobHandler")
	public void lolSkinlineJobHandler() throws Exception {
		skinlineService.getAndSaveSkinline();
	}

}
