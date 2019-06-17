package com.nekolr.fish.service.query;

import com.nekolr.fish.dao.RoleRepository;
import com.nekolr.fish.entity.Role;
import com.nekolr.fish.service.dto.RoleDTO;
import com.nekolr.fish.service.mapper.RoleMapper;
import com.nekolr.fish.util.PageUtils;
import com.nekolr.fish.vo.PageVO;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "role")
public class RoleQueryService {

    @Autowired
    private RoleRepository roleRepository;
    @Resource
    private RoleMapper roleMapper;

    @Cacheable(keyGenerator = "keyGenerator")
    public PageVO queryAll(RoleDTO role, Pageable pageable) {
        Page<Role> roles = roleRepository.findAll(new Spec(role), pageable);
        return PageUtils.toPageVO(roles.map(roleMapper::toDto));
    }

    class Spec implements Specification<Role> {

        private RoleDTO role;

        public Spec(RoleDTO role) {
            this.role = role;
        }

        @Override
        public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> predicates = new ArrayList<>();

            if (ObjectUtils.isNotEmpty(role.getId())) {
                // id 相等
                predicates.add(cb.equal(root.get("id").as(Long.class), role.getId()));
            }

            if (StringUtils.isNotBlank(role.getName())) {
                // 模糊匹配 name
                predicates.add(cb.like(root.get("name").as(String.class), "%" + role.getName() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }
    }
}
