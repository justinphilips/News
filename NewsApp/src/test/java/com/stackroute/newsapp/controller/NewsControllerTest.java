package com.stackroute.newsapp.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.domain.WishList;
import com.stackroute.newsapp.exception.NewsNotFoundException;
import com.stackroute.newsapp.service.NewsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(NewsController.class)
public class NewsControllerTest {

    @Autowired
    private transient MockMvc mockMvc;

    @MockBean
    private transient NewsService newsService;

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
    public void getParticularNew() throws Exception, NewsNotFoundException {

        long newsID = 1234;
        when(newsService.getParticularNew(newsID)).thenReturn(news);

        MvcResult result = mockMvc.perform(get(
                "/news/1234").accept(
                MediaType.APPLICATION_JSON)
                .header("Content-type",MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();



        String title = JsonPath.parse(result.getResponse().getContentAsString()).read("$.title");
        assertEquals(title,news.getTitle());
    }

    @Test
    public void getAllNews() throws Exception {

        List<News> newsArray = new ArrayList();

        newsArray.add(news);
        when(newsService.getNewsList()).thenReturn(newsArray);


        MvcResult result = mockMvc.perform(get(
                "/news").accept(
                MediaType.APPLICATION_JSON)
                .header("Content-type",MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();



        String newsData = result.getResponse().getContentAsString();
        DocumentContext context = JsonPath.parse(newsData);
        int length = context.read("$.length()");
        assertEquals(length ,newsArray.size());
    }

    @Test
    public void createNews() throws Exception {

        given(newsService.createNews(Mockito.any(News.class))).willReturn(news);

        MvcResult result = mockMvc.perform(post(
                "/news")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonToString(news)))
                .andExpect(status().isOk())
                .andReturn();
        String title = JsonPath.parse(result.getResponse().getContentAsString()).read("$.title");
        assertEquals(title,news.getTitle());
    }

    private String jsonToString(final Object obj1) throws JsonProcessingException {
        String result;
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String message = mapper.writeValueAsString(obj1);
            result = message;
        } catch (JsonProcessingException e) {
            result = "Json processing error";
        }

        return result;
    }

    @Test
    public void updateNews() throws Exception {
        long newsID = 1234;
        given(newsService.updateNews(Mockito.anyLong(), Mockito.any(News.class))).willReturn(news);

        MvcResult result = mockMvc.perform(put(
                "/news/1234")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(news)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(news.getTitle()))
                .andReturn();
    }

    @Test
    public void deleteNews() throws Exception {
        long newsID = 1234;
        given(newsService.deleteNews(Mockito.anyLong())).willReturn("Success");

        MvcResult result = mockMvc.perform(delete(
                "/news/1234")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(news)))
                .andExpect(status().isOk())
                .andReturn();
    }



    @Test
    public void getAllWishListOfUser() throws Exception {

        List<News> wishListArray = new ArrayList();

        wishListArray.add(news);
        when(newsService.getAllWishListOfUser("UserID")).thenReturn(wishListArray);


        MvcResult result = mockMvc.perform(get(
                "/news/wishList/UserID").accept(
                MediaType.APPLICATION_JSON)
                .header("Content-type",MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();



        String newsData = result.getResponse().getContentAsString();
        DocumentContext context = JsonPath.parse(newsData);
        int length = context.read("$.length()");
        assertEquals(length ,wishListArray.size());
    }

    @Test
    public void addToWishList() throws Exception {

        given(newsService.addToWishList(Mockito.anyString(), Mockito.anyLong())).willReturn(news);

        MvcResult result = mockMvc.perform(post(
                "/news/addToWishList/1234")
                .header("userID", "UserId")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void removeFromWishList() throws Exception {

        given(newsService.removeFromWishList(Mockito.anyString(), Mockito.anyLong())).willReturn("Success");

        MvcResult result = mockMvc.perform(delete(
                "/news/removeFromWishList/1234")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("userID", "UserId"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
