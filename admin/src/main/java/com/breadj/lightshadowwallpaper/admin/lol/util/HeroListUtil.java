package com.breadj.lightshadowwallpaper.admin.lol.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.breadj.lightshadowwallpaper.admin.lol.model.vo.HeroListVO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author liaoheng
 * @version 1.0
 * @date 2024/6/1 14:55
 */
@Slf4j
public class HeroListUtil {

	private static final String HERO_LIST_URL = "https://game.gtimg.cn/images/lol/act/img/js/heroList/hero_list.js?ts=";

	public static List<HeroListVO.HeroVO> getHeroList() {
		// 生成请求的完整URL，带有时间戳参数
		String url = HERO_LIST_URL + (System.currentTimeMillis() / 600000);

		try (HttpResponse response = HttpRequest.get(url).execute()) {

			if (response.isOk()) {
				// 解析响应JSON
				HeroListVO heroListVO = JSON.parseObject(response.body(), HeroListVO.class);

				// 提取英雄列表
				return heroListVO.getHeroList();
			}
			else {
				throw new RuntimeException("Failed to fetch hero list: " + response.getStatus());
			}
		}
	}

	public static void main(String[] args) {
		List<HeroListVO.HeroVO> heroList = getHeroList();
		log.info("heroList:{}", heroList);
	}

}