package com.itcast.ssm.service.impl;

import com.itcast.ssm.dao.ISysLogDao;
import com.itcast.ssm.domain.SysLog;
import com.itcast.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ISysLogServiceImpl implements ISysLogService {
    @Autowired
    private ISysLogDao iSysLogDao;
    @Override
    public void save(SysLog sysLog) {
        iSysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() {
        return iSysLogDao.findAll();
    }
}
