package com.stackroute.newsapp.dao;

import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.model.Articles;
import com.stackroute.newsapp.model.NewsResponse;
import com.stackroute.newsapp.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.stackroute.newsapp.exception.NewsNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsDao {

  @Autowired
  NewsRepository newsRepository;


  public List<News> getAllNews(){
    List<News> newsList = newsRepository.findAll();
    return newsList;
  }

  public News getNewsWithId(Long id) throws NewsNotFoundException{
    News news =  newsRepository.findByNewsId(id);
    if(news != null) {
      return news;
    } else {
      throw new NewsNotFoundException("Data Not availabe for the newsid :" + id);
    }
  }

  public List<News> storeNewsData(NewsResponse newsData){

    List<Articles> article = newsData.getArticles();
    List<News> addedNews = new ArrayList<News>();
    for(int i=0; i< newsData.getArticles().size(); i++){
      System.out.println(article.get(i).getAuthor());
      News news = new News();
      news.setContent(article.get(i).getContent());
      news.setTitle(article.get(i).getTitle());
      news.setDescription(article.get(i).getDescription());
      news.setUrl(article.get(i).getUrl());
      news.setImageUrl(article.get(i).getUrlToImage());
      news.setPublishedDate(article.get(i).getPublishedAt());
      addedNews.add(newsRepository.save(news));
    }
 return addedNews;
  }

  public News createNews(News news){
    return newsRepository.save(news);

  }

  public News updateNews(Long id, News news) throws NewsNotFoundException{

    News newsToUpdate =  newsRepository.findByNewsId(id);
    if(newsToUpdate != null) {
      return newsRepository.save(news);
    } else {
      throw new NewsNotFoundException("Data Not availabe for the newsid :" + id);
    }
  }

  public String deleteNews(Long id) throws NewsNotFoundException{

    News news =  newsRepository.findByNewsId(id);
    if(news != null) {
      newsRepository.deleteById(id);
    } else {
      throw new NewsNotFoundException("Data Not availabe for the newsid :" + id);
    }
    return "Success";
  }
}
