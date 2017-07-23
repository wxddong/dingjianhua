package com.dingjh.log.logUtil;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.dingjh.config.SystemConfig;

@Component
public class LogManager {
    @Autowired
    private  ILogFile_Do logFile_Do;

    private static LogManager logManager;  
    @PostConstruct  
    public void init() {
        logManager = this; 
    }  
    
	//日志的级别
	public static final String LEVEL_INFO = "INFO";
	public static final String LEVEL_ERROR = "ERROR";
	public static final String LEVEL_EXCEPTION = "EXCEPTION";
	
	//日志文件名
//	public static final String FILE_INFO = "NAVIGATE_SERVICE_"+TicketDictionary.LOCAL_IP;
	public static final String FILE_ERROR = "ERROR_";
	public static final String FILE_EXCEPTION = "EXCEPTION";
	
    
	private String logTime = null;//记录时间
	private String logLevel = null;//日志级别
	private String logFileName = null;//日志文件名
	private String className = null;//类名
	private String methodName = null;//方法名
	private String logInfo = null;//日志内容
	
	public String getFormatLog() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.logTime);
		sb.append(", ");
		sb.append(this.logLevel);
		sb.append(", ");
		sb.append(this.className);
		sb.append(", ");
        sb.append(this.methodName);
		sb.append(", ");
		sb.append(this.logInfo);
		return sb.toString();
	}

	public void log() {
		try {
			log2Device(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void log2Device(LogManager log) throws IOException {
		String saveFilePath = SystemConfig.getConfig("log_file_path")+log.getLogTime().substring(0, 10);
		if(!new File(saveFilePath).exists())//指定的路径或着指定的目录文件是否已经存在
		{
			try {
			    this.logFile_Do.createFile(saveFilePath);//创建日志目录
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        LogFile.log(log, saveFilePath); //创建并记录日志文件 
	}

	public static void log(String level, String logFileName,String className,String methodName,
			String logInfo) {
//		LogManager logManager = new LogManager();
	    //赋值，this的值也会发送改变
	    logManager.logTime = String.valueOf(new Timestamp(System.currentTimeMillis()));
	    logManager.logLevel = level;
	    logManager.logFileName = logFileName;
	    logManager.className = className;
	    logManager.methodName = methodName;
	    logManager.logInfo = logInfo;
	    logManager.log();
	}
	
	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public String getLogFileName() {
        return logFileName;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

	public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }

}
