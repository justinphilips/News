package com.stackroute.newsapp.service;

import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.domain.WishList;
import com.stackroute.newsapp.exception.NewsNotFoundException;

import java.util.List;

public interface NewsService {

  List<News> getNewsList();

  News getParticularNew(Long id) throws NewsNotFoundException;

  News createNews(News news);

  News updateNews(Long id, News news) throws NewsNotFoundException;

  String deleteNews(Long id) throws NewsNotFoundException;

  News addToWishList(String userID, Long id) throws NewsNotFoundException;

  String removeFromWishList(String userID, Long id) throws NewsNotFoundException;

  List<News> getAllWishListOfUser(String userId) throws NewsNotFoundException;

}
