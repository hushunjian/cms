package com.m2m.domain;

import java.util.Date;

public class TopicNews {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column topic_news.id
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column topic_news.topic_id
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    private Long topicId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column topic_news.content
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column topic_news.type
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column topic_news.create_time
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column topic_news.id
     *
     * @return the value of topic_news.id
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column topic_news.id
     *
     * @param id the value for topic_news.id
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column topic_news.topic_id
     *
     * @return the value of topic_news.topic_id
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public Long getTopicId() {
        return topicId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column topic_news.topic_id
     *
     * @param topicId the value for topic_news.topic_id
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column topic_news.content
     *
     * @return the value of topic_news.content
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column topic_news.content
     *
     * @param content the value for topic_news.content
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column topic_news.type
     *
     * @return the value of topic_news.type
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column topic_news.type
     *
     * @param type the value for topic_news.type
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column topic_news.create_time
     *
     * @return the value of topic_news.create_time
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column topic_news.create_time
     *
     * @param createTime the value for topic_news.create_time
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}