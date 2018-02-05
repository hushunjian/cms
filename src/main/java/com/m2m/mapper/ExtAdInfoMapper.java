package com.m2m.mapper;

import com.m2m.entity.ExtAdTag;

import java.util.List;

public interface ExtAdInfoMapper {

    List<ExtAdTag> findByBannerId(Long id);

}