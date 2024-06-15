package com.breadj.lightshadowwallpaper.admin.lol.skinline.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 英雄联盟皮肤系列
 *
 * @author liaoheng 2024-06-15 15:53:47
 */
@Data
@TableName("lol_skinline")
@Schema(title = "英雄联盟皮肤系列")
public class Skinline {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	@Schema(title = "主键")
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
