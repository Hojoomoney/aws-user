package com.kubernetesdemo.awsuser.article.repository;

import com.kubernetesdemo.awsuser.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {
    // JPQL default방식
    @Query("select a from articles a where a.board.id = :boardId order by a.id desc")
    public List<Article> getArticlesByBoardId(@Param("boardId") Long boardId);
//    @Query(value = "select * from articles a where a.board_id = :boardId", nativeQuery = true)
//    public List<Map<String, Object>> getQanArticles(@Param("boardId") Long boardId);


    //JPSQL Return Type DTO
//   String articleDtoMapping = "new com.kubernetesdemo.awsuser.article.model.ArticleDto(" +
//            "a.id, a.title, a.content, a.board_id, a.writer_id, " +
//           "a.regDate, a.modDate)";
//    @Query("select " + articleDtoMapping + " from articles a where a.board.id = :boardId")
//    public List<Article> getArticlesDTOsByBoardId(@Param("boardId") Long boardId);
    Boolean existsByTitle(String title);

    List<Article> findAllByOrderByIdDesc();
}
