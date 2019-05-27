package com.nekolr.fish.util;

import com.nekolr.fish.service.dto.MenuDTO;
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
public class MenuUtils {

    /**
     * 将列表转换成树形
     * <p>
     * 时间复杂度：O(N)
     *
     * @param menus 未形成树形结构的菜单列表
     * @return 形成树形结构的菜单列表
     */
    public static List<MenuDTO> menus2Tree(List<MenuDTO> menus) {
        if (menus != null) {
            long startTime = System.currentTimeMillis();
            Map<Long, List<MenuDTO>> map = new HashMap<>((int) (menus.size() / 0.75F + 1.0F));
            List<MenuDTO> result = new ArrayList<>();
            menus.forEach(menu -> {
                map.put(menu.getId(), map.get(menu.getId()) == null ? new ArrayList<>() : map.get(menu.getId()));
                menu.setChildren(map.get(menu.getId()));
                if (menu.getPid().equals(0L)) {
                    result.add(menu);
                } else {
                    map.put(menu.getPid(), map.get(menu.getPid()) == null ? new ArrayList<>() : map.get(menu.getPid()));
                    map.get(menu.getPid()).add(menu);
                    map.put(menu.getPid(), map.get(menu.getPid()));
                }
            });
            long endTime = System.currentTimeMillis();
            log.info("convert menu list to tree completed. total time: {} ms", (endTime - startTime));
            return result;
        }
        return null;
    }
}
