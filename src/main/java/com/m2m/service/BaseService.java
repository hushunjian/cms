package com.m2m.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Long getPageStart(Long totalRecord, Long pageSize, Long pageIndex) {
        Long totalPage = (totalRecord + pageSize - 1) / pageSize;
        if (pageIndex > totalPage) {
            pageIndex = totalPage;
        }
        if (pageIndex < 1) {
            pageIndex = 1L;
        }
        Long start = (pageIndex - 1) * pageSize;
        return start;
    }
}
