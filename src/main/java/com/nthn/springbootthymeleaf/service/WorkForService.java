package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.entity.WorkFor;

public interface WorkForService {
    WorkFor save(WorkFor workFor);

    WorkFor update(Integer id, WorkFor workFor);

    WorkFor getById(Integer id);
}
