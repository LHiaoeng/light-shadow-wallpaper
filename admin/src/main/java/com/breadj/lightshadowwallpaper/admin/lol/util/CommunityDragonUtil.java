package com.breadj.lightshadowwallpaper.admin.lol.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.breadj.lightshadowwallpaper.admin.lol.model.vo.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author liaoheng
 * @version 1.0
 * @date 2024/6/15 14:22
 */
@Slf4j
public class CommunityDragonUtil {

	/**
	 * pbe版本
	 */
	public static final String COMMUNITYDRAGON_RAW_PBE_BASE_URL = "https://raw.communitydragon.org/pbe/";

	/**
	 * 最新版本
	 */
	public static final String COMMUNITYDRAGON_RAW_LATEST_BASE_URL = "https://raw.communitydragon.org/latest/";

	public static final String REGION_NAME_DEFAULT = "global";

	public static final String LANGUAGE_CODE_DEFAULT = "default";

	/**
	 * 国服中文
	 */
	public static final String LANGUAGE_CODE_CHINESE = "zh_cn";

	/**
	 * 获取pbe默认英雄列表
	 * @return
	 */
	public static List<PbeChampionSummaryVo> getPbeDefaultChampionSummaryList() {
		try {
			String json = HttpUtil.get(COMMUNITYDRAGON_RAW_PBE_BASE_URL + "plugins/rcp-be-lol-game-data/"
					+ REGION_NAME_DEFAULT + "/" + LANGUAGE_CODE_DEFAULT + "/v1/champion-summary.json");

			return JSON.parseArray(json, PbeChampionSummaryVo.class);
		}
		catch (Exception e) {
			log.error("请求pbe英雄列表接口异常：", e);
			throw new RuntimeException("请求pbe英雄列表接口异常：" + e.getMessage());
		}
	}

	/**
	 * 获取pbe英雄信息
	 * @param championId 英雄信息
	 * @param lang 语言
	 * @return
	 */
	public static PbeChampionVO getPbeChampionInfo(Long championId, String lang) {
		try {
			String json = HttpUtil.get(COMMUNITYDRAGON_RAW_PBE_BASE_URL + "plugins/rcp-be-lol-game-data/"
					+ REGION_NAME_DEFAULT + "/" + lang + "/v1/champions/" + championId + ".json");

			// log.info("请求pbe英雄信息接口返回：{}", json);

			return JSONObject.parseObject(json, PbeChampionVO.class);
		}
		catch (Exception e) {
			log.error("请求pbe英雄信息接口异常：", e);
			throw new RuntimeException("请求pbe英雄信息接口异常：" + e.getMessage());
		}
	}

	/**
	 * 获取pbe皮肤列表 key ： 皮肤id 【英雄id + 皮肤编号（三个字符长度）】
	 * @param lang 语言
	 * @return
	 */
	public static Map<String, PbeSkinVO> getPbeSkinList(String lang) {
		try {
			String json = HttpUtil.get(COMMUNITYDRAGON_RAW_PBE_BASE_URL + "plugins/rcp-be-lol-game-data/"
					+ REGION_NAME_DEFAULT + "/" + lang + "/v1/skins.json");

			return JSONObject.parseObject(json, Map.class);
		}
		catch (Exception e) {
			throw new RuntimeException("请求pbe英雄信息接口异常：" + e.getMessage());
		}
	}

	/**
	 * 获取pbe皮肤系列信息列表
	 * @param lang
	 * @return
	 */
	public static List<PbeSkinlineVO> getPbeSkinlineList(String lang) {
		try {
			String json = HttpUtil.get(COMMUNITYDRAGON_RAW_PBE_BASE_URL + "plugins/rcp-be-lol-game-data/"
					+ REGION_NAME_DEFAULT + "/" + lang + "/v1/skinlines.json");

			return JSONArray.parseArray(json, PbeSkinlineVO.class);
		}
		catch (Exception e) {
			log.error("请求pbe皮肤系列信息列表接口异常：", e);
			throw new RuntimeException("请求pbe皮肤系列信息列表接口异常：" + e.getMessage());
		}
	}

	/**
	 * 获取pbe英雄联盟宇宙信息列表
	 * @param lang
	 * @return
	 */
	public static List<PbeUniverseVO> getPbeUniverseList(String lang) {
		try {
			String json = HttpUtil.get(COMMUNITYDRAGON_RAW_PBE_BASE_URL + "plugins/rcp-be-lol-game-data/"
					+ REGION_NAME_DEFAULT + "/" + lang + "/v1/universes.json");

			return JSONArray.parseArray(json, PbeUniverseVO.class);
		}
		catch (Exception e) {
			log.error("请求pbe英雄联盟宇宙信息列表接口异常：", e);
			throw new RuntimeException("请求pbe英雄联盟宇宙信息列表接口异常：" + e.getMessage());
		}
	}

	public static void main(String[] args) {
		// getPbeDefaultChampionSummaryList();
		// getPbeChampionInfo(103L, CHINESE_LANGUAGE_CODE);
		// Map<String, PbeSkinVO> pbeSkinList = getPbeSkinList(CHINESE_LANGUAGE_CODE);
		// log.info("pbeSkinList:{}", pbeSkinList.get("103001"));

		// log.info("PbeSkinlineList:{}", getPbeSkinlineList(CHINESE_LANGUAGE_CODE));

		// log.info("PbeUniverseList:{}", getPbeUniverseList(CHINESE_LANGUAGE_CODE));

	}

}
