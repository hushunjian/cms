package com.m2m.mapper;

import java.util.Date;

public interface ExtTagInHomeMapper {
    int clear(Date date);

    int append();

    Date getMaxCreatedAt();
}

