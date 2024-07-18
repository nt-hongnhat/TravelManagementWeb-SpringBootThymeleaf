package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.entity.Permission;

import java.util.List;

public interface PermissionService {
    
    Permission getPermissionById(int id);
    
    List<Permission> getAllPermission();
    
    Permission addPermission(Permission permission);
    
    Permission updatePermission(Integer id, Permission permission);
    
}
