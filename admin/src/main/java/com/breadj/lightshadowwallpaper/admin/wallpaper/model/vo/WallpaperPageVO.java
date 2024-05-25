package com.breadj.lightshadowwallpaper.admin.wallpaper.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 壁纸分页视图对象
 *
 * @author liaoheng 2024-05-25 14:56:24
 */
@Data
@Schema(title = "壁纸分页视图对象")
public class WallpaperPageVO {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Schema(title="主键id")
    private Integer id;
    
    /**
     * 壁纸类型。1：图片；2：视频
     */
    @Schema(title="壁纸类型。1：图片；2：视频")
    private Integer type;
    
    /**
     * 壁纸标题
     */
    @Schema(title="壁纸标题")
    private String title;
    
    /**
     * 标题链接
     */
    @Schema(title="标题链接")
    private String titleLink;
    
    /**
     * 壁纸网址
     */
    @Schema(title="壁纸网址")
    private String url;
    
    /**
     * 壁纸默认地址
     */
    @Schema(title="壁纸默认地址")
    private String urlBase;
    
    /**
     * 版权信息
     */
    @Schema(title="版权信息")
    private String copyright;
    
    /**
     * 版权信息链接
     */
    @Schema(title="版权信息链接")
    private String copyrightLink;
    
    /**
     * 壁纸描述
     */
    @Schema(title="壁纸描述")
    private String description;
    
    /**
     * 壁纸来源(0-默认未知,1-英雄联盟,2-必应)
     */
    @Schema(title="壁纸来源(0-默认未知,1-英雄联盟,2-必应)")
    private Integer source;
    
    /**
     * 上架时间
     */
    @Schema(title="上架时间")
    private LocalDateTime launchTime;
    
	@Schema(title="创建时间")
	private LocalDateTime createTime;
}