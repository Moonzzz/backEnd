package com.aiit.pojo;


import java.sql.Date;

public class Film {
    private  int id;
    private  int fNo;
    private  String name;
    private  String url;
    private  String image;
    private  Date datePublished;
    private  String description;

    public Film() {
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", fNo=" + fNo +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", image='" + image + '\'' +
                ", datePublished=" + datePublished +
                ", description='" + description + '\'' +
                '}';
    }

    public Film(int id, int fNo, String name, String url, String image, Date datePublished, String description) {
        this.id = id;
        this.fNo = fNo;
        this.name = name;
        this.url = url;
        this.image = image;
        this.datePublished = datePublished;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getfNo() {
        return fNo;
    }

    public void setfNo(int fNo) {
        this.fNo = fNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
