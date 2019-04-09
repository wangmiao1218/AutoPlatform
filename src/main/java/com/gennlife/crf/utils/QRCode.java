package com.gennlife.crf.utils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * @Description: 生成二维码
 * @author: wangmiao
 * @Date: 2018年6月28日 下午12:20:03 
 */
public class QRCode {

	public static void main(String[] args) throws IOException {
		String text = "你好";
		int width = 100;
		int height = 100;
		String format = "png";
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		hints.put(EncodeHintType.MARGIN, 2);
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(text,
					BarcodeFormat.QR_CODE, width, height, hints);
			Path file = new java.io.File("E:/new.png").toPath();
			MatrixToImageWriter.writeToPath(bitMatrix, format, file);

		} catch (WriterException e) {
			e.printStackTrace();
		}
	}

	
}
