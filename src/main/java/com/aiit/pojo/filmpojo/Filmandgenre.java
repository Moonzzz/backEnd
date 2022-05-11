package com.aiit.pojo.filmpojo;


public class Filmandgenre {

  private long id;
  private long fId;
  private long gId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getFId() {
    return fId;
  }

  public void setFId(long fId) {
    this.fId = fId;
  }


  public long getGId() {
    return gId;
  }

  public void setGId(long gId) {
    this.gId = gId;
  }

  @Override
  public String toString() {
    return "Filmandgenre{" +
            "id=" + id +
            ", fId=" + fId +
            ", gId=" + gId +
            '}';
  }
}
