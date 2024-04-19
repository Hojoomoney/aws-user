package com.kubernetesdemo.awsuser.user.model;

import com.kubernetesdemo.awsuser.article.model.Article;
import com.kubernetesdemo.awsuser.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
@ToString(exclude = {"id"})
@Entity(name="users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Setter
    private String password;
    private String email;
    private String name;
    @Setter
    private String phone;
    @Setter
    private String job;
    private String token;

    @OneToMany(mappedBy = "writer", fetch =FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Article> articles;
}



