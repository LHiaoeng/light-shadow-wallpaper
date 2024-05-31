package com.breadj.lightshadowwallpaper.admin.wallpaper.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.breadj.lightshadowwallpaper.admin.wallpaper.converter.WallpaperConverter;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.entity.Wallpaper;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.qo.WallpaperQO;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.vo.WallpaperPageVO;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.vo.WallpaperRestVO;
import com.hccake.ballcat.common.model.domain.PageParam;
import com.hccake.ballcat.common.model.domain.PageResult;
import com.hccake.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.hccake.extend.mybatis.plus.mapper.ExtendMapper;
import com.hccake.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 壁纸
 *
 * @author liaoheng 2024-05-25 14:56:24
 */
public interface WallpaperMapper extends ExtendMapper<Wallpaper> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询参数
	 * @return PageResult<WallpaperPageVO> VO分页数据
	 */
	default PageResult<WallpaperPageVO> queryPage(PageParam pageParam, WallpaperQO qo) {
		IPage<Wallpaper> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<Wallpaper> wrapper = WrappersX.lambdaQueryX(Wallpaper.class);

		wrapper.orderByDesc(Wallpaper::getId)
			.likeIfPresent(Wallpaper::getTitle, qo.getTitle())
			.eqIfPresent(Wallpaper::getSource, qo.getSource())
			.eqIfPresent(Wallpaper::getStatus, qo.getStatus())
			.eqIfPresent(Wallpaper::getType, qo.getType());

		this.selectPage(page, wrapper);
		IPage<WallpaperPageVO> voPage = page.convert(WallpaperConverter.INSTANCE::poToPageVo);

		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

	default PageResult<WallpaperRestVO> queryRestPage(PageParam pageParam, WallpaperQO qo) {
		IPage<Wallpaper> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<Wallpaper> wrapper = WrappersX.lambdaQueryX(Wallpaper.class);
		wrapper.eq(Wallpaper::getStatus, 0)
			.likeIfPresent(Wallpaper::getTitle, qo.getTitle())
			.eqIfPresent(Wallpaper::getSource, qo.getSource())
			.eqIfPresent(Wallpaper::getBingCountry, qo.getBingCountry())
			.leIfPresent(Wallpaper::getLaunchTime, qo.getLaunchTimeEnd())
			.geIfPresent(Wallpaper::getLaunchTime, qo.getLaunchTimeStart())
			.eqIfPresent(Wallpaper::getType, qo.getType());

		this.selectPage(page, wrapper);
		IPage<WallpaperRestVO> voPage = page.convert(WallpaperConverter.INSTANCE::poToRestVo);

		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

}