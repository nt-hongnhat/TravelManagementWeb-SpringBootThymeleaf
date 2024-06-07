package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.model.Group;
import com.nthn.springbootthymeleaf.repository.GroupRepository;
import com.nthn.springbootthymeleaf.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Group getGroupById(int id) {
        return groupRepository.findById(id);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public List<Group> getGroupsByName(String name) {
        return List.of();
    }

    @Override
    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public void deleteGroup(int id) {

    }
}
