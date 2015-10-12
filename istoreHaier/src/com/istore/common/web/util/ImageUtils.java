package com.istore.common.web.util;

import java.awt.Dimension;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUtils {

	public static final String _PNG = ".PNG";
	public static final String _JPG = ".JPG";
	public static final String _GIF = ".GIF";
	public static final String _png = ".png";
	public static final String _jpg = ".jpg";
	public static final String _gif = ".gif";

	private static final Logger log = LoggerFactory.getLogger(ImageUtils.class);

	public static String getImageName(String fileName) {
		if (fileName.endsWith(_PNG)) {
			fileName = UUID.randomUUID().toString() + _PNG;
		} else if (fileName.endsWith(_JPG)) {
			fileName = UUID.randomUUID().toString() + _JPG;
		} else if (fileName.endsWith(_GIF)) {
			fileName = UUID.randomUUID().toString() + _GIF;
		} else if (fileName.endsWith(_png)) {
			fileName = UUID.randomUUID().toString() + _png;
		} else if (fileName.endsWith(_jpg)) {
			fileName = UUID.randomUUID().toString() + _jpg;
		} else if (fileName.endsWith(_gif)) {
			fileName = UUID.randomUUID().toString() + _gif;
		}
		return fileName;
	}

	public static String getImageName(String fileName, String catentryId) {
		return catentryId + _jpg;
	}

	public static byte[] readData(InputStream in) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = in.read(buffer)) != -1) {
			output.write(buffer, 0, len);
		}
		output.close();
		return output.toByteArray();
	}

	public static byte[] zoom(byte[] imgData, Integer width, Integer height,
			boolean sameDegree) {
		if (imgData == null || 0 == imgData.length)
			log.error("___Image is null");
		try {
			ImageInfo info = new ImageInfo();
			MagickImage image = new MagickImage(info, imgData);
			return zoom(image, width, height, sameDegree);
		} catch (MagickException e) {
			log.error("__" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public  static double scale(byte[] imgData) {
		try {
			ImageInfo info = new ImageInfo();
			MagickImage image = new MagickImage(info, imgData);
			Dimension dim = image.getDimension();
			double imgWidth = dim.getWidth();
			double imgHeight = dim.getHeight();
			log.info("imgWidth:"+imgWidth);
			log.info("imgHeight:"+imgHeight);
			return (imgWidth/imgHeight);
		} catch (MagickException e) {
			e.printStackTrace();
			return 0;
		}

	}
	
	public static boolean validate(byte[] imgData,double width,double height){
		try {
			ImageInfo info = new ImageInfo();
			MagickImage image = new MagickImage(info, imgData);
			Dimension dim = image.getDimension();
			double imgWidth = dim.getWidth();
			double imgHeight = dim.getHeight();
			return (width==imgWidth)&&(imgHeight==height);
		} catch (MagickException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public static byte[] zoom(MagickImage image, Integer width, Integer height,boolean sameDegree)  {
		try{
			if (sameDegree) {
				Dimension dim = image.getDimension();
				double imgWidth = dim.getWidth();
				double imgHeight = dim.getHeight();
				double sx = (double) width / imgWidth;
				double sy = (double) height / imgHeight;
				if (sx > sy) {
					sx = sy;
					width = new Long(Math.round(sx * imgWidth)).intValue();
				} else {
					sy = sx;
					height = new Long(Math.round(sy * imgHeight)).intValue();
				}
			}
			MagickImage scaled = image.scaleImage(width, height);
			return scaled.imageToBlob(new ImageInfo());
			
		}catch (MagickException e) {
			e.printStackTrace();
			return null;
		}
	}

}
