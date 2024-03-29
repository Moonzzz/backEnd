package com.aiit.pojo.filmpojo;


import java.util.List;

public class Genre {

  private long id;
  private String name;
  private List<Film> films;


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

  public List<Film> getFilms() {
    return films;
  }

  public void setFilms(List<Film> films) {
    this.films = films;
  }

  @Override
  public String toString() {
    return "Genre{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", films=" + films +
            '}';
  }
}
