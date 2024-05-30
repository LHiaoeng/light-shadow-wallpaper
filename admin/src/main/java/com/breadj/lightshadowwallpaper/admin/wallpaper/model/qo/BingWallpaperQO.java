package com.breadj.lightshadowwallpaper.admin.wallpaper.model.qo;

import com.breadj.lightshadowwallpaper.admin.wallpaper.enums.BingWallpaperRegionEnum;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springdoc.api.annotations.ParameterObject;

@Data
@Schema(title = "必应壁纸查询对象")
@ParameterObject
public class BingWallpaperQO {
	/**
	 * 返回数据格式，不存在返回xml格式 （非必需）
	 * js (一般使用这个，返回json格式)
	 * xml（返回xml格式）
	 */
	@Parameter(description = "返回数据格式(js:json格式,xml:xml格式)", required = true)
	private String format = "js";

	/**
	 * 请求图片截止天数 (非必需)
	 * 0 今天
	 * -1 截止中明天 （预准备的）
	 * 1 截止至昨天，类推（目前最多获取到7天前的图片）
	 */
	@Parameter(description = "请求图片截止天数(0:今天,1:截止至昨天，类推（目前最多获取到7天前的图片）,-1:明天)")
	private Integer idx;

	/**
	 * 返回请求数量，目前最多一次获取8张(必需)
	 * 1-8
	 */
	@Parameter(description = "返回请求数量(1-8)", required = true)
	private Integer n = 8;

	/**
	 * 超高清
	 */
	@Parameter(description = "超高清")
	private Integer uhd = 1;

	/**
	 * 超高清图片宽度
	 */
	@Parameter(description = "超高清图片宽度")
	private Integer uhdwidth = 320;

	/**
	 * 超高清图片高度
	 */
	@Parameter(description = "超高清图片高度")
	private Integer uhdheight = 180;

	/**
	 * 地区（非必需）
	 * @see BingWallpaperRegionEnum
	 */
	@Parameter(description = "地区(非必需)")
	private String mkt;
}
