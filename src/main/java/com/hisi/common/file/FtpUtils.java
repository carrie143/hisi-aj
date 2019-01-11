package com.hisi.common.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * ftp文件上下载工具
 * 
 * @author Ys 2017年12月5日 上午9:04:19
 */
public class FtpUtils {
	/**
	 * 
	 * FTP服务器 IP
	 */
	private String ftpHostName;
	/**
	 * 
	 * FTP服务器端口
	 */
	private Integer ftpPort;
	/**
	 * 
	 * FTP登录账号
	 */
	private String ftpUserName;
	/**
	 * 
	 * FTP登录密码
	 */
	private String ftpPassword;
	/**
	 * 
	 * FTP服务器基础目录
	 */
	private String ftpBasePath;

	/**
	 * 
	 * @param ftpHostName
	 *            IP
	 * @param ftpPort
	 *            端口
	 * @param ftpUserName
	 *            用户名
	 * @param ftpPassword
	 *            密码
	 * @param ftpBasePath
	 *            服务器基础目录 windows系列服务器不指定此参数，linux需指定
	 */
	public FtpUtils(String ftpHostname, Integer ftpPort, String ftpUsername,
			String ftpPassword, String ftpBasePath) {
		super();
		this.ftpHostName = ftpHostname;
		this.ftpPort = ftpPort;
		this.ftpUserName = ftpUsername;
		this.ftpPassword = ftpPassword;
		this.ftpBasePath = ftpBasePath == null ? "" : ftpBasePath;
	}

	/**
	 * 
	 * 向FTP服务器上传文件
	 * 
	 * 
	 * 
	 * @param filePath
	 *            FTP服务器文件存放路径。例如分日期存放：/2017/01/01。文件的路径为basePath+filePath
	 * 
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * 
	 * @param input
	 *            输入流
	 * 
	 * @return 成功返回true，否则返回false
	 * 
	 * 
	 */
	public boolean uploadFile(String filePath, String filename,
			InputStream input) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			/**
			 * 
			 * 连接FTP服务器
			 */
			ftp.connect(ftpHostName, ftpPort);
			/**
			 * 
			 * 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
			 */
			ftp.login(ftpUserName, ftpPassword);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			/**
			 * 
			 * 切换到上传目录
			 */
			System.out.println(ftp.printWorkingDirectory());
			if (!ftp.changeWorkingDirectory(ftpBasePath + filePath)) {
				/**
				 * 
				 * 如果目录不存在创建目录
				 */
				String[] dirs = filePath.split("/");
				// String tempPath = ftpBasePath;
				String tempPath = "";
				for (String dir : dirs) {
					if (null == dir || "".equals(dir))
						continue;
					tempPath += "\\" + dir;
					if (!ftp.changeWorkingDirectory(tempPath)) {
						if (!ftp.makeDirectory(tempPath)) {
							return result;
						} else {
							ftp.changeWorkingDirectory(tempPath);
						}
					}
				}
			}
			// 解决本机不能上传文件的问题
			// ftp.enterLocalPassiveMode();
			/**
			 * 
			 * 设置上传文件的类型为二进制类型
			 */
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			/**
			 * 
			 * 上传文件
			 */
			if (!ftp.storeFile(filename, input)) {
				return result;
			}
			input.close();
			ftp.logout();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * 从FTP服务器下载文件
	 * 
	 * 
	 * 
	 * @param remotePath
	 *            FTP服务器上的相对路径
	 * 
	 * @param fileName
	 *            要下载的文件名
	 * 
	 * @param localPath
	 *            下载后保存到本地的路径
	 * 
	 * @return 成功返回true，否则返回false
	 * 
	 * 
	 */
	public boolean downloadFile(String remotePath, String fileName,
			String localPath) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(ftpHostName, ftpPort);
			System.out.println("connect");
			/**
			 * 
			 * 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
			 */
			ftp.login(ftpUserName, ftpPassword);
			System.out.println("login");
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			/**
			 * 
			 * 转移到FTP服务器目录
			 */
			// ftp.changeWorkingDirectory(ftpBasePath + remotePath);
			ftp.changeWorkingDirectory(remotePath);
			FTPFile[] fs = ftp.listFiles();
			for (FTPFile ff : fs) {
				if (ff.getName().equals(fileName)) {
					File localFile = new File(localPath + "/" + ff.getName());

					OutputStream is = new FileOutputStream(localFile);
					ftp.retrieveFile(ff.getName(), is);
					is.close();
				}
			}

			ftp.logout();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * 删除ftp上的文件
	 * 
	 * @param srcFname
	 * @return true || false
	 */
	public boolean removeFile(String srcFname) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		int reply;
		try {
			ftp.connect(ftpHostName, ftpPort);
			ftp.login(ftpUserName, ftpPassword);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			if (ftp != null)
				// result = ftp.deleteFile(ftpBasePath + srcFname);
				result = ftp.deleteFile(srcFname);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
