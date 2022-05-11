package com.aiit.pojo.filmpojo;


import java.sql.Date;

public class Film {

  private long id;
  private String fNo;
  private String name;
  private String url;
  private String image;
  private Date datePublished;
  private String description;
  private String genre;
  public Film() {
  }

  public Film(String fNo, String name, Date datePublished) {
    this.fNo = fNo;
    this.name = name;
    this.datePublished = datePublished;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public String getfNo() {
    return fNo;
  }

  public void setfNo(String fNo) {
    this.fNo = fNo;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  @Override
  public String toString() {
    return "Film{" +
            "id=" + id +
            ", fNo='" + fNo + '\'' +
            ", name='" + name + '\'' +
            ", url='" + url + '\'' +
            ", image='" + image + '\'' +
            ", datePublished=" + datePublished +
            ", description='" + description + '\'' +
            ", genre='" + genre + '\'' +
            '}';
  }
}
