package com.breadj.lightshadowwallpaper.admin.wallpaper.service;

import com.breadj.lightshadowwallpaper.admin.wallpaper.model.entity.Wallpaper;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.vo.WallpaperPageVO;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.qo.WallpaperQO;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.vo.WallpaperRestVO;
import com.hccake.ballcat.common.model.domain.PageParam;
import com.hccake.ballcat.common.model.domain.PageResult;
import com.hccake.extend.mybatis.plus.service.ExtendService;

/**
 * 壁纸
 *
 * @author liaoheng 2024-05-25 14:56:24
 */
public interface WallpaperService extends ExtendService<Wallpaper> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;WallpaperPageVO&gt; 分页数据
	 */
	PageResult<WallpaperPageVO> queryPage(PageParam pageParam, WallpaperQO qo);

	PageResult<WallpaperRestVO> queryRestPage(PageParam pageParam, WallpaperQO qo);

	/**
	 * 根据id复制壁纸
	 * @param id 需要复制的壁纸ID
	 * @return
	 */
	boolean copyWallpaperById(Long id);

}