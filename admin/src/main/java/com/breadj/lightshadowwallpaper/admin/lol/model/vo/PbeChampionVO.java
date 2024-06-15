package com.breadj.lightshadowwallpaper.admin.lol.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liaoheng
 * @version 1.0
 * @date 2024/6/15 11:50
 */
@NoArgsConstructor
@Data
public class PbeChampionVO {

	@JSONField(name = "id")
	private Long id;

	@JSONField(name = "name")
	private String name;

	@JSONField(name = "alias")
	private String alias;

	@JSONField(name = "title")
	private String title;

	@JSONField(name = "shortBio")
	private String shortBio;

	@JSONField(name = "tacticalInfo")
	private TacticalInfoVO tacticalInfo;

	@JSONField(name = "playstyleInfo")
	private PlaystyleInfoVO playstyleInfo;

	@JSONField(name = "squarePortraitPath")
	private String squarePortraitPath;

	@JSONField(name = "stingerSfxPath")
	private String stingerSfxPath;

	@JSONField(name = "chooseVoPath")
	private String chooseVoPath;

	@JSONField(name = "banVoPath")
	private String banVoPath;

	@JSONField(name = "roles")
	private List<String> roles;

	@JSONField(name = "recommendedItemDefaults")
	private List<?> recommendedItemDefaults;

	@JSONField(name = "skins")
	private List<SkinsVO> skins;

	@JSONField(name = "passive")
	private PassiveVO passive;

	@JSONField(name = "spells")
	private List<SpellsVO> spells;

	@NoArgsConstructor
	@Data
	public static class TacticalInfoVO {

		@JSONField(name = "style")
		private Integer style;

		@JSONField(name = "difficulty")
		private Integer difficulty;

		@JSONField(name = "damageType")
		private String damageType;

	}

	@NoArgsConstructor
	@Data
	public static class PlaystyleInfoVO {

		@JSONField(name = "damage")
		private Integer damage;

		@JSONField(name = "durability")
		private Integer durability;

		@JSONField(name = "crowdControl")
		private Integer crowdControl;

		@JSONField(name = "mobility")
		private Integer mobility;

		@JSONField(name = "utility")
		private Integer utility;

	}

	@NoArgsConstructor
	@Data
	public static class PassiveVO {

		@JSONField(name = "name")
		private String name;

		@JSONField(name = "abilityIconPath")
		private String abilityIconPath;

		@JSONField(name = "abilityVideoPath")
		private String abilityVideoPath;

		@JSONField(name = "abilityVideoImagePath")
		private String abilityVideoImagePath;

		@JSONField(name = "description")
		private String description;

	}

	@NoArgsConstructor
	@Data
	public static class SkinsVO {

		@JSONField(name = "id")
		private Integer id;

		@JSONField(name = "isBase")
		private Boolean isBase;

		@JSONField(name = "name")
		private String name;

		@JSONField(name = "splashPath")
		private String splashPath;

		@JSONField(name = "uncenteredSplashPath")
		private String uncenteredSplashPath;

		@JSONField(name = "tilePath")
		private String tilePath;

		@JSONField(name = "loadScreenPath")
		private String loadScreenPath;

		@JSONField(name = "skinType")
		private String skinType;

		@JSONField(name = "rarity")
		private String rarity;

		@JSONField(name = "isLegacy")
		private Boolean isLegacy;

		@JSONField(name = "splashVideoPath")
		private Object splashVideoPath;

		@JSONField(name = "collectionSplashVideoPath")
		private Object collectionSplashVideoPath;

		@JSONField(name = "collectionCardHoverVideoPath")
		private Object collectionCardHoverVideoPath;

		@JSONField(name = "featuresText")
		private Object featuresText;

		@JSONField(name = "chromaPath")
		private Object chromaPath;

		@JSONField(name = "emblems")
		private Object emblems;

		@JSONField(name = "regionRarityId")
		private Integer regionRarityId;

