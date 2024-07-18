package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.entity.Permission;
import com.nthn.springbootthymeleaf.repository.AccountRepository;
import com.nthn.springbootthymeleaf.repository.PermissionRepository;
import com.nthn.springbootthymeleaf.service.PermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;
    
    @Autowired
    private AccountRepository accountRepository;
    
    private Permission requireOne(Integer id) {
        return permissionRepository.findById(id)
                                   .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
    
    @Override
    public Permission getPermissionById(int id) {
        return requireOne(id);
    }
    
    @Override
    public List<Permission> getAllPermission() {
        List<Permission> permissions = permissionRepository.findAll(Sort.by("id").ascending());
        permissions.forEach(permission -> {
            permission.setCountAccount(permission.getAccounts().size());
        });
        return permissions;
    }
    
    @Override
    public Permission addPermission(Permission permission) {
        return permissionRepository.save(permission);
    }
    
    @Override
    public Permission updatePermission(Integer id, Permission permission) {
        Permission permission1 = requireOne(id);
        BeanUtils.copyProperties(permission, permission1);
        permission1=permissionRepository.save(permission1);
        Permission finalPermission = permission1;
        permission1.getAccounts().forEach(account -> {
            account.setPermission(finalPermission);
            accountRepository.save(account);
        });
        return permission1;
    }
}
