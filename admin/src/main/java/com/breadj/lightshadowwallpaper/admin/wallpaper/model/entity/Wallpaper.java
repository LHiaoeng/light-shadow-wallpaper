package com.breadj.lightshadowwallpaper.admin.wallpaper.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.hccake.ballcat.system.model.entity.SysUser;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 壁纸
 *
 * @author liaoheng 2024-05-25 14:56:24
 */
@Data
@TableName("wallpaper")
@Schema(title = "壁纸")
public class Wallpaper {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	@Schema(title = "主键id")
	private Long id;

	/**
	 * 壁纸类型。1：图片；2：视频
	 */
	@Schema(title = "壁纸类型。1：图片；2：视频")
	private Integer type;

	/**
	 * 壁纸标题
	 */
	@Schema(title = "壁纸标题")
	private String title;

	/**
	 * 标题链接
	 */
	@Schema(title = "标题链接")
	@TableField(updateStrategy = FieldStrategy.IGNORED)
	private String titleLink;

	/**
	 * 壁纸网址
	 */
	@Schema(title = "壁纸网址")
	private String url;

	/**
	 * 壁纸默认地址
	 */
	@Schema(title = "壁纸默认地址")
	@TableField(updateStrategy = FieldStrategy.IGNORED)
	private String urlBase;

	/**
	 * 预览海报
	 */
	@Schema(title = "预览海报")
	@TableField(updateStrategy = FieldStrategy.IGNORED)
	private String poster;

	/**
	 * 版权信息
	 */
	@Schema(title = "版权信息")
	@TableField(updateStrategy = FieldStrategy.IGNORED)
	private String copyright;

	/**
	 * 版权信息链接
	 */
	@Schema(title = "版权信息链接")
	@TableField(updateStrategy = FieldStrategy.IGNORED)
	private String copyrightLink;

	/**
	 * 壁纸描述
	 */
	@Schema(title = "壁纸描述")
	@TableField(updateStrategy = FieldStrategy.IGNORED)
	private String description;

	/**
	 * 壁纸来源(0-默认未知,1-英雄联盟,2-必应)
	 */
	@Schema(title = "壁纸来源(0-默认未知,1-英雄联盟,2-必应)")
	private Integer source;

	/**
	 * 必应壁纸所属国家
	 */
	@Schema(title = "必应壁纸所属国家")
	@TableField(updateStrategy = FieldStrategy.IGNORED)
	private String bingCountry;

	/**
	 * 上架时间
	 */
	@Schema(title = "上架时间")
	@TableField(updateStrategy = FieldStrategy.IGNORED)
	private LocalDateTime launchTime;

	/**
	 * 壁纸状态(0-启用,1-停用)
	 */
	@Schema(title = "壁纸状态(0-启用,1-停用)")
	private Integer status;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	@Schema(title = "创建时间")
	private LocalDateTime createTime;

	/**
	 * 创建人
	 */
	@TableField(fill = FieldFill.INSERT)
	@Schema(title = "创建人")
	private Long createBy;

	/**
	 * 修改人
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	@Schema(title = "修改人")
	private Long updateBy;

	/**
	 * 修改时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	@Schema(title = "修改时间")
	private LocalDateTime updateTime;

}
