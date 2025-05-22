package com.coop.domain.post.repository;

import com.coop.domain.post.entity.Post;
import com.coop.domain.post.enums.PostCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p " +
            "WHERE :category IS NULL OR p.category = :category")
    Page<Post> findAllByCategory(Pageable pageable, @Param("category") PostCategory category);

    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% OR p.content LIKE %:keyword%")
    Page<Post> findByKeywordLike(Pageable pageable, @Param("keyword") String keyword);

    @Query(value = "SELECT * FROM post WHERE MATCH(title, content) AGAINST(:keyword IN NATURAL LANGUAGE MODE)",
            countQuery = "SELECT COUNT(*) FROM post WHERE MATCH(title, content) AGAINST(:keyword IN NATURAL LANGUAGE MODE)",
            nativeQuery = true)
    Page<Post> findByKeywordNatural(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "SELECT * FROM post WHERE MATCH(title, content) AGAINST (:keyword IN BOOLEAN MODE)",
            countQuery = "SELECT COUNT(*) FROM post WHERE MATCH(title, content) AGAINST(:keyword IN BOOLEAN MODE)",
            nativeQuery = true)
    Page<Post> findByKeywordBoolean(@Param("keyword") String keyword, Pageable pageable);
}