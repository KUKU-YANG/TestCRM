package com.crm.utils;

import java.util.UUID;

/**
 * �ļ��ϴ�������
 * 
 * @author ljw
 *
 */
public class UploadUtils {
	// ��������ļ���
	public static String getUuidFileName(String fileName) {
		// ��ȡ��չ��
		Integer idx = fileName.lastIndexOf(".");
		String extions = fileName.substring(idx);
		// ���������ȥ����-���������չ��
		return UUID.randomUUID().toString().replace("-", "") + extions;
	}

	// Ŀ¼����
	public static String getPath(String uuidFileName) {
		int code1 = uuidFileName.hashCode();
		int d1 = code1 & 0xf;// ��Ϊ1��Ŀ¼
		int cdoe2 = code1 >>> 4;
		int d2 = cdoe2 & 0xf;// ��Ϊ2��Ŀ¼
		return "/" + d1 + "/" + d2;
	}

	public static void main(String[] args) {
		System.out.println(getUuidFileName("aaa.txt"));
	}
}