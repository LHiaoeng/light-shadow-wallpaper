package com.breadj.lightshadowwallpaper.admin.lol.skinline.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.breadj.lightshadowwallpaper.admin.lol.skinline.converter.SkinlineConverter;
import com.breadj.lightshadowwallpaper.admin.lol.skinline.model.entity.Skinline;
import com.breadj.lightshadowwallpaper.admin.lol.skinline.model.qo.SkinlineQO;
import com.breadj.lightshadowwallpaper.admin.lol.skinline.model.vo.SkinlinePageVO;
import com.hccake.ballcat.common.model.domain.PageParam;
import com.hccake.ballcat.common.model.domain.PageResult;
import com.hccake.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.hccake.extend.mybatis.plus.mapper.ExtendMapper;
import com.hccake.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 英雄联盟皮肤系列
 *
 * @author liaoheng 2024-06-15 15:53:47
 */
public interface SkinlineMapper extends ExtendMapper<Skinline> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询参数
	 * @return PageResult<SkinlinePageVO> VO分页数据
	 */
	default PageResult<SkinlinePageVO> queryPage(PageParam pageParam, SkinlineQO qo) {
		IPage<Skinline> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<Skinline> wrapper = WrappersX.lambdaQueryX(Skinline.class);
		this.selectPage(page, wrapper);
		IPage<SkinlinePageVO> voPage = page.convert(SkinlineConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

}