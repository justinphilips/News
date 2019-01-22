package com.stackroute.newsapp.model;

import java.util.List;

public class NewsResponse {


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getTotalResults() {
    return totalResults;
  }

  public void setTotalResults(Integer totalResults) {
    this.totalResults = totalResults;
  }

  public List<Articles> getArticles() {
    return articles;
  }

  public void setArticles(List<Articles> articles) {
    this.articles = articles;
  }

  private String status;
  private Integer totalResults;
  private List<Articles> articles;

}
