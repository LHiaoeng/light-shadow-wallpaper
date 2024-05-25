package com.breadj.lightshadowwallpaper.admin.wallpaper.service.impl;

import com.breadj.lightshadowwallpaper.admin.wallpaper.mapper.WallpaperMapper;
import com.breadj.lightshadowwallpaper.admin.wallpaper.service.WallpaperService;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.entity.Wallpaper;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.vo.WallpaperPageVO;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.qo.WallpaperQO;
import com.hccake.ballcat.common.model.domain.PageParam;
import com.hccake.ballcat.common.model.domain.PageResult;
import com.hccake.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 壁纸
 *
 * @author liaoheng 2024-05-25 14:56:24
 */
@Service
public class WallpaperServiceImpl extends ExtendServiceImpl<WallpaperMapper, Wallpaper> implements WallpaperService {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<WallpaperPageVO> 分页数据
    */
    @Override
    public PageResult<WallpaperPageVO> queryPage(PageParam pageParam, WallpaperQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

}
