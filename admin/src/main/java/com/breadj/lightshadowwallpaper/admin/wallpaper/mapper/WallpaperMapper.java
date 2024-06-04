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
			.leIfPresent(Wallpaper::getLaunchTime, qo.getLaunchTimeEnd())
			.geIfPresent(Wallpaper::getLaunchTime, qo.getLaunchTimeStart())
			.likeIfPresent(Wallpaper::getTitle, qo.getTitle())
			.eqIfPresent(Wallpaper::getSource, qo.getSource())
			.eqIfPresent(Wallpaper::getStatus, qo.getStatus())
			.likeIfPresent(Wallpaper::getUrlBase, qo.getUrlBase())
			.eqIfPresent(Wallpaper::getType, qo.getType());

		if (qo.getHasMainUrl() != null) {
			if (qo.getHasMainUrl() == 1) {
				// 字段不为空且不为 null
				wrapper.isNotNull(Wallpaper::getUrl).ne(Wallpaper::getUrl, "");
			}
			else if (qo.getHasMainUrl() == 0) {
				// 字段为空或为 null
				wrapper.and(w -> w.isNull(Wallpaper::getUrl).or().eq(Wallpaper::getUrl, ""));
			}
		}

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
			// 过滤掉没有图片的
			.isNotNull(Wallpaper::getUrl)
			.ne(Wallpaper::getUrl, "")
			.eqIfPresent(Wallpaper::getType, qo.getType());

		// 先按创建时间倒序排序，再按上架时间倒序排序
		wrapper.orderByDesc(Wallpaper::getId).orderByDesc(Wallpaper::getLaunchTime);

		this.selectPage(page, wrapper);
		IPage<WallpaperRestVO> voPage = page.convert(WallpaperConverter.INSTANCE::poToRestVo);

		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

}