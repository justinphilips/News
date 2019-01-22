package com.stackroute.newsapp.service;

import com.stackroute.newsapp.HttpUtils.HttpClient;
import com.stackroute.newsapp.dao.NewsDao;
import com.stackroute.newsapp.dao.WishListDoa;
import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.domain.WishList;
import com.stackroute.newsapp.exception.NewsAlreadyExistException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

public class NewsServiceImplTest {

  @Mock
  private NewsDao newsDao;

  @Mock
  private WishListDoa wishListDoa;

  @Mock
  private HttpClient httpClient;


  private transient News news;

  private transient WishList wishList;

  @InjectMocks
  private NewsServiceImpl newsServiceImpl;

  @Before
  public void setupMock() {
    MockitoAnnotations.initMocks(this);
    long newsID = 1234;
    long wishListID = 9874;


    this.news = new News(newsID, "Title", "Description", "News URL", "Image URl", "publishedDate", "News Content");
    this.wishList = new WishList(wishListID, "UserID", newsID);
  }

  @Test
  public void getParticularNew() throws Exception, NewsAlreadyExistException {

    long newsID = 1234;

    when(newsDao.getNewsWithId(newsID)).thenReturn(news);
    News tempNews = newsServiceImpl.getParticularNew(newsID);
    assertEquals(news.getTitle(),tempNews.getTitle());
  }

  @Test
  public void getNewsList() throws Exception {

    List<News> newsArray = new ArrayList();

    newsArray.add(news);

    when(newsDao.getAllNews()).thenReturn(newsArray);
    List<News> tempNews = newsServiceImpl.getNewsList();
    assertEquals(newsArray.size(),tempNews.size());
  }

  @Test
  public void createNews() throws Exception {


    given(newsDao.createNews(Mockito.any(News.class))).willReturn(news);
    News tempNews = newsServiceImpl.createNews(news);
    assertEquals(news.getContent(),tempNews.getContent());
  }

  @Test
  public void updateNews() throws Exception {
    long newsID = 1234;

    given(newsDao.updateNews(Mockito.anyLong(), Mockito.any(News.class))).willReturn(news);
    News tempNews = newsServiceImpl.updateNews(newsID, news);
    assertEquals(news.getDescription(),tempNews.getDescription());
  }

  @Test
  public void deleteNews() throws Exception {
    long newsID = 1234;

    given(newsDao.deleteNews(Mockito.anyLong())).willReturn("Success");
    String tempNews = newsServiceImpl.deleteNews(newsID);
    assertEquals("Success",tempNews);
  }

  @Test
  public void addToWishList() throws Exception {

    long newsID = 1234;
    String userId = "UserId";
    given(wishListDoa.addNewsToWishList(Mockito.anyLong(), Mockito.anyString())).willReturn(news);
    News result = newsServiceImpl.addToWishList(userId, newsID);
    assertEquals(news,result);
  }

  @Test
  public void removeFromWishList() throws Exception {

    long newsID = 1234;
    String userId = "UserId";
    given(wishListDoa.removeFromWishList(Mockito.anyLong(), Mockito.anyString())).willReturn("Suucess");
    String result = newsServiceImpl.removeFromWishList(userId, newsID);
    assertEquals("Suucess",result);
  }

  @Test
  public void getAllWishListOfUser() throws Exception {

    List<News> wishListArray = new ArrayList();

    wishListArray.add(news);
    String userId = "UserId";
    when(wishListDoa.getAllWishListOfUser(Mockito.anyString())).thenReturn(wishListArray);
    List<News> tempWishList = newsServiceImpl.getAllWishListOfUser(userId);
    assertEquals(wishListArray.size(),tempWishList.size());
  }


}
