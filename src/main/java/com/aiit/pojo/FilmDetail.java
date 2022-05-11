package com.aiit.pojo;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @auther Mr Tang
 * @Date 2018/12/25 10:39
 */
@Component("filmDetail")
public class FilmDetail implements Comparable<FilmDetail>{
    private String id;
    private String name;
    private String image;

    @Override
    public int compareTo(FilmDetail o) {
        return o.getRatingValue().compareTo(this.getRatingValue());
    }

    private String datePublished;
    private String description;
    private String count;
    private String ratingValue;
    private String genre;

    public FilmDetail() {

    }

    public FilmDetail(String id, String name, String image, String datePublished, String description, String count, String ratingValue) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.datePublished = datePublished;
        this.description = description;
        this.count = count;
        this.ratingValue = ratingValue;
    }

    public FilmDetail(String id, String name, String image, String datePublished, String description, String count, String ratingValue, String genre) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.datePublished = datePublished;
        this.description = description;
        this.count = count;
        this.ratingValue = ratingValue;
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(String ratingValue) {
        this.ratingValue = ratingValue;
    }

    @Override
    public String toString() {
        return "FilmDetail{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", datePublished='" + datePublished + '\'' +
                ", description='" + description + '\'' +
                ", count='" + count + '\'' +
                ", ratingValue='" + ratingValue + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
