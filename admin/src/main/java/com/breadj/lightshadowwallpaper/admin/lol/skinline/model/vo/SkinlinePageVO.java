package com.breadj.lightshadowwallpaper.admin.lol.skinline.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 英雄联盟皮肤系列分页视图对象
 *
 * @author liaoheng 2024-06-15 15:53:47
 */
@Data
@Schema(title = "英雄联盟皮肤系列分页视图对象")
public class SkinlinePageVO {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@Schema(title = "")
	private Long id;

	/**
	 * 拳头皮肤系列编号
	 */
	@Schema(title = "拳头皮肤系列编号")
	private Long riotSkinlineId;

	/**
	 * 皮肤系列名称
	 */
	@Schema(title = "皮肤系列名称")
	private String name;

	/**
	 * 皮肤系列描述
	 */
	@Schema(title = "皮肤系列描述")
	private String description;

	/**
	 * 皮肤系列英文名称
	 */
	@Schema(title = "皮肤系列英文名称")
	private String engName;

	/**
	 * 皮肤系列英文描述
	 */
	@Schema(title = "皮肤系列英文描述")
	private String engDescription;

}