package com.breadj.lightshadowwallpaper.admin.wallpaper.controller;

import com.breadj.lightshadowwallpaper.admin.wallpaper.model.qo.WallpaperQO;
import com.breadj.lightshadowwallpaper.admin.wallpaper.service.WallpaperService;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.entity.Wallpaper;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.vo.WallpaperPageVO;
import com.hccake.ballcat.common.log.operation.annotation.CreateOperationLogging;
import com.hccake.ballcat.common.log.operation.annotation.DeleteOperationLogging;
import com.hccake.ballcat.common.log.operation.annotation.UpdateOperationLogging;
import com.hccake.ballcat.common.model.domain.PageParam;
import com.hccake.ballcat.common.model.domain.PageResult;
import com.hccake.ballcat.common.model.result.BaseResultCode;
import com.hccake.ballcat.common.model.result.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 壁纸
 *
 * @author liaoheng 2024-05-25 14:56:24
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/wallpaper/wallpaper")
@Tag(name = "壁纸管理")
public class WallpaperController {

	private final WallpaperService wallpaperService;

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param wallpaperQO 壁纸查询对象
	 * @return R 通用返回体
	 */
	@Operation(summary = "分页查询")
	@GetMapping("/page")
	@PreAuthorize("@per.hasPermission('wallpaper:wallpaper:read')")
	public R<PageResult<WallpaperPageVO>> getWallpaperPage(PageParam pageParam, WallpaperQO wallpaperQO) {
		return R.ok(wallpaperService.queryPage(pageParam, wallpaperQO));
	}

	/**
	 * 新增壁纸
	 * @param wallpaper 壁纸
	 * @return R 通用返回体
	 */
	@Operation(summary = "新增壁纸")
	@CreateOperationLogging(msg = "新增壁纸")
	@PostMapping
	@PreAuthorize("@per.hasPermission('wallpaper:wallpaper:add')")
	public R<Void> save(@RequestBody Wallpaper wallpaper) {
		return wallpaperService.save(wallpaper) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增壁纸失败");
	}

	/**
	 * 修改壁纸
	 * @param wallpaper 壁纸
	 * @return R 通用返回体
	 */
	@Operation(summary = "修改壁纸")
	@UpdateOperationLogging(msg = "修改壁纸")
	@PutMapping
	@PreAuthorize("@per.hasPermission('wallpaper:wallpaper:edit')")
	public R<Void> updateById(@RequestBody Wallpaper wallpaper) {
		return wallpaperService.updateById(wallpaper) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改壁纸失败");
	}

	/**
	 * 通过id删除壁纸
	 * @param id id
	 * @return R 通用返回体
	 */
	@Operation(summary = "通过id删除壁纸")
	@DeleteOperationLogging(msg = "通过id删除壁纸")
	@DeleteMapping("/{id}")
	@PreAuthorize("@per.hasPermission('wallpaper:wallpaper:del')")
	public R<Void> removeById(@PathVariable("id") Integer id) {
		return wallpaperService.removeById(id) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除壁纸失败");
	}

	@Operation(summary = "通过id复制壁纸")
	@CreateOperationLogging(msg = "复制壁纸")
	@PostMapping("/copy/{id}")
	@PreAuthorize("@per.hasPermission('wallpaper:wallpaper:copy')")
	public R<Void> copyWallpaperById(@PathVariable("id") Long id) {
		return wallpaperService.copyWallpaperById(id) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id复制壁纸失败");
	}

}