package com.stackroute.newsapp.repository;

import com.stackroute.newsapp.domain.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM WishList w WHERE w.userId = :userId AND w.newsId = :id")
    public void removeNews(@Param("id") Long id, @Param("userId") String userId);

    @Query(value = "SELECT w FROM WishList w WHERE w.userId = :userId AND w.newsId = :id")
    public WishList checkwishList(@Param("id") Long id, @Param("userId") String userId);

    @Query(value = "SELECT w FROM WishList w WHERE w.userId = :userId")
    public List<WishList> findByUserId(String userId);
}
