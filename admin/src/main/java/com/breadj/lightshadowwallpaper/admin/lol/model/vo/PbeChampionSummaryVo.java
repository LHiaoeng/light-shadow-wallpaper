package com.breadj.lightshadowwallpaper.admin.lol.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liaoheng
 * @version 1.0
 * @date 2024/6/15 11:47
 */
@NoArgsConstructor
@Data
public class PbeChampionSummaryVo {

	@JSONField(name = "id")
	private Long id;

	@JSONField(name = "name")
	private String name;

	@JSONField(name = "alias")
	private String alias;

	@JSONField(name = "squarePortraitPath")
	private String squarePortraitPath;

	@JSONField(name = "roles")
	private List<String> roles;

}
