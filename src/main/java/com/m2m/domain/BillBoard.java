package com.m2m.domain;

import java.util.Date;

public class BillBoard {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column billboard.id
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column billboard.type
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column billboard.name
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column billboard.summary
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    private String summary;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column billboard.bg_color
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    private String bgColor;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column billboard.image
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    private String image;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column billboard.img_width
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    private Integer imgWidth;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column billboard.img_height
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    private Integer imgHeight;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column billboard.show_name
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    private Integer showName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column billboard.create_time
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column billboard.update_time
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column billboard.status
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column billboard.mode
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    private Integer mode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column billboard.id
     *
     * @return the value of billboard.id
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column billboard.id
     *
     * @param id the value for billboard.id
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column billboard.type
     *
     * @return the value of billboard.type
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column billboard.type
     *
     * @param type the value for billboard.type
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column billboard.name
     *
     * @return the value of billboard.name
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column billboard.name
     *
     * @param name the value for billboard.name
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column billboard.summary
     *
     * @return the value of billboard.summary
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public String getSummary() {
        return summary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column billboard.summary
     *
     * @param summary the value for billboard.summary
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column billboard.bg_color
     *
     * @return the value of billboard.bg_color
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public String getBgColor() {
        return bgColor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column billboard.bg_color
     *
     * @param bgColor the value for billboard.bg_color
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public void setBgColor(String bgColor) {
        this.bgColor = bgColor == null ? null : bgColor.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column billboard.image
     *
     * @return the value of billboard.image
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public String getImage() {
        return image;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column billboard.image
     *
     * @param image the value for billboard.image
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column billboard.img_width
     *
     * @return the value of billboard.img_width
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public Integer getImgWidth() {
        return imgWidth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column billboard.img_width
     *
     * @param imgWidth the value for billboard.img_width
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public void setImgWidth(Integer imgWidth) {
        this.imgWidth = imgWidth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column billboard.img_height
     *
     * @return the value of billboard.img_height
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public Integer getImgHeight() {
        return imgHeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column billboard.img_height
     *
     * @param imgHeight the value for billboard.img_height
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public void setImgHeight(Integer imgHeight) {
        this.imgHeight = imgHeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column billboard.show_name
     *
     * @return the value of billboard.show_name
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public Integer getShowName() {
        return showName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column billboard.show_name
     *
     * @param showName the value for billboard.show_name
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public void setShowName(Integer showName) {
        this.showName = showName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column billboard.create_time
     *
     * @return the value of billboard.create_time
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column billboard.create_time
     *
     * @param createTime the value for billboard.create_time
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column billboard.update_time
     *
     * @return the value of billboard.update_time
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column billboard.update_time
     *
     * @param updateTime the value for billboard.update_time
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column billboard.status
     *
     * @return the value of billboard.status
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column billboard.status
     *
     * @param status the value for billboard.status
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column billboard.mode
     *
     * @return the value of billboard.mode
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public Integer getMode() {
        return mode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column billboard.mode
     *
     * @param mode the value for billboard.mode
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public void setMode(Integer mode) {
        this.mode = mode;
    }
}