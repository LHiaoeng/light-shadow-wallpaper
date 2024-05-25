package com.breadj.lightshadowwallpaper.admin.wallpaper.model.qo;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import org.springdoc.api.annotations.ParameterObject;

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
}