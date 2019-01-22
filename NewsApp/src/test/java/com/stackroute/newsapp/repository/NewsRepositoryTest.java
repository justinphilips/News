package com.stackroute.newsapp.repository;

import com.stackroute.newsapp.domain.News;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;


import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class NewsRepositoryTest {

    @Autowired
    private NewsRepository newsRepository;


    @Test
    public void findByNewsId() throws Exception {
     long newsid = 1235;
       News newsData=  newsRepository.save(new News(newsid, "My own News", "Test", "localhost:8888", null, null, null));
        News news = newsRepository.findByNewsId(newsData.getNewsId());
        assertEquals("Test", news.getDescription());

    }
}
