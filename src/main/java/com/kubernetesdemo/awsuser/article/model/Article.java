package com.kubernetesdemo.awsuser.article.model;


import com.kubernetesdemo.awsuser.board.model.Board;
import com.kubernetesdemo.awsuser.common.BaseEntity;
import com.kubernetesdemo.awsuser.user.model.User;
import jakarta.persistence.*;
import lombok.*;

@Entity(name="articles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Article extends BaseEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Setter
   private String title;
   @Setter
   private String content;

   @ManyToOne
   @JoinColumn(name = "board_id")
   private Board board;

   @ManyToOne
   @JoinColumn(name = "writer_id")
   private User writer;

   public static Article of(String title, String content){
      Article article = new Article();
      article.title = title;
      article.content = content;
      return article;
   }

}
