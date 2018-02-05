package com.m2m.request;

import com.m2m.Constant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class CmsAppConfig4UpdateLightBoxRequest extends CmsCredentialRequest implements Serializable {
    @NotNull
    private Long id;
    @NotNull
    private String image;

    private String mainText;

    private String subText;
    @NotNull
    @Pattern(regexp = Constant.COLOUR_REGEX, message = "请输入类似与[#FFFFFF]格式的颜色码")
    private String mainColor;
    @NotNull
    @Pattern(regexp = Constant.URL_REGEX, message = "请输入正确的URL地址")
    private String link;
    @NotNull
    private Date beginAt;
    @NotNull
    private Date endAt;
    @NotNull
    private Integer status;
}
