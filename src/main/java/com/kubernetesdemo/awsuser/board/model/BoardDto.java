package com.kubernetesdemo.awsuser.board.model;

import com.kubernetesdemo.awsuser.article.model.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component //Object 같은거
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private String description;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private List<Article> articles;
}
