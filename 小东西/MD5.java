package com.haha;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public static void main(String[] args) {
		// md5加密
		// 1.信息摘要器
		try {
			MessageDigest digest = MessageDigest.getInstance("md5");
			String password = "12356";
			// 2.变成byte数组
			byte bytes[] = digest.digest(password.getBytes());
			StringBuffer buffer = new StringBuffer();

			// 3.每一个byte个和8个二进制位做与运算
			for (byte b : bytes) {
				int number = b & 0xff;

				// 4.把int类型转换成十六进制
				String numberStr = Integer.toHexString(number);
				System.out.println();
				// 5.不足的补全
				if (numberStr.length() == 1) {
					buffer.append("0");
				}

				buffer.append(numberStr);

			}
			// buffer.toString();标准的md5加密后的结果
			System.out.println(buffer.toString());

		} catch (NoSuchAlgorithmException e) {
			// 没有预先准备的说法异常
			e.printStackTrace();
		}
	}
}
