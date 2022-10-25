package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.WorkFor;

public interface WorkForService {
    WorkFor save(WorkFor workFor);

    WorkFor update(Integer id, WorkFor workFor);

    WorkFor getById(Integer id);
}
