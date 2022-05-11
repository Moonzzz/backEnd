package com.aiit.pojo;

import org.springframework.stereotype.Component;

/**
 * @auther Mr Tang
 * @Date 2018/12/24 21:49
 */
@Component("film")
public class Film {
    private String id;
    private String fNo;
    private String name;
    private String url;
    private String image;
    private String datePublished;
    private String description;
    private String genere;
    public Film() {
    }

    public Film(String name, String url, String image, String datePublished, String description) {
        this.name = name;
        this.url = url;
        this.image = image;
        this.datePublished = datePublished;
        this.description = description;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfNo() {
        return fNo;
    }

    public void setfNo(String fNo) {
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

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id='" + id + '\'' +
                ", fNo='" + fNo + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", image='" + image + '\'' +
                ", datePublished='" + datePublished + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
