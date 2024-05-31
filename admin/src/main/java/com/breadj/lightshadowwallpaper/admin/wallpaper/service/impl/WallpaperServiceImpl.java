package com.breadj.lightshadowwallpaper.admin.wallpaper.service.impl;

import com.breadj.lightshadowwallpaper.admin.wallpaper.mapper.WallpaperMapper;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.vo.WallpaperRestVO;
import com.breadj.lightshadowwallpaper.admin.wallpaper.service.WallpaperService;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.entity.Wallpaper;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.vo.WallpaperPageVO;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.qo.WallpaperQO;
import com.hccake.ballcat.common.model.domain.PageParam;
import com.hccake.ballcat.common.model.domain.PageResult;
import com.hccake.ballcat.system.mapper.SysUserMapper;
import com.hccake.ballcat.system.model.entity.SysUser;
import com.hccake.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.hccake.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import com.hccake.extend.mybatis.plus.toolkit.WrappersX;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 壁纸
 *
 * @author liaoheng 2024-05-25 14:56:24
 */
@Slf4j
@Service
public class WallpaperServiceImpl extends ExtendServiceImpl<WallpaperMapper, Wallpaper> implements WallpaperService {

	@Autowired
	private SysUserMapper sysUserMapper;

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<WallpaperPageVO> 分页数据
	 */
	@Override
	public PageResult<WallpaperPageVO> queryPage(PageParam pageParam, WallpaperQO qo) {
		PageResult<WallpaperPageVO> wallpaperPage = baseMapper.queryPage(pageParam, qo);
		List<WallpaperPageVO> records = wallpaperPage.getRecords().stream().peek(vo -> {

			LambdaQueryWrapperX<SysUser> sysUserWrapper = WrappersX.lambdaQueryX(SysUser.class);
			if (null != vo.getCreateBy()) {
				sysUserWrapper.eq(SysUser::getUserId, vo.getCreateBy());
				SysUser creator = sysUserMapper.selectOne(sysUserWrapper);
				vo.setCreator(creator);
			}

			if (null != vo.getUpdateBy()) {
				sysUserWrapper.eq(SysUser::getUserId, vo.getUpdateBy());
				SysUser updater = sysUserMapper.selectOne(sysUserWrapper);
				vo.setUpdater(updater);
			}

		}).collect(Collectors.toList());

		wallpaperPage.setRecords(records);

		return wallpaperPage;
	}

	@Override
	public PageResult<WallpaperRestVO> queryRestPage(PageParam pageParam, WallpaperQO qo) {
		return baseMapper.queryRestPage(pageParam, qo);
	}
}
