package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.Permission;
import com.nthn.springbootthymeleaf.repository.PermissionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;


    public Permission getById(Integer id) {
        return (requireOne(id));
    }

    public List<Permission> getPermissions() {
        List<Permission> permissions = permissionRepository.findAll(Sort.by("id").ascending());
        permissions.forEach(permission -> {
            permission.setCountAccount(permission.getAccounts().size());
        });
        return permissions;
    }

    private Permission requireOne(Integer id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

//    public Page<PermissionDTO> query(PermissionQueryVO vO) {
//        throw new UnsupportedOperationException();
//    }
//
//    private PermissionDTO toDTO(Permission original) {
//        PermissionDTO bean = new PermissionDTO();
//        BeanUtils.copyProperties(original, bean);
//        return bean;
//    }
}
