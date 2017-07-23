package com.dingjh.activeMQProducter.queueMessModel;

/**
 * 
 * @ClassName: StudyQueueMessModel
 * @Description: 消息模型
 * 
 * @author dingjianhua
 * @date 2017年4月17日 下午4:36:36
 
 */
public class StudyQueueMessModel{
    private String methodName;
    
    public StudyQueueMessModel() {
        super();
    }
    
    public StudyQueueMessModel(String methodName) {
        super();
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

}
