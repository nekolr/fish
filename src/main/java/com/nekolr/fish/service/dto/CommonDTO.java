package com.nekolr.fish.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author nekolr
 */
@Getter
@Setter
public class CommonDTO implements Serializable {

    Long id;

    Long pid;

    List<?> children;
}
