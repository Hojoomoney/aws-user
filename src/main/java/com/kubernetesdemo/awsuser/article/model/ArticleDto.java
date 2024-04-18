package com.kubernetesdemo.awsuser.article.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component //Object 같은거
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private Long writerId;
    private Long boardId;
}
