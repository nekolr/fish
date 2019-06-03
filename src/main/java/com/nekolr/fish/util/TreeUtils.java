package com.nekolr.fish.util;

import com.nekolr.fish.service.dto.CommonDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单工具类
 *
 * @author nekolr
 */
@Slf4j
public class TreeUtils {


    /**
     * 将列表转换成树形
     * <p>
     * 时间复杂度：O(N)
     *
     * @param dtoList 未形成树形结构的菜单列表
     * @return 形成树形结构的菜单列表
     */
    public static List<CommonDTO> toTree(List<CommonDTO> dtoList) {
        if (dtoList != null) {
            long startTime = System.currentTimeMillis();
            Map<Long, List<CommonDTO>> map = new HashMap<>((int) (dtoList.size() / 0.75F + 1.0F));
            List<CommonDTO> result = new ArrayList<>();
            dtoList.forEach(dto -> {
                map.put(dto.getId(), map.get(dto.getId()) == null ? new ArrayList<>() : map.get(dto.getId()));
                dto.setChildren(map.get(dto.getId()));
                if (dto.getPid().equals(0L)) {
                    result.add(dto);
                } else {
                    map.put(dto.getPid(), map.get(dto.getPid()) == null ? new ArrayList<>() : map.get(dto.getPid()));
                    map.get(dto.getPid()).add(dto);
                    map.put(dto.getPid(), map.get(dto.getPid()));
                }
            });
            long endTime = System.currentTimeMillis();
            log.info("convert list to tree completed. total time: {} ms", (endTime - startTime));
            return result;
        }
        return null;
    }
}
