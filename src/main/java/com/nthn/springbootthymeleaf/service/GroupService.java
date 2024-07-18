package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.model.Group;

import java.util.List;

public interface GroupService {
    Group getGroupById(int id);
    List<Group> getAllGroups();
    List<Group> getGroupsByName(String name);
    Group saveGroup(Group group);
    void deleteGroup(int id);
}
