package com.dingjh.log.logUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @ClassName: LogFile
 * @Description: 将日志写入硬类
 * 
 * @author dingjianhua
 * @date 2017年4月13日 下午3:17:37
 
 */
public class LogFile {
	private String fileName = null;
	private String filePath = null;

	public static void log(LogManager log, String saveFilePath)
			throws IOException {
		LogFile logFile = new LogFile();
		String fileName=log.getLogFileName()+" "+log.getLogTime().substring(0, 13)+ ".log";

		logFile.setFilePath(saveFilePath);
		logFile.setFileName(fileName);
		logFile.log(log.getFormatLog());
		logFile = null;
	}

	public void log(String logInfo) throws IOException {
		writeFile(filePath, fileName, logInfo);
	}

	public synchronized void writeFile(String filePath, String fileName,
			String logInfo) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(filePath + "/"
				+ fileName, true));
		bw.newLine();
		bw.append(logInfo);
		bw.flush();
		bw.close();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
