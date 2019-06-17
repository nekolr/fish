package com.nekolr.fish.service.query;

import com.nekolr.fish.dao.UserRepository;
import com.nekolr.fish.entity.User;
import com.nekolr.fish.service.dto.UserDTO;
import com.nekolr.fish.service.mapper.UserMapper;
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
@CacheConfig(cacheNames = "user")
public class UserQueryService {

    @Autowired
    private UserRepository userRepository;
    @Resource
    private UserMapper userMapper;

    @Cacheable(keyGenerator = "keyGenerator")
    public PageVO queryAll(UserDTO user, Pageable pageable) {
        Page<User> users = userRepository.findAll(new Spec(user), pageable);
        return PageUtils.toPageVO(users.map(userMapper::toDto));
    }

    class Spec implements Specification<User> {

        private UserDTO user;

        public Spec(UserDTO user) {
            this.user = user;
        }

        @Override
        public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> predicates = new ArrayList<>();

            if (ObjectUtils.isNotEmpty(user.getId())) {
                // id 相等
                predicates.add(cb.equal(root.get("id").as(Long.class), user.getId()));
            }

            if (StringUtils.isNotBlank(user.getUsername())) {
                // 模糊匹配 username
                predicates.add(cb.like(root.get("username").as(String.class), "%" + user.getUsername() + "%"));
            }

            if (StringUtils.isNotBlank(user.getEmail())) {
                // 模糊匹配 email
                predicates.add(cb.like(root.get("email").as(String.class), "%" + user.getEmail() + "%"));
            }

            if (StringUtils.isNotBlank(user.getPhone())) {
                // 模糊匹配 phone
                predicates.add(cb.like(root.get("phone").as(String.class), "%" + user.getPhone() + "%"));
            }

            if (ObjectUtils.isNotEmpty(user.getEnabled())) {
                // enabled 相等
                predicates.add(cb.equal(root.get("enabled").as(Boolean.class), user.getEnabled()));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }
    }
}
