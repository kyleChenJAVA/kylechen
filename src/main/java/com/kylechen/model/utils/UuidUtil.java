package com.kylechen.model.utils;

import java.util.UUID;
/*
 *主键生成工具类
 *@author kylechen QQ2848142215
 * 2018年3月20日
 */
public class UuidUtil {
	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
	public static void main(String[] args) {
		System.out.println(get32UUID());
	}
}

