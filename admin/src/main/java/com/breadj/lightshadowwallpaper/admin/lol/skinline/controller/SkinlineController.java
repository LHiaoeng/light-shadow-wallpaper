package com.breadj.lightshadowwallpaper.admin.lol.skinline.controller;

import com.breadj.lightshadowwallpaper.admin.lol.skinline.model.entity.Skinline;
import com.breadj.lightshadowwallpaper.admin.lol.skinline.model.qo.SkinlineQO;
import com.breadj.lightshadowwallpaper.admin.lol.skinline.model.vo.SkinlinePageVO;
import com.breadj.lightshadowwallpaper.admin.lol.skinline.service.SkinlineService;
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
 * 英雄联盟皮肤系列
 *
 * @author liaoheng 2024-06-15 15:53:47
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/skinline/-skinline")
@Tag(name = "英雄联盟皮肤系列管理")
public class SkinlineController {

	private final SkinlineService skinlineService;

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param skinlineQO 英雄联盟皮肤系列查询对象
	 * @return R 通用返回体
	 */
	@Operation(summary = "分页查询")
	@GetMapping("/page")
	@PreAuthorize("@per.hasPermission('skinline:-skinline:read')")
	public R<PageResult<SkinlinePageVO>> getSkinlinePage(PageParam pageParam, SkinlineQO skinlineQO) {
		return R.ok(skinlineService.queryPage(pageParam, skinlineQO));
	}

	/**
	 * 新增英雄联盟皮肤系列
	 * @param skinline 英雄联盟皮肤系列
	 * @return R 通用返回体
	 */
	@Operation(summary = "新增英雄联盟皮肤系列")
	@CreateOperationLogging(msg = "新增英雄联盟皮肤系列")
	@PostMapping
	@PreAuthorize("@per.hasPermission('skinline:-skinline:add')")
	public R<Void> save(@RequestBody Skinline skinline) {
		return skinlineService.save(skinline) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增英雄联盟皮肤系列失败");
	}

	/**
	 * 修改英雄联盟皮肤系列
	 * @param skinline 英雄联盟皮肤系列
	 * @return R 通用返回体
	 */
	@Operation(summary = "修改英雄联盟皮肤系列")
	@UpdateOperationLogging(msg = "修改英雄联盟皮肤系列")
	@PutMapping
	@PreAuthorize("@per.hasPermission('skinline:-skinline:edit')")
	public R<Void> updateById(@RequestBody Skinline skinline) {
		return skinlineService.updateById(skinline) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改英雄联盟皮肤系列失败");
	}

	/**
	 * 通过id删除英雄联盟皮肤系列
	 * @param id id
	 * @return R 通用返回体
	 */
	@Operation(summary = "通过id删除英雄联盟皮肤系列")
	@DeleteOperationLogging(msg = "通过id删除英雄联盟皮肤系列")
	@DeleteMapping("/{id}")
	@PreAuthorize("@per.hasPermission('skinline:-skinline:del')")
	public R<Void> removeById(@PathVariable("id") Long id) {
		return skinlineService.removeById(id) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除英雄联盟皮肤系列失败");
	}

}