package com.m2m.domain;

import java.util.Date;

public class LiveFavorite {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column live_favorite.id
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column live_favorite.topic_id
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    private Long topicId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column live_favorite.uid
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    private Long uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column live_favorite.create_time
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column live_favorite.id
     *
     * @return the value of live_favorite.id
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column live_favorite.id
     *
     * @param id the value for live_favorite.id
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column live_favorite.topic_id
     *
     * @return the value of live_favorite.topic_id
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public Long getTopicId() {
        return topicId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column live_favorite.topic_id
     *
     * @param topicId the value for live_favorite.topic_id
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column live_favorite.uid
     *
     * @return the value of live_favorite.uid
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public Long getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column live_favorite.uid
     *
     * @param uid the value for live_favorite.uid
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column live_favorite.create_time
     *
     * @return the value of live_favorite.create_time
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column live_favorite.create_time
     *
     * @param createTime the value for live_favorite.create_time
     *
     * @mbg.generated Thu Jan 18 10:54:32 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}