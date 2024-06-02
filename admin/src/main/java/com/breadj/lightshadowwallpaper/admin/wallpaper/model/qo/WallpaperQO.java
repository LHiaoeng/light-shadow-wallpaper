package com.breadj.lightshadowwallpaper.admin.wallpaper.model.qo;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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
	@Schema(description = "上架时间起")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate launchTimeStart;

	/**
	 * 上架时间止
	 */
	@Schema(description = "上架时间止")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate launchTimeEnd;

	/**
	 * 必应壁纸所属国家
	 */
	@Schema(title = "必应壁纸所属国家")
	private String bingCountry;

	/**
	 * 是否有壁纸网址
	 */
	@Schema(title = "是否有壁纸网址（0：否；1：是）")
	private Integer hasMainUrl;

}