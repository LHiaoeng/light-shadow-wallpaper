package com.breadj.lightshadowwallpaper.admin.lol.skinline.model.qo;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import org.springdoc.api.annotations.ParameterObject;

/**
 * 英雄联盟皮肤系列 查询对象
 *
 * @author liaoheng 2024-06-15 15:53:47
 */
@Data
@Schema(title = "英雄联盟皮肤系列查询对象")
@ParameterObject
public class SkinlineQO {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@Parameter(description = "")
	private Long id;

}