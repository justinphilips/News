package com.stackroute.newsapp.service;

import com.stackroute.newsapp.HttpUtils.HttpClient;
import com.stackroute.newsapp.dao.NewsDao;
import com.stackroute.newsapp.dao.WishListDoa;
import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.domain.WishList;
import com.stackroute.newsapp.exception.NewsNotFoundException;
import com.stackroute.newsapp.model.NewsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

  @Autowired
  private NewsDao newsDao;

  @Autowired
  private WishListDoa wishListDoa;

  @Autowired
  private HttpClient httpClient;

  /**
   * Desc: To get the news list from database
   *
   * @return News List Object
   */
  @Override
  public List<News> getNewsList() {
    List<News> newsList = new ArrayList<>();
    try {
     newsList = newsDao.getAllNews();
      if (newsList.size() == 0){
        System.out.print("fecthing news list from external news API");
        NewsResponse newsData =  httpClient.getAllNewsFromServer();
        newsList = newsDao.storeNewsData(newsData);
      }
    } catch (Exception e){
      e.printStackTrace();

    }
    return newsList;
  }


  /**
   * Desc: To get the news object from database for given newsid
   *
   * @param id - news id
   * @return News object
   */
  @Override
  public News getParticularNew(Long id) throws NewsNotFoundException{

    return newsDao.getNewsWithId(id);
  }

  /**
   * Desc: To create the news in database
   *
   * @param news - news object
   * @return News object
   */
  @Override
  public News createNews(News news) {

    return newsDao.createNews(news);
  }

  /**
   * Desc: To update the news in database
   *
   * @param id - news id
   * @param news - news object
   * @return news - news object
   */
  @Override
  public News updateNews(Long id, News news) throws NewsNotFoundException {

    return newsDao.updateNews(id, news);
  }

  /**
   * Desc: To delete the news in database
   *
   * @param id - news id
   * @return string
   */
  @Override
  public String deleteNews(Long id) throws NewsNotFoundException {

    return newsDao.deleteNews(id);
  }

  /**
   * Desc: To add the news in user list database
   *
   * @param id - news id
   * @param userID - user id
   * @return news - news object
   */
  @Override
  public News addToWishList(String userID, Long id) throws NewsNotFoundException {

    return wishListDoa.addNewsToWishList(id, userID);
  }


  /**
   * Desc: To remove the news in user list database
   *
   * @param id - news id
   * @param userID - user id
   * @return news - news object
   */
  @Override
  public String removeFromWishList(String userID, Long id) throws NewsNotFoundException {

    return wishListDoa.removeFromWishList(id, userID);
  }



  /**
   * Desc: To get all the news in user list database
   *
   * @param userID - user id
   * @return news list
   */
  @Override
  public List<News> getAllWishListOfUser(String userID) throws NewsNotFoundException {

    return wishListDoa.getAllWishListOfUser(userID);
  }

}
