package com.breadj.lightshadowwallpaper.admin.lol.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liaoheng
 * @version 1.0
 * @date 2024/6/15 17:08
 */
@NoArgsConstructor
@Data
public class PbeUniverseVO {

	@JSONField(name = "id")
	private Long id;

	@JSONField(name = "name")
	private String name;

	@JSONField(name = "description")
	private String description;

	@JSONField(name = "imagePath")
	private String imagePath;

	@JSONField(name = "skinSets")
	private List<Long> skinSets;

}
