package com.breadj.lightshadowwallpaper.admin;

import com.breadj.lightshadowwallpaper.admin.wallpaper.service.BingWallpaperService;
import org.ballcat.springsecurity.oauth2.server.authorization.autoconfigure.OAuth2AuthorizationServerAutoConfiguration;
import org.ballcat.springsecurity.oauth2.server.resource.OAuth2ResourceServerAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

@JdbcTest(excludeAutoConfiguration = { OAuth2AuthorizationServerAutoConfiguration.class,
		OAuth2ResourceServerAutoConfiguration.class })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BingWallpaperTest {
	@Autowired
	private BingWallpaperService bingWallpaperService;

	@Test
	public void getBingWallpaper() {
		bingWallpaperService.getBingWallpaper();
	}

}
