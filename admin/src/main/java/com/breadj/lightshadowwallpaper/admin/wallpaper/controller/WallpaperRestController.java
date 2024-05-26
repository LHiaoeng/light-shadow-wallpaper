package com.breadj.lightshadowwallpaper.admin.wallpaper.controller;

import com.breadj.lightshadowwallpaper.admin.wallpaper.model.qo.WallpaperQO;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.vo.WallpaperPageVO;
import com.breadj.lightshadowwallpaper.admin.wallpaper.service.WallpaperService;
import com.hccake.ballcat.common.model.domain.PageParam;
import com.hccake.ballcat.common.model.domain.PageResult;
import com.hccake.ballcat.common.model.result.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wallpaper")
@Tag(name = "web客户端壁纸API")
public class WallpaperRestController {

	private final WallpaperService wallpaperService;

	@Operation(summary = "web客户端分页查询")
	@GetMapping("/page")
	public R<PageResult<WallpaperPageVO>> getWallpaperPage(PageParam pageParam, WallpaperQO wallpaperQO) {
		return R.ok(wallpaperService.queryRestPage(pageParam, wallpaperQO));
	}
}
