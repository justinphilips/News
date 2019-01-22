package com.stackroute.newsapp.dao;

import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.domain.WishList;
import com.stackroute.newsapp.exception.NewsNotFoundException;
import com.stackroute.newsapp.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WishListDoa {

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private NewsDao newsDao;

    public News addNewsToWishList(Long id, String userId) throws NewsNotFoundException {

        News news =  newsDao.getNewsWithId(id);
        if(news != null) {
            WishList wishList = new WishList();
            wishList.setNewsId(id);
            wishList.setUserId(userId);
            WishList wishListData =  wishListRepository.checkwishList(id, userId);
            System.out.print("wishListData" + wishListData);
            if(wishListData == null){
                wishListRepository.save(wishList);
            } else {
                removeFromWishList(id, userId);
//                throw new NewsNotFoundException("News has been already added for the user newsid  :" + id);
            }
        } else {
            throw new NewsNotFoundException("Data Not availabe for the newsid :" + id);
        }
        return news;
    }

    public String removeFromWishList(Long id, String userId) throws NewsNotFoundException {

        News news =  newsDao.getNewsWithId(id);
        if(news != null) {
            wishListRepository.removeNews(id, userId);
        } else {
            throw new NewsNotFoundException("Data Not availabe for the newsid :" + id);
        }
        return "Success";
    }

    public List<News> getAllWishListOfUser(String userId) throws NewsNotFoundException {

        try {

          List<WishList> userWishList = wishListRepository.findByUserId(userId);
          List<News> newsList = new ArrayList<News>();
          for(WishList w :  userWishList) {
            newsList.add(newsDao.getNewsWithId(w.getNewsId()));

          }

           return newsList;
        } catch (Exception e) {
            throw new NewsNotFoundException("No WishList Avaliable for UserID :" + userId);
        }
    }
}
