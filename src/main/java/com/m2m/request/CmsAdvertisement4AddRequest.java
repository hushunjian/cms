package com.m2m.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsAdvertisement4AddRequest extends CmsCredentialRequest implements Serializable {
    @NotNull
    private String name;
    @NotNull
    private Long positionId;
    @NotNull
    private String cover;
    @NotNull
    private Integer coverWidth;
    @NotNull
    private Integer coverHeight;
    @NotNull
    private Integer probability;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date validAt;
    @NotNull
    private Integer type;
    private Long topicId;
    private String url;
}