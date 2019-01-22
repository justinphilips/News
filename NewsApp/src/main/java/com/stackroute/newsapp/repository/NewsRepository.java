package com.stackroute.newsapp.repository;

import com.stackroute.newsapp.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {

  public News findByNewsId(Long id);
}
