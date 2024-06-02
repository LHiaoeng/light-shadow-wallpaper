package com.breadj.lightshadowwallpaper.admin.lol.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.breadj.lightshadowwallpaper.admin.lol.model.vo.HeroInfoVO;
import com.breadj.lightshadowwallpaper.admin.lol.model.vo.HeroListVO;
import com.breadj.lightshadowwallpaper.admin.lol.service.LolWallpaperService;
import com.breadj.lightshadowwallpaper.admin.lol.util.HeroInfoUtil;
import com.breadj.lightshadowwallpaper.admin.lol.util.HeroListUtil;
import com.breadj.lightshadowwallpaper.admin.wallpaper.mapper.WallpaperMapper;
import com.breadj.lightshadowwallpaper.admin.wallpaper.model.entity.Wallpaper;
import com.breadj.lightshadowwallpaper.admin.wallpaper.service.WallpaperService;
import com.hccake.ballcat.system.model.entity.SysDict;
import com.hccake.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class LolWallpaperServiceImpl extends ExtendServiceImpl<WallpaperMapper, Wallpaper>
		implements LolWallpaperService {

	private final WallpaperService wallpaperService;

	/**
	 * @param skin
	 * @return
	 */
	private Wallpaper createWallpaper(HeroInfoVO.SkinsVO skin) {
		Wallpaper wallpaper = new Wallpaper();

		wallpaper.setUrl(skin.getMainImg());
		wallpaper.setPoster(skin.getMainImg());

		String title = skin.getName();

		if (Objects.equals(skin.getIsBase(), "1")) {
			title += " " + skin.getHeroTitle();
		}

		wallpaper.setTitle(title);

		wallpaper.setCopyright("英雄联盟");
		wallpaper.setCopyrightLink("https://lol.qq.com");
		wallpaper.setDescription(skin.getDescription());
		wallpaper.setSource(1);
		wallpaper.setType(1);
		wallpaper.setStatus(0);
		wallpaper.setCreateBy(1L);
		wallpaper.setCreateTime(LocalDateTime.now());
		wallpaper.setUpdateBy(1L);
		wallpaper.setUpdateTime(LocalDateTime.now());

		if (StrUtil.isNotBlank(skin.getPublishTime())) {
			LocalDate localDate = LocalDate.parse(skin.getPublishTime(), DateTimeFormatter.BASIC_ISO_DATE);
			wallpaper.setLaunchTime(localDate.atStartOfDay());
		}

		return wallpaper;
	}

	public void saveBatchIfNotExists(List<Wallpaper> wallpaperList) {
		if (wallpaperList == null || wallpaperList.isEmpty()) {
			return;
		}

		// 查找已存在的记录
		Set<String> existingKeys = baseMapper
			.selectList(Wrappers.<Wallpaper>lambdaQuery()
				.in(Wallpaper::getTitle, wallpaperList.stream().map(Wallpaper::getTitle).collect(Collectors.toSet()))
				.in(Wallpaper::getType, wallpaperList.stream().map(Wallpaper::getType).collect(Collectors.toSet())))
			.stream()
			.map(wallpaper -> wallpaper.getTitle() + ":" + wallpaper.getType())
			.collect(Collectors.toSet());

		log.info("已存在的记录：{}", existingKeys);

		// 过滤出不存在的记录
		List<Wallpaper> nonExistingEntities = wallpaperList.stream()
			.filter(wallpaper -> !existingKeys.contains(wallpaper.getTitle() + ":" + wallpaper.getType()))
			.collect(Collectors.toList());

		if (nonExistingEntities.isEmpty()) {
			return;
		}

		// 批量插入不存在的记录
		wallpaperService.saveBatch(nonExistingEntities);
	}

	/**
	 * 获取英雄皮肤壁纸
	 */
	@Override
	public void getAndSaveLolSkinWallpaper() {
		List<HeroListVO.HeroVO> heroList = HeroListUtil.getHeroList();

		if (CollectionUtil.isEmpty(heroList)) {
			log.error("英雄列表为空");
			return;
		}

		List<Wallpaper> wallpaperList = new ArrayList<>();

		heroList.forEach(hero -> {
			if (null == hero || null == hero.getHeroId()) {
				log.info("英雄对象为空或ID为空。");
				return;
			}

			HeroInfoVO heroInfo = HeroInfoUtil.getHeroInfo(hero.getHeroId());
			if (null == heroInfo) {
				log.info("英雄ID={}，英雄名称={}，信息为空。", hero.getHeroId(), hero.getName() + hero.getTitle());
				return;
			}

			List<HeroInfoVO.SkinsVO> skins = heroInfo.getSkins();
			if (CollectionUtil.isEmpty(skins)) {
				log.info("英雄ID={}，英雄名称={}，皮肤列表为空。", hero.getHeroId(), hero.getName() + hero.getTitle());
				return;
			}

			log.info("英雄ID={}，英雄名称={}，已成功获取皮肤列表。", hero.getHeroId(), hero.getName() + " " + hero.getTitle());

			skins.forEach(skin -> {
				if (null == skin) {
					log.info("皮肤对象为空。");
					return;
				}

				Wallpaper wallpaper = createWallpaper(skin);
				wallpaperList.add(wallpaper);
			});

		});

		saveBatchIfNotExists(wallpaperList);
	}

}
