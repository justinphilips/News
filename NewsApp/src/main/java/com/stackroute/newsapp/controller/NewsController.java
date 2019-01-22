package com.stackroute.newsapp.controller;

import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.domain.WishList;
import com.stackroute.newsapp.exception.NewsNotFoundException;
import com.stackroute.newsapp.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class NewsController {

  @Autowired
  private NewsService newsService;

  /**
   * Desc: Get all the news
   *
   * @return List of News Object
   */
  @RequestMapping(method = RequestMethod.GET, value = "/news")
  public ResponseEntity<List<News>> getAllNews() {
    System.out.println("Inside Get all news Controller function");
    return new ResponseEntity<List<News>>(newsService.getNewsList(), HttpStatus.OK);
  }


  /**
   * Desc: Get a news
   * params newsids
   * @return News Object
   */
  @RequestMapping(method = RequestMethod.GET, value = "/news/{id}")
  public ResponseEntity<?> getParticularNew(@PathVariable Long id)  {
    System.out.println("Inside Get Particular New Controller function");
    try {
      return new ResponseEntity<News>(newsService.getParticularNew(id), HttpStatus.OK);
    } catch (NewsNotFoundException e) {
      System.out.println("Inside Get NewsNotFoundException for Particular New Controller function");
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
    }
  }

  /**
   * Desc: Create a news
   * params news objects
   * @return created News Object
   */
  @RequestMapping(method = RequestMethod.POST, value = "/news")
  public ResponseEntity<News> createNews(@RequestBody News news) {
    System.out.println("Inside create news Controller function");
    return new ResponseEntity<News>(newsService.createNews(news), HttpStatus.OK);
  }

  /**
   * Desc: Update a news
   * params news objects and newsid
   * @return updated News Object
   */
  @RequestMapping(method = RequestMethod.PUT, value = "/news/{id}")
  public ResponseEntity<?> updateNews(@RequestBody News news, @PathVariable Long id) {
    System.out.println("Inside updateNews Controller function");
    try {
      return new ResponseEntity<News>(newsService.updateNews(id, news), HttpStatus.OK);
    } catch (NewsNotFoundException e) {
      System.out.println("Inside NewsNotFoundException for updateNews Controller function");
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
    }
  }

  /**
   * Desc: Delete a news
   * params newsid
   * @return delete a given News Object
   */
  @RequestMapping(method = RequestMethod.DELETE, value = "/news/{id}")
  public ResponseEntity<String> deleteNews(@PathVariable Long id) {
    System.out.println("Inside deleteNews Controller function");
    try {
      return new ResponseEntity<String>(newsService.deleteNews(id), HttpStatus.OK);
    } catch (NewsNotFoundException e) {
      System.out.println("Inside  NewsNotFoundException for deleteNews  Controller function");
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
    }
  }

  /**
   * Desc: get all wishlist of user
   * params userid
   * @return List of News Object
   */
  @RequestMapping(method = RequestMethod.GET, value = "/news/wishList/{userId}")
  public ResponseEntity<?> getAllWishListOfUser(@PathVariable String userId)  {
    System.out.println("Inside getAllWishListOfUser Controller function");
    try {
      return new ResponseEntity<List<News>>(newsService.getAllWishListOfUser(userId), HttpStatus.OK);
    } catch (NewsNotFoundException e) {
      System.out.println("Inside NewsNotFoundException for getAllWishListOfUser Controller function");
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
    }
  }

  /**
   * Desc: add to wishlist of user
   * params userid and newsid
   * @return  News Object
   */
  @RequestMapping(method = RequestMethod.POST, value = "/news/addToWishList/{id}")
  public ResponseEntity<?> addToWishList(@RequestHeader String userID, @PathVariable Long id) {
    System.out.println("Inside add news to WishList Controller function");
    try {
      return new ResponseEntity<News>(newsService.addToWishList(userID, id), HttpStatus.OK);
    } catch (NewsNotFoundException e) {
      System.out.println("Inside NewsNotFoundException for addToWishList  Controller function");
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
    }
  }

  /**
   * Desc: delete from wishlist of user
   * params userid and newsid
   * @return  News Object
   */
  @RequestMapping(method = RequestMethod.DELETE, value = "/news/removeFromWishList/{id}")
  public ResponseEntity<String> removeFromWishList(@RequestHeader String userID, @PathVariable Long id) {
    System.out.println("Inside remove news From WishList Controller function");
    try {
      return new ResponseEntity<String>(newsService.removeFromWishList(userID, id), HttpStatus.OK);
    } catch (NewsNotFoundException e) {
      System.out.println("Inside NewsNotFoundException for removeFromWishList  Controller function");
      return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
    }
  }


}
