package com.kubernetesdemo.awsuser.board.model;

import com.kubernetesdemo.awsuser.article.model.Article;
import com.kubernetesdemo.awsuser.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name="boards")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String title;
    private String content;
    @Setter
    private String description;

    @OneToMany(mappedBy ="board", cascade = CascadeType.ALL)
    private List<Article> articles;

}
