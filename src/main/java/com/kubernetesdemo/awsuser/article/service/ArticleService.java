package com.kubernetesdemo.awsuser.article.service;

import com.kubernetesdemo.awsuser.article.model.Article;
import com.kubernetesdemo.awsuser.article.model.ArticleDto;
import com.kubernetesdemo.awsuser.board.model.Board;
import com.kubernetesdemo.awsuser.board.repository.BoardRepository;
import com.kubernetesdemo.awsuser.common.service.CommandService;
import com.kubernetesdemo.awsuser.common.service.QueryService;

import java.util.List;

public interface ArticleService extends CommandService<ArticleDto>, QueryService<ArticleDto> {
    // 디폴트 메소드는 로직도 똑같을 때 선언

    List<ArticleDto> findArticlesByTitle(String title);
    List<ArticleDto> findArticlesByContent(String content);
    default Article dtoToEntity(ArticleDto dto, BoardRepository repository){    //dto 를 entity로 바꾸는 것
        return Article.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .board(repository.findById(dto.getBoardId()).orElseThrow(null))
                .build();
    }

    default ArticleDto entityToDto(Article ent){ //entity 를 dto로 바꾸는 것
        return ArticleDto.builder()
                .id(ent.getId())
                .title(ent.getTitle())
                .content(ent.getContent())
                .build();
    }
    List<ArticleDto> getArticlesByBoardId(Long boardId);
}
