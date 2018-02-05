package com.m2m.mapper;

import java.util.Date;

public interface ExtTagExhibitionMapper {
    int clear(Date date);

    int append();

    Date getMaxCreatedAt();
}

