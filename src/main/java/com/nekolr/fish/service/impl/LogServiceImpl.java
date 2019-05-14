package com.nekolr.fish.service.impl;

import com.nekolr.fish.dao.LogRepository;
import com.nekolr.fish.entity.Log;
import com.nekolr.fish.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author nekolr
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveLog(Log log) {
        logRepository.save(log);
    }
}
