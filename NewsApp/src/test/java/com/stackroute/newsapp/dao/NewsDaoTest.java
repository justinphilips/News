package com.stackroute.newsapp.dao;

import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.repository.NewsRepository;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class NewsDaoTest {

    @Mock
    private NewsRepository newsRepository;

    @InjectMocks
    private NewsDao newsDao;

    private transient News news;


    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        long newsID = 1234;
        this.news = new News(newsID, "Title", "Description", "News URL", "Image URl", "publishedDate", "News Content");

    }

    @Test
    public void getAllNews() throws Exception {

        List<News> newsArray = new ArrayList();
        newsArray.add(news);

        when(newsRepository.findAll()).thenReturn(newsArray);
        List<News> tempNews = newsDao.getAllNews();
        assertEquals(newsArray.size(),tempNews.size());
    }

    @Test
    public void getNewsWithId() throws Exception {

        long newsID = 1234;

        when(newsRepository.findByNewsId(newsID)).thenReturn(news);
        News tempNews = newsDao.getNewsWithId(newsID);
        assertEquals(news.getTitle(),tempNews.getTitle());
    }

    @Test
    public void createNews() throws Exception {


        given(newsRepository.save(Mockito.any(News.class))).willReturn(news);
        News tempNews = newsDao.createNews(news);
        assertEquals(news.getContent(),tempNews.getContent());
    }

    @Test
    public void updateNews() throws Exception {

        long newsID = 1234;
        when(newsRepository.findByNewsId(newsID)).thenReturn(news);
        given(newsRepository.save(Mockito.any(News.class))).willReturn(news);
        News tempNews = newsDao.updateNews(newsID, news);
        assertEquals(news.getImageUrl(),tempNews.getImageUrl());
    }

    @Test
    public void deleteNews() throws Exception {

        long newsID = 1234;
        when(newsRepository.findByNewsId(newsID)).thenReturn(news);
        doNothing().when(newsRepository).deleteById(Mockito.anyLong());
        String tempNews = newsDao.deleteNews(newsID);
        assertEquals("Success",tempNews);
    }
}
