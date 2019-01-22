package com.stackroute.newsapp.domain;

import javax.persistence.*;


@Entity
public class News {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long newsId;

  private String title;
  @Column(length = 1000)
  private String description;
  @Column(length = 1000)
  private String url;
  @Column(length = 1000)
  private String imageUrl;
  private String publishedDate;
  @Column(length = 5000)
  private String content;

  public News(Long newsId, String title, String description, String url, String imageUrl, String publishedDate, String content) {
    this.newsId = newsId;
    this.title = title;
    this.description = description;
    this.url = url;
    this.imageUrl = imageUrl;
    this.publishedDate = publishedDate;
    this.content = content;
  }


  public News() {
  }

  public Long getNewsId() {
    return newsId;
  }

  public void setNewsId(Long newsId) {
    this.newsId = newsId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getPublishedDate() {
    return publishedDate;
  }

  public void setPublishedDate(String publishedDate) {
    this.publishedDate = publishedDate;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }



}
