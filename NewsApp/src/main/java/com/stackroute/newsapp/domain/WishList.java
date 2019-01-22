package com.stackroute.newsapp.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long wishListId;

    public WishList(Long wishListId, String userId, Long newsId) {
        this.wishListId = wishListId;
        this.userId = userId;
        this.newsId = newsId;
    }

    public WishList() {

    }

    private String userId;

    private Long newsId;

    public Long getWishListId() {
        return wishListId;
    }

    public void setWishListId(Long wishListId) {
        this.wishListId = wishListId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

}
