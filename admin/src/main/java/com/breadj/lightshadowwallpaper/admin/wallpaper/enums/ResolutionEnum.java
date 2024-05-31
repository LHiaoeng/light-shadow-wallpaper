package com.breadj.lightshadowwallpaper.admin.wallpaper.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @author liaoheng
 * @version 1.0
 * @date 2024/5/31 17:59
 */
@Getter
public enum ResolutionEnum {

	PreviewSize("previewSize", 320, 180), SD("SD", 720, 480), HD("HD", 1280, 720), FULL_HD("FullHD", 1920, 1080),
	_4K("4K", 3840, 2160), _8K("8K", 7680, 4320);

	private final String label;

	private final int width;

	private final int height;

	ResolutionEnum(String label, int width, int height) {
		this.label = label;
		this.width = width;
		this.height = height;
	}

}
