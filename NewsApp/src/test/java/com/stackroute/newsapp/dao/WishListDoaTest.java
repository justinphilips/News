package com.stackroute.newsapp.dao;

import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.domain.WishList;
import com.stackroute.newsapp.repository.WishListRepository;
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

public class WishListDoaTest {

    @Mock
    private WishListRepository wishListRepository;

    @Mock
    private NewsDao newsDao;

    @InjectMocks
    private WishListDoa wishListDoa;

    private transient News news;

    private transient WishList wishList;

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        long newsID = 1234;
        long wishListID = 9874;


        this.news = new News(newsID, "Title", "Description", "News URL", "Image URl", "publishedDate", "News Content");
        this.wishList = new WishList(wishListID, "UserID", newsID);
    }


    @Test
    public void removeFromWishList() throws Exception {

        long newsID = 1234;
        when(newsDao.getNewsWithId(newsID)).thenReturn(news);
        doNothing().when(wishListRepository).removeNews(Mockito.anyLong(), Mockito.anyString());
        String tempWishList = wishListDoa.removeFromWishList(newsID, "userId");
        assertEquals("Success",tempWishList);
    }



    @Test
    public void getAllWishListOfUser() throws Exception {

        List<WishList> wishListArray = new ArrayList();

        wishListArray.add(wishList);
        String userId = "UserId";
        when(wishListRepository.findByUserId(Mockito.anyString())).thenReturn(wishListArray);
        List<News> tempWishList = wishListDoa.getAllWishListOfUser(userId);
        assertEquals(wishListArray.size(),tempWishList.size());
    }

}
