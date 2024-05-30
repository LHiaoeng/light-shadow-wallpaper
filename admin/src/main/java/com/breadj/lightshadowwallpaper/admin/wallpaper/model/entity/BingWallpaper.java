package com.breadj.lightshadowwallpaper.admin.wallpaper.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class BingWallpaper {

	private List<Images> images;
	private Tooltips tooltips;

	@NoArgsConstructor
	@Data
	public static class Tooltips {
		private String loading;
		private String previous;
		private String next;
		private String walle;
		private String walls;
	}

	@NoArgsConstructor
	@Data
	public static class Images {
		private String startdate;
		private String fullstartdate;
		private String enddate;
		private String url;
		private String urlbase;
		private String copyright;
		private String copyrightlink;
		private String title;
		private String quiz;
		private Boolean wp;
		private String hsh;
		private Integer drk;
		private Integer top;
		private Integer bot;
		private List<?> hs;
	}
}
