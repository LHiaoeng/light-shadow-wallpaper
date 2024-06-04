package com.breadj.lightshadowwallpaper.admin.openapi;

import com.breadj.lightshadowwallpaper.admin.wallpaper.model.qo.BingWallpaperQO;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.qo.WallpaperQO;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.vo.WallpaperRestVO;
import com.breadj.lightshadowwallpaper.admin.wallpaper.service.BingWallpaperService;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/wallpaper")
@Tag(name = "web客户端壁纸API")
public class WallpaperRestController {

	private final WallpaperService wallpaperService;

	private final BingWallpaperService bingWallpaperService;

	/**
	 * web客户端分页查询数据库壁纸
	 * @param pageParam
	 * @param wallpaperQO
	 * @return
	 */
	@Operation(summary = "web客户端分页查询数据库壁纸")
	@GetMapping("/page")
	public R<PageResult<WallpaperRestVO>> getWallpaperPage(PageParam pageParam, WallpaperQO wallpaperQO) {
		return R.ok(wallpaperService.queryRestPage(pageParam, wallpaperQO));
	}

	/**
	 * 调用必应壁纸接口获取必应壁纸
	 * @param qo
	 * @return
	 */
	@Operation(summary = "web客户端调用必应壁纸接口获取必应壁纸")
	@GetMapping("/getBingWallpapers")
	public R<List<WallpaperRestVO>> getBingWallpapers(BingWallpaperQO qo) {
		return R.ok(bingWallpaperService.getBingWallpaperByQO(qo));
	}

}
