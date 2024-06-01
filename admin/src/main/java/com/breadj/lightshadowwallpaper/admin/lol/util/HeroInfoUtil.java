package com.breadj.lightshadowwallpaper.admin.lol.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.breadj.lightshadowwallpaper.admin.lol.model.vo.HeroInfoVO;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

/**
 * @author liaoheng
 * @version 1.0
 * @date 2024/6/1 15:29
 */
@Slf4j
public class HeroInfoUtil {

	private static final String HERO_INFO_URL = "https://game.gtimg.cn/images/lol/act/img/js/hero/";

	/**
	 * 根据英雄ID获取英雄信息、技能、皮肤
	 * @param heroId
	 * @return
	 */
	public static HeroInfoVO getHeroInfo(String heroId) {
		if (StrUtil.isBlank(heroId)) {
			throw new IllegalArgumentException("请传入heroId");
		}

		// 生成请求的完整URL，带有时间戳参数
		String url = HERO_INFO_URL + heroId + ".js?ts=" + (System.currentTimeMillis() / 600000);

		try (HttpResponse response = HttpRequest.get(url).execute()) {
			if (response.isOk()) {
				return JSON.parseObject(response.body(), HeroInfoVO.class);
			}
			else {
				throw new RuntimeException("Failed to fetch hero info: " + response.getStatus());
			}
		}
	}

	public static void main(String[] args) {
		try {
			HeroInfoVO heroInfo = getHeroInfo("64");
			log.info("heroInfo:{}", JSONObject.toJSONString(
					heroInfo.getSkins().stream().map(HeroInfoVO.SkinsVO::getName).collect(Collectors.toList())));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
