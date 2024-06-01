package com.breadj.lightshadowwallpaper.admin.lol.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liaoheng
 * @version 1.0
 * @date 2024/6/1 15:06
 */
@NoArgsConstructor
@Data
public class HeroListVO {

	@JSONField(name = "hero")
	private List<HeroVO> heroList;

	@JSONField(name = "version")
	private String version;

	@JSONField(name = "fileName")
	private String fileName;

	@JSONField(name = "fileTime")
	private String fileTime;

	@NoArgsConstructor
	@Data
	public static class HeroVO {

		@JSONField(name = "heroId")
		private String heroId;

		@JSONField(name = "name")
		private String name;

		@JSONField(name = "alias")
		private String alias;

		@JSONField(name = "title")
		private String title;

		@JSONField(name = "roles")
		private List<String> roles;

		@JSONField(name = "isWeekFree")
		private String isWeekFree;

		@JSONField(name = "attack")
		private String attack;

		@JSONField(name = "defense")
		private String defense;

		@JSONField(name = "magic")
		private String magic;

		@JSONField(name = "difficulty")
		private String difficulty;

		@JSONField(name = "selectAudio")
		private String selectAudio;

		@JSONField(name = "banAudio")
		private String banAudio;

		@JSONField(name = "isARAMweekfree")
		private String isARAMweekfree;

		@JSONField(name = "ispermanentweekfree")
		private String ispermanentweekfree;

		@JSONField(name = "changeLabel")
		private String changeLabel;

		@JSONField(name = "goldPrice")
		private String goldPrice;

		@JSONField(name = "couponPrice")
		private String couponPrice;

		@JSONField(name = "camp")
		private String camp;

		@JSONField(name = "campId")
		private String campId;

		@JSONField(name = "keywords")
		private String keywords;

		@JSONField(name = "instance_id")
		private String instanceId;

	}

}
