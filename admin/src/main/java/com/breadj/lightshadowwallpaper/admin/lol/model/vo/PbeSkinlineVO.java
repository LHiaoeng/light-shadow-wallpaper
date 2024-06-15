package com.breadj.lightshadowwallpaper.admin.lol.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liaoheng
 * @version 1.0
 * @date 2024/6/15 12:11
 */
@NoArgsConstructor
@Data
public class PbeSkinlineVO {

	@JSONField(name = "id")
	private Long id;

	@JSONField(name = "name")
	private String name;

	@JSONField(name = "description")
	private String description;

}
