package com.breadj.lightshadowwallpaper.admin.lol.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liaoheng
 * @version 1.0
 * @date 2024/6/1 15:33
 */
@NoArgsConstructor
@Data
public class HeroInfoVO {

	@JSONField(name = "hero")
	private HeroVO hero;

	@JSONField(name = "skins")
	private List<SkinsVO> skins;

	@JSONField(name = "spells")
	private List<SpellsVO> spells;

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

		@JSONField(name = "shortBio")
		private String shortBio;

		@JSONField(name = "camp")
		private String camp;

		@JSONField(name = "campId")
		private String campId;

		@JSONField(name = "attack")
		private String attack;

		@JSONField(name = "defense")
		private String defense;

		@JSONField(name = "magic")
		private String magic;

		@JSONField(name = "difficulty")
		private String difficulty;

		@JSONField(name = "hp")
		private String hp;

		@JSONField(name = "hpperlevel")
		private String hpperlevel;

		@JSONField(name = "mp")
		private String mp;

		@JSONField(name = "mpperlevel")
		private String mpperlevel;

		@JSONField(name = "movespeed")
		private String movespeed;

		@JSONField(name = "armor")
		private String armor;

		@JSONField(name = "armorperlevel")
		private String armorperlevel;

		@JSONField(name = "spellblock")
		private String spellblock;

		@JSONField(name = "spellblockperlevel")
		private String spellblockperlevel;

		@JSONField(name = "attackrange")
		private String attackrange;

		@JSONField(name = "hpregen")
		private String hpregen;

		@JSONField(name = "hpregenperlevel")
		private String hpregenperlevel;

		@JSONField(name = "mpregen")
		private String mpregen;

		@JSONField(name = "mpregenperlevel")
		private String mpregenperlevel;

		@JSONField(name = "crit")
		private String crit;

		@JSONField(name = "critperlevel")
		private String critperlevel;

		@JSONField(name = "attackdamage")
		private String attackdamage;

		@JSONField(name = "attackdamageperlevel")
		private String attackdamageperlevel;

		@JSONField(name = "attackspeed")
		private String attackspeed;

		@JSONField(name = "attackspeedperlevel")
		private String attackspeedperlevel;

		@JSONField(name = "allytips")
		private List<String> allytips;

		@JSONField(name = "enemytips")
		private List<String> enemytips;

		@JSONField(name = "heroVideoPath")
		private String heroVideoPath;

		@JSONField(name = "isWeekFree")
		private String isWeekFree;

		@JSONField(name = "damageType")
		private String damageType;

		@JSONField(name = "style")
		private String style;

		@JSONField(name = "difficultyL")
		private String difficultyL;

		@JSONField(name = "damage")
		private String damage;

		@JSONField(name = "durability")
		private String durability;

		@JSONField(name = "crowdControl")
		private String crowdControl;

		@JSONField(name = "mobility")
		private String mobility;

		@JSONField(name = "utility")
		private String utility;

		@JSONField(name = "selectAudio")
		private String selectAudio;

		@JSONField(name = "banAudio")
		private String banAudio;

		@JSONField(name = "changeLabel")
		private String changeLabel;

		@JSONField(name = "goldPrice")
		private String goldPrice;

		@JSONField(name = "couponPrice")
		private String couponPrice;

		@JSONField(name = "keywords")
		private String keywords;

		@JSONField(name = "introduce")
		private String introduce;

		@JSONField(name = "palmHeroHeadImg")
		private String palmHeroHeadImg;

		@JSONField(name = "relations")
		private List<?> relations;

	}

	@NoArgsConstructor
	@Data
	public static class SkinsVO {

		@JSONField(name = "skinId")
		private String skinId;

		@JSONField(name = "heroId")
		private String heroId;

		@JSONField(name = "heroName")
		private String heroName;

		@JSONField(name = "heroTitle")
		private String heroTitle;

		@JSONField(name = "name")
		private String name;

		@JSONField(name = "chromas")
		private String chromas;

		@JSONField(name = "chromasBelongId")
		private String chromasBelongId;

		@JSONField(name = "isBase")
		private String isBase;

		@JSONField(name = "emblemsName")
		private String emblemsName;

		@JSONField(name = "description")
		private String description;

		@JSONField(name = "mainImg")
		private String mainImg;

		@JSONField(name = "iconImg")
		private String iconImg;

		@JSONField(name = "loadingImg")
		private String loadingImg;

		@JSONField(name = "videoImg")
		private String videoImg;

		@JSONField(name = "sourceImg")
		private String sourceImg;

		@JSONField(name = "vedioPath")
		private String vedioPath;

		@JSONField(name = "suitType")
		private String suitType;

		@JSONField(name = "publishTime")
		private String publishTime;

		@JSONField(name = "chromaImg")
		private String chromaImg;

		@JSONField(name = "centerImg")
		private String centerImg;

	}

	@NoArgsConstructor
	@Data
	public static class SpellsVO {

		@JSONField(name = "heroId")
		private String heroId;

		@JSONField(name = "spellKey")
		private String spellKey;

		@JSONField(name = "name")
		private String name;

		@JSONField(name = "description")
		private String description;

		@JSONField(name = "abilityIconPath")
		private String abilityIconPath;

		@JSONField(name = "abilityVideoPath")
		private String abilityVideoPath;

		@JSONField(name = "dynamicDescription")
		private String dynamicDescription;

		@JSONField(name = "cost")
		private List<String> cost;

		@JSONField(name = "costburn")
		private String costburn;

		@JSONField(name = "cooldown")
		private List<String> cooldown;

		@JSONField(name = "cooldownburn")
		private String cooldownburn;

		@JSONField(name = "range")
		private List<String> range;

	}

}
