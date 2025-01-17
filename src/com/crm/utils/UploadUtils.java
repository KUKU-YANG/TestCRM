package com.crm.utils;

import java.util.UUID;

/**
 * 文件上传工具类
 * 
 * @author ljw
 *
 */
public class UploadUtils {
	// 生成随机文件名
	public static String getUuidFileName(String fileName) {
		// 获取扩展名
		Integer idx = fileName.lastIndexOf(".");
		String extions = fileName.substring(idx);
		// 将随机名字去除“-”后加上扩展名
		return UUID.randomUUID().toString().replace("-", "") + extions;
	}

	// 目录分离
	public static String getPath(String uuidFileName) {
		int code1 = uuidFileName.hashCode();
		int d1 = code1 & 0xf;// 作为1级目录
		int cdoe2 = code1 >>> 4;
		int d2 = cdoe2 & 0xf;// 作为2级目录
		return "/" + d1 + "/" + d2;
	}

	public static void main(String[] args) {
		System.out.println(getUuidFileName("aaa.txt"));
	}
}
