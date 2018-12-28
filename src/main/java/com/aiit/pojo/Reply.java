package com.aiit.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

/**
 *
 */
public class Reply {
    private  int id;
    private int memberId;
    private  String content;
    private  Integer likeNum;
    private Date datePublished;

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", content='" + content + '\'' +
                ", likeNum=" + likeNum +
                ", datePublished=" + datePublished +
                '}';
    }

    public Reply(int id, int memberId, String content, Integer likeNum, Date datePublished) {
        this.id = id;
        this.memberId = memberId;
        this.content = content;
        this.likeNum = likeNum;
        this.datePublished = datePublished;
    }

    public Reply() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }
}
