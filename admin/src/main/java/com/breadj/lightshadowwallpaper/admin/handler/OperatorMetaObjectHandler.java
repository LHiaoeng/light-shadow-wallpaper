package com.breadj.lightshadowwallpaper.admin.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.hccake.ballcat.common.security.userdetails.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 操作人自动填充
 */
@Slf4j
@Component
public class OperatorMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		Long currentUserId = getCurrentUserId();
		LocalDateTime now = LocalDateTime.now();

		// 设置创建人字段
		this.strictInsertFill(metaObject, "createBy", Long.class, currentUserId);
		// 设置创建时间字段
		this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, now);

		// 设置更新人字段
		this.strictInsertFill(metaObject, "updateBy", Long.class, currentUserId);
		// 设置更新时间字段
		this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, now);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		// 获取当前操作人的 ID
		Long currentUserId = getCurrentUserId();

		// 设置更新人字段
		this.strictUpdateFill(metaObject, "updateBy", Long.class, currentUserId);
		// 设置更新时间字段
		this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
	}

	private Long getCurrentUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User) authentication.getPrincipal();

		return currentUser.getUserId();
	}

}