		@JSONField(name = "rarityGemPath")
		private Object rarityGemPath;

		@JSONField(name = "skinLines")
		private Object skinLines;

		@JSONField(name = "description")
		private Object description;

		@JSONField(name = "loadScreenVintagePath")
		private String loadScreenVintagePath;

		@JSONField(name = "chromas")
		private List<ChromasVO> chromas;

		@NoArgsConstructor
		@Data
		public static class ChromasVO {

			@JSONField(name = "id")
			private Integer id;

			@JSONField(name = "name")
			private String name;

			@JSONField(name = "chromaPath")
			private String chromaPath;

			@JSONField(name = "colors")
			private List<String> colors;

			@JSONField(name = "descriptions")
			private List<DescriptionsVO> descriptions;

			@JSONField(name = "rarities")
			private List<RaritiesVO> rarities;

			@NoArgsConstructor
			@Data
			public static class DescriptionsVO {

				@JSONField(name = "region")
				private String region;

				@JSONField(name = "description")
				private String description;

			}

			@NoArgsConstructor
			@Data
			public static class RaritiesVO {

				@JSONField(name = "region")
				private String region;

				@JSONField(name = "rarity")
				private Integer rarity;

			}

		}

	}

	@NoArgsConstructor
	@Data
	public static class SpellsVO {

		@JSONField(name = "spellKey")
		private String spellKey;

		@JSONField(name = "name")
		private String name;

		@JSONField(name = "abilityIconPath")
		private String abilityIconPath;

		@JSONField(name = "abilityVideoPath")
		private String abilityVideoPath;

		@JSONField(name = "abilityVideoImagePath")
		private String abilityVideoImagePath;

		@JSONField(name = "cost")
		private String cost;

		@JSONField(name = "cooldown")
		private String cooldown;

		@JSONField(name = "description")
		private String description;

		@JSONField(name = "dynamicDescription")
		private String dynamicDescription;

		@JSONField(name = "range")
		private List<Integer> range;

		@JSONField(name = "costCoefficients")
		private List<Integer> costCoefficients;

		@JSONField(name = "cooldownCoefficients")
		private List<Integer> cooldownCoefficients;

		@JSONField(name = "coefficients")
		private CoefficientsVO coefficients;

		@JSONField(name = "effectAmounts")
		private EffectAmountsVO effectAmounts;

		@JSONField(name = "ammo")
		private AmmoVO ammo;

		@JSONField(name = "maxLevel")
		private Integer maxLevel;

		@NoArgsConstructor
		@Data
		public static class CoefficientsVO {

			@JSONField(name = "coefficient1")
			private Double coefficient1;

			@JSONField(name = "coefficient2")
			private Double coefficient2;

		}

		@NoArgsConstructor
		@Data
		public static class EffectAmountsVO {

			@JSONField(name = "Effect1Amount")
			private List<Integer> effect1Amount;

			@JSONField(name = "Effect2Amount")
			private List<Integer> effect2Amount;

			@JSONField(name = "Effect3Amount")
			private List<Integer> effect3Amount;

			@JSONField(name = "Effect4Amount")
			private List<Integer> effect4Amount;

			@JSONField(name = "Effect5Amount")
			private List<Integer> effect5Amount;

			@JSONField(name = "Effect6Amount")
			private List<Integer> effect6Amount;

			@JSONField(name = "Effect7Amount")
			private List<Integer> effect7Amount;

			@JSONField(name = "Effect8Amount")
			private List<Integer> effect8Amount;

			@JSONField(name = "Effect9Amount")
			private List<Integer> effect9Amount;

			@JSONField(name = "Effect10Amount")
			private List<Integer> effect10Amount;

		}

		@NoArgsConstructor
		@Data
		public static class AmmoVO {

			@JSONField(name = "ammoRechargeTime")
			private List<Integer> ammoRechargeTime;

			@JSONField(name = "maxAmmo")
			private List<Integer> maxAmmo;

		}

	}

}
