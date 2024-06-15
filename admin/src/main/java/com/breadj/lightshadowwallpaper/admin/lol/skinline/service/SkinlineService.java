package com.breadj.lightshadowwallpaper.admin.lol.skinline.service;

import com.breadj.lightshadowwallpaper.admin.lol.skinline.model.entity.Skinline;
import com.breadj.lightshadowwallpaper.admin.lol.skinline.model.vo.SkinlinePageVO;
import com.breadj.lightshadowwallpaper.admin.lol.skinline.model.qo.SkinlineQO;
import com.hccake.ballcat.common.model.domain.PageParam;
import com.hccake.ballcat.common.model.domain.PageResult;
import com.hccake.extend.mybatis.plus.service.ExtendService;

/**
 * 英雄联盟皮肤系列
 *
 * @author liaoheng 2024-06-15 15:53:47
 */
public interface SkinlineService extends ExtendService<Skinline> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;SkinlinePageVO&gt; 分页数据
	 */
	PageResult<SkinlinePageVO> queryPage(PageParam pageParam, SkinlineQO qo);

	/**
	 * 根据拳头皮肤系列id查询皮肤系列信息
	 * @param riotId
	 * @return
	 */
	Skinline getByRiotId(Long riotId);

	/**
	 * 获取并保存pbe的英雄联盟皮肤系列信息列表
	 */
	void getAndSaveSkinline();

}