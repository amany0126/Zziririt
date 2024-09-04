package com.zziririt.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class FileRenameUseUserId implements FileRenamePolicy{
	
	private String UserId;
	
	public FileRenameUseUserId(String Id) {
		UserId = Id;
	}
	@Override
	public File rename(File originFile) {
		String changeName= 	new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) 
				+UserId+originFile.getName().substring(originFile.getName().lastIndexOf("."));
		return new File(originFile.getParent(),changeName);
	}
}
