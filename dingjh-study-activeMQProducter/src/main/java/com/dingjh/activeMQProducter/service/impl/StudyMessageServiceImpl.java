package com.dingjh.activeMQProducter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dingjh.activeMQProducter.producter.IMessageProducter;
import com.dingjh.activeMQProducter.queueMessModel.StudyQueueMessModel;
import com.dingjh.activeMQProducter.service.IStudyMessageService;

@Component(value="studyMessageService")
public class StudyMessageServiceImpl implements IStudyMessageService {
    @Autowired
    private IMessageProducter<StudyQueueMessModel> messageProducter;

    @Override
    public void sendNotice() {
        try {
            StudyQueueMessModel studyQueueMessModel = new StudyQueueMessModel("sendNotice");
            messageProducter.sendMessage(studyQueueMessModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
