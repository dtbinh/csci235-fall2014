package edu.hendrix.img.database;

import edu.hendrix.lmsl.storage.Storage;

public class YUYVImageListStorage extends Storage<YUYVImageList> {

	public static YUYVImageListStorage getEV3Storage() {
		return new YUYVImageListStorage("/home/root");
	}
	
	public static YUYVImageListStorage getPCStorage() {
		return new YUYVImageListStorage(System.getProperty("user.home"));
	}
	
	public YUYVImageListStorage(String baseDir) {
		super(baseDir);
	}

	@Override
	public String getDirName() {
		return "YUYVImages";
	}

	@Override
	public String getDefaultName() {
		return "images.txt";
	}

	@Override
	public YUYVImageList fromString(String src) {
		return YUYVImageList.fromString(src);
	}
}
