package com.hisi.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hisi.common.file.FtpUtils;
import com.hisi.common.util.DateUtil;
import com.hisi.common.util.DateUtil.Pattern;
import com.hisi.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {

	@Value("${ftp.ip}")
	private String ftpIp;
	@Value("${ftp.port}")
	private int ftpPort;
	@Value("${ftp.account}")
	private String ftpAccount;
	@Value("${ftp.password}")
	private String ftpPassword;
	@Value("${ftp.basePath}")
	private String ftpBasePath;
	@Value("${mImagesShowPath}")
	private String mImagesShowPath;

	@Override
	public String pictureUpload(CommonsMultipartFile file,
			HttpServletRequest request, String filePath) {
		String nameEnd = file.getOriginalFilename().substring(
				file.getOriginalFilename().lastIndexOf(".") + 1);
		nameEnd = nameEnd.equalsIgnoreCase("blob") ? "jpg" : nameEnd;
		// 文件的路径
		filePath = filePath + "/" + DateUtil.getSysTime(Pattern.PATTERN_4);
		// 文件改的名字
		String fileName = DateUtil.getSysTime(Pattern.PATTERN_1)
				+ (int) (Math.random() * 9000 + 1000) + "." + nameEnd;
		// 上传图片到FTP
		FtpUtils ftpUtils = new FtpUtils(ftpIp, ftpPort, ftpAccount,
				ftpPassword, ftpBasePath);
		boolean flag = false;
		try {
			flag = ftpUtils.uploadFile(filePath, fileName,
					file.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!flag)
			return null;
		return mImagesShowPath + filePath + "/" + fileName;
	}

	@Override
	public List<String> pictureUploads(CommonsMultipartFile[] files,
			HttpServletRequest request, String filePath) {
		boolean flag = false;
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < files.length; i++) {
			String nameEnd = files[i].getOriginalFilename().substring(
					files[i].getOriginalFilename().lastIndexOf(".") + 1);
			nameEnd = nameEnd.equalsIgnoreCase("blob") ? "jpg" : nameEnd;
			// 文件的路径
			String filePathUpload = filePath + "/"
					+ DateUtil.getSysTime(Pattern.PATTERN_4);
			// 文件改的名字
			String fileName = DateUtil.getSysTime(Pattern.PATTERN_1)
					+ (int) (Math.random() * 9000 + 1000) + "." + nameEnd;
			// 上传图片到FTP
			FtpUtils ftpUtils = new FtpUtils(ftpIp, ftpPort, ftpAccount,
					ftpPassword, ftpBasePath);
			try {
				flag = ftpUtils.uploadFile(filePathUpload, fileName,
						files[i].getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!flag)
				return null;
			result.add(mImagesShowPath + filePathUpload + "/" + fileName);
		}
		return result;
	}
}