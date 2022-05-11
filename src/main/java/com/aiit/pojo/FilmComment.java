package com.aiit.pojo;

import org.springframework.stereotype.Component;

/**
 * @auther Mr Tang
 * @Date 2018/12/28 10:26
 */
@Component("filmComment")
public class FilmComment {
    private String id;
    private String fName;//查询时要用
    private String mName;//查询时要用
    private String mImgsrc;
    private String fDescrip;


    private String fId;//插入数据时要用
    private String mId;//插入数据时要用
    private String image;
    private String video;
    private String date;
    private String viewNum;
    private String title;
    private String content;
    private String imgrealpath;


    public FilmComment() {
    }

    public FilmComment(String fId, String mId, String date, String title, String content) {
        this.fId = fId;
        this.mId = mId;
        this.date = date;
        this.title = title;
        this.content = content;
    }

    public FilmComment(String fId, String mId, String image, String date, String title, String content) {
        this.fId = fId;
        this.mId = mId;
        this.image = image;
        this.date = date;
        this.title = title;
        this.content = content;
    }

    public FilmComment(String fId, String mId, String image, String video, String date, String title, String content) {
        this.fId = fId;
        this.mId = mId;
        this.image = image;
        this.video=video;
        this.date = date;
        this.title = title;
        this.content = content;
    }

    public FilmComment(String id, String fName, String mName, String fId, String mId, String image, String date, String viewNum, String title, String content) {
        this.id = id;
        this.fName = fName;
        this.mName = mName;
        this.fId = fId;
        this.mId = mId;
        this.image = image;
        this.date = date;
        this.viewNum = viewNum;
        this.title = title;
        this.content = content;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getmImgsrc() {
        return mImgsrc;
    }

    public void setmImgsrc(String mImgsrc) {
        this.mImgsrc = mImgsrc;
    }


    public String getfDescrip() {
        return fDescrip;
    }

    public void setfDescrip(String fDescrip) {
        this.fDescrip = fDescrip;
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getImgrealpath() {
        return imgrealpath;
    }

    public void setImgrealpath(String imgrealpath) {
        this.imgrealpath = imgrealpath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getViewNum() {
        return viewNum;
    }

    public void setViewNum(String viewNum) {
        this.viewNum = viewNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "FilmComment{" +
                "id='" + id + '\'' +
                ", fName='" + fName + '\'' +
                ", mName='" + mName + '\'' +
                ", mImgsrc='" + mImgsrc + '\'' +
                ", fDescrip='" + fDescrip + '\'' +
                ", fId='" + fId + '\'' +
                ", mId='" + mId + '\'' +
                ", image='" + image + '\'' +
                ", video='" + video + '\'' +
                ", date='" + date + '\'' +
                ", viewNum='" + viewNum + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", imgrealpath='" + imgrealpath + '\'' +
                '}';
    }
}
