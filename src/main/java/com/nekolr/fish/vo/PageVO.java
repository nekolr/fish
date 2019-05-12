package com.nekolr.fish.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVO<T> implements Serializable {

    private T data;

    private Long totalSize;
}
