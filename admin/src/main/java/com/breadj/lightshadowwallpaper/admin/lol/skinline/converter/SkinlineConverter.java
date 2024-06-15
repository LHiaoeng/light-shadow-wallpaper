package com.breadj.lightshadowwallpaper.admin.lol.skinline.converter;

import com.breadj.lightshadowwallpaper.admin.lol.skinline.model.entity.Skinline;
import com.breadj.lightshadowwallpaper.admin.lol.skinline.model.vo.SkinlinePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 英雄联盟皮肤系列模型转换器
 *
 * @author liaoheng 2024-06-15 15:53:47
 */
@Mapper
public interface SkinlineConverter {

	SkinlineConverter INSTANCE = Mappers.getMapper(SkinlineConverter.class);

	/**
	 * PO 转 PageVO
	 * @param skinline 英雄联盟皮肤系列
	 * @return SkinlinePageVO 英雄联盟皮肤系列PageVO
	 */
	SkinlinePageVO poToPageVo(Skinline skinline);

}
