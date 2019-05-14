package com.nekolr.fish.dao;

import com.nekolr.fish.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author nekolr
 */
public interface LogRepository extends JpaRepository<Log, Long>, JpaSpecificationExecutor {

}
