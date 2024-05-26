package com.breadj.lightshadowwallpaper.admin.wallpaper.model.qo;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import org.springdoc.api.annotations.ParameterObject;

import java.time.LocalDateTime;

/**
 * 壁纸 查询对象
 *
 * @author liaoheng 2024-05-25 14:56:24
 */
@Data
@Schema(title = "壁纸查询对象")
@ParameterObject
public class WallpaperQO {

	private static final long serialVersionUID = 1L;

	/**
	 * 壁纸标题
	 */
	@Parameter(description = "壁纸标题")
	private String title;

	/**
	 * 壁纸类型
	 */
	@Parameter(description = "壁纸类型")
	private Integer type;

	/**
	 * 壁纸来源(0-默认未知,1-英雄联盟,2-必应)
	 */
	@Schema(description = "壁纸来源(0-默认未知,1-英雄联盟,2-必应)")
	private Integer source;

	/**
	 * 壁纸状态(0-启用,1-停用)
	 */
	@Schema(description = "壁纸状态(0-启用,1-停用)")
	private Integer status;

	/**
	 * 上架时间起
	 */
	private LocalDateTime launchTimeStart;

	/**
	 * 上架时间止
	 */
	private LocalDateTime launchTimeEnd;
}