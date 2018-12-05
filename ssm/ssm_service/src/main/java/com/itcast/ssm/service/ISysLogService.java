package com.itcast.ssm.service;

import com.itcast.ssm.domain.SysLog;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ISysLogService {
    public abstract void save(SysLog sysLog);
    public abstract List<SysLog> findAll();
}
