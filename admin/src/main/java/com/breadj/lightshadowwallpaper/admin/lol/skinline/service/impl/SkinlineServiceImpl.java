package com.breadj.lightshadowwallpaper.admin.lol.skinline.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.breadj.lightshadowwallpaper.admin.lol.model.vo.PbeSkinlineVO;
import com.breadj.lightshadowwallpaper.admin.lol.skinline.model.entity.Skinline;
import com.breadj.lightshadowwallpaper.admin.lol.skinline.model.vo.SkinlinePageVO;
import com.breadj.lightshadowwallpaper.admin.lol.skinline.model.qo.SkinlineQO;
import com.breadj.lightshadowwallpaper.admin.lol.skinline.mapper.SkinlineMapper;
import com.breadj.lightshadowwallpaper.admin.lol.skinline.service.SkinlineService;
import com.breadj.lightshadowwallpaper.admin.lol.util.CommunityDragonUtil;
import com.hccake.ballcat.common.model.domain.PageParam;
import com.hccake.ballcat.common.model.domain.PageResult;
import com.hccake.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 英雄联盟皮肤系列
 *
 * @author liaoheng 2024-06-15 15:53:47
 */
@Service
@Slf4j
public class SkinlineServiceImpl extends ExtendServiceImpl<SkinlineMapper, Skinline> implements SkinlineService {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<SkinlinePageVO> 分页数据
	 */
	@Override
	public PageResult<SkinlinePageVO> queryPage(PageParam pageParam, SkinlineQO qo) {
		return baseMapper.queryPage(pageParam, qo);
	}

	/**
	 * 根据拳头皮肤系列id查询皮肤系列信息
	 * @param riotId
	 * @return
	 */
	@Override
	public Skinline getByRiotId(Long riotId) {
		QueryWrapper<Skinline> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("riot_skinline_id", riotId);

		return baseMapper.selectOne(queryWrapper);
	}

	/**
	 * 获取并保存pbe的英雄联盟皮肤系列信息列表
	 */
	@Override
	public void getAndSaveSkinline() {
		// 英文
		List<PbeSkinlineVO> pbeSkinlineList = CommunityDragonUtil
			.getPbeSkinlineList(CommunityDragonUtil.LANGUAGE_CODE_DEFAULT);
		List<PbeSkinlineVO> chinesePbeSkinlineList = CommunityDragonUtil
			.getPbeSkinlineList(CommunityDragonUtil.LANGUAGE_CODE_CHINESE);
		if (CollectionUtil.isEmpty(chinesePbeSkinlineList)) {
			log.info("pbe皮肤系列中文信息列表失败。");
			return;
		}

		List<Skinline> skinlineList = new ArrayList<>();

		for (PbeSkinlineVO pbeSkinlineVO : chinesePbeSkinlineList) {
			Long riotId = pbeSkinlineVO.getId();
			if (null == riotId || riotId <= 0L) {
				continue;
			}

			Skinline skinline = new Skinline();

			String name = pbeSkinlineVO.getName();
			String description = pbeSkinlineVO.getDescription();

			// 根据riotId(不是主键）查询记录是否存在
			Skinline oldSkinline = this.getByRiotId(riotId);
			if (null != oldSkinline) {
				if (name.equals(oldSkinline.getName()) && description.equals(oldSkinline.getDescription())) {
					continue;
				}

				skinline.setId(oldSkinline.getId());
			}

			skinlineList.add(skinline);

			skinline.setRiotSkinlineId(riotId);
			skinline.setName(name);
			skinline.setDescription(description);

			if (CollectionUtil.isEmpty(pbeSkinlineList)) {
				continue;
			}

			PbeSkinlineVO engVO = pbeSkinlineList.stream()
				.filter(vo -> vo.getId().equals(pbeSkinlineVO.getId()))
				.findFirst()
				.orElse(null);

			if (null == engVO) {
				continue;
			}

			skinline.setEngName(engVO.getName());
			skinline.setEngDescription(engVO.getDescription());
		}

		if (CollectionUtil.isEmpty(skinlineList)) {
			return;
		}

		this.saveOrUpdateBatch(skinlineList);
	}

}
