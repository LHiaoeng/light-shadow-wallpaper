package com.breadj.lightshadowwallpaper.admin.wallpaper.converter;

import com.breadj.lightshadowwallpaper.admin.wallpaper.model.entity.Wallpaper;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.vo.WallpaperPageVO;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.vo.WallpaperRestVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 壁纸模型转换器
 *
 * @author liaoheng 2024-05-25 14:56:24
 */
@Mapper
public interface WallpaperConverter {

	WallpaperConverter INSTANCE = Mappers.getMapper(WallpaperConverter.class);

	/**
	 * PO 转 PageVO
	 * @param wallpaper 壁纸
	 * @return WallpaperPageVO 壁纸PageVO
	 */
	WallpaperPageVO poToPageVo(Wallpaper wallpaper);

	WallpaperRestVO poToRestVo(Wallpaper wallpaper);

}
