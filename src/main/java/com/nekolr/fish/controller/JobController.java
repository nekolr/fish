package com.nekolr.fish.controller;

import com.nekolr.fish.entity.Job;
import com.nekolr.fish.exception.BadRequestException;
import com.nekolr.fish.log.annotation.Log;
import com.nekolr.fish.service.JobService;
import com.nekolr.fish.service.dto.JobDTO;
import com.nekolr.fish.support.I18nUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private I18nUtils i18nUtils;

    @Log("获取职务列表")
    @GetMapping("/jobs")
    @PreAuthorize("hasAnyAuthority('JOB_ALL', 'JOB_SELECT')")
    public ResponseEntity<List<JobDTO>> getJobs() {
        return new ResponseEntity(jobService.findAll(), HttpStatus.OK);
    }

    @Log("获取职务信息")
    @GetMapping("/jobs/{id}")
    @PreAuthorize("hasAnyAuthority('JOB_ALL', 'JOB_SELECT')")
    public ResponseEntity<JobDTO> getJob(@PathVariable String id) {
        if (NumberUtils.isDigits(id)) {
            return new ResponseEntity(jobService.findById(Long.valueOf(id)), HttpStatus.OK);
        } else {
            throw new BadRequestException(i18nUtils.getMessage("exceptions.bad_request"));
        }
    }

    @Log("创建职务")
    @PostMapping("/jobs")
    @PreAuthorize("hasAnyAuthority('JOB_ALL', 'JOB_POST')")
    public ResponseEntity<JobDTO> createJob(@Validated @RequestBody Job job) {
        JobDTO entity = jobService.saveJob(job);
        return ResponseEntity.ok(entity);
    }

    @Log("更新职务信息")
    @PutMapping("/jobs")
    @PreAuthorize("hasAnyAuthority('JOB_ALL', 'JOB_PUT')")
    public ResponseEntity<JobDTO> updateJob(@Validated @RequestBody Job job) {
        JobDTO result = jobService.updateJob(job);
        return ResponseEntity.ok(result);
    }

    @Log("删除职务")
    @DeleteMapping("/jobs/{id}")
    @PreAuthorize("hasAnyAuthority('JOB_ALL', 'JOB_DELETE')")
    public ResponseEntity deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
