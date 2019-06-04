package com.nekolr.fish.controller;

import com.nekolr.fish.log.annotation.Log;
import com.nekolr.fish.service.JobService;
import com.nekolr.fish.service.dto.JobDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 职务控制器
 *
 * @author nekolr
 */
@RestController
@RequestMapping("api")
public class JobController {

    @Autowired
    private JobService jobService;

    @Log("获取职务列表")
    @GetMapping("/jobs")
    @PreAuthorize("hasAnyAuthority('JOB_ALL', 'JOB_SELECT')")
    public ResponseEntity<List<JobDTO>> getJobs() {
        return new ResponseEntity(jobService.findAll(), HttpStatus.OK);
    }
}
