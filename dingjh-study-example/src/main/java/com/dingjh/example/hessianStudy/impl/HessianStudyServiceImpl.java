package com.dingjh.example.hessianStudy.impl;

import com.dingjh.example.hessianStudy.IHessianStudyService;
import com.dingjh.log.logUtil.LogManager;

public class HessianStudyServiceImpl implements IHessianStudyService {

    @Override
    public String hessianStudyTest(String param) {
        LogManager.log(LogManager.LEVEL_INFO,
                LogManager.LEVEL_INFO,this.getClass().getName(),"hessianStudyTest",
                "入参： "+ param);
        return param;
    }

}
