package com.breadj.lightshadowwallpaper.admin.lol.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liaoheng
 * @version 1.0
 * @date 2024/6/15 11:53
 */
@NoArgsConstructor
@Data
public class PbeSkinVO {

	@JSONField(name = "id")
	private Long id;

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
	private String chromaPath;

	@JSONField(name = "chromas")
	private List<ChromasVO> chromas;

	@JSONField(name = "questSkinInfo")
	private QuestSkinInfoVO questSkinInfo;

	@JSONField(name = "emblems")
	private List<EmblemsVO> emblems;

	@JSONField(name = "regionRarityId")
	private Integer regionRarityId;

	@JSONField(name = "rarityGemPath")
	private String rarityGemPath;

	@JSONField(name = "skinLines")
	private List<SkinLinesVO> skinLines;

	@JSONField(name = "skinAugments")
	private SkinAugmentsVO skinAugments;

	@JSONField(name = "description")
	private String description;

	@NoArgsConstructor
	@Data
	public static class QuestSkinInfoVO {

		@JSONField(name = "name")
		private String name;

		@JSONField(name = "productType")
		private String productType;

		@JSONField(name = "collectionDescription")
		private String collectionDescription;

		@JSONField(name = "descriptionInfo")
		private List<?> descriptionInfo;

		@JSONField(name = "splashPath")
		private String splashPath;

		@JSONField(name = "uncenteredSplashPath")
		private String uncenteredSplashPath;

		@JSONField(name = "tilePath")
		private String tilePath;

		@JSONField(name = "collectionCardPath")
		private String collectionCardPath;

		@JSONField(name = "tiers")
		private List<TiersVO> tiers;

		@NoArgsConstructor
		@Data
		public static class TiersVO {

			@JSONField(name = "id")
			private Integer id;

			@JSONField(name = "name")
			private String name;

			@JSONField(name = "stage")
			private Integer stage;

			@JSONField(name = "description")
			private String description;

			@JSONField(name = "splashPath")
			private String splashPath;

			@JSONField(name = "uncenteredSplashPath")
			private String uncenteredSplashPath;

			@JSONField(name = "tilePath")
			private String tilePath;

			@JSONField(name = "loadScreenPath")
			private String loadScreenPath;

			@JSONField(name = "shortName")
			private String shortName;

			@JSONField(name = "splashVideoPath")
			private Object splashVideoPath;

			@JSONField(name = "collectionSplashVideoPath")
			private Object collectionSplashVideoPath;

			@JSONField(name = "collectionCardHoverVideoPath")
			private Object collectionCardHoverVideoPath;

			@JSONField(name = "skinAugments")
			private SkinAugmentsVO skinAugments;

			@NoArgsConstructor
			@Data
			public static class SkinAugmentsVO {

				@JSONField(name = "borders")
				private BordersVO borders;

				@NoArgsConstructor
				@Data
				public static class BordersVO {

					@JSONField(name = "layer0")
					private List<Layer0VO> layer0;

					@NoArgsConstructor
					@Data
					public static class Layer0VO {

						@JSONField(name = "contentId")
						private String contentId;

						@JSONField(name = "layer")
						private Integer layer;

						@JSONField(name = "priority")
						private Integer priority;

						@JSONField(name = "borderPath")
						private String borderPath;

					}

				}

			}

		}

	}

	@NoArgsConstructor
	@Data
	public static class SkinAugmentsVO {

		@JSONField(name = "borders")
		private BordersVO borders;

		@NoArgsConstructor
		@Data
		public static class BordersVO {

			@JSONField(name = "layer0")
			private List<Layer0VO> layer0;

			@NoArgsConstructor
			@Data
			public static class Layer0VO {

				@JSONField(name = "contentId")
				private String contentId;

				@JSONField(name = "layer")
				private Integer layer;

				@JSONField(name = "priority")
				private Integer priority;

				@JSONField(name = "borderPath")
				private String borderPath;

			}

		}

	}

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

	@NoArgsConstructor
	@Data
	public static class EmblemsVO {

		@JSONField(name = "name")
		private String name;

		@JSONField(name = "emblemPath")
		private EmblemPathVO emblemPath;

		@JSONField(name = "positions")
		private PositionsVO positions;

		@NoArgsConstructor
		@Data
		public static class EmblemPathVO {

			@JSONField(name = "large")
			private String large;

			@JSONField(name = "small")
			private String small;

		}

		@NoArgsConstructor
		@Data
		public static class PositionsVO {

		}

	}

	@NoArgsConstructor
	@Data
	public static class SkinLinesVO {

		@JSONField(name = "id")
		private Integer id;

	}

}
