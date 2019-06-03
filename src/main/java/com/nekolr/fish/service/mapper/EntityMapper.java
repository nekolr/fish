package com.nekolr.fish.service.mapper;


/**
 * 实体映射接口
 *
 * @param <E> entity 类型
 * @param <D> dto 类型
 */
public interface EntityMapper<E, D> {

    /**
     * 将 entity 映射为 dto
     *
     * @param entity 类型
     * @return
     */
    D toDto(E entity);

    /**
     * 将 dto 映射为 entity
     *
     * @param dto
     * @return
     */
    E toEntity(D dto);
}
