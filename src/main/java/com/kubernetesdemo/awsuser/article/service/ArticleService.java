package com.kubernetesdemo.awsuser.article.service;

import com.kubernetesdemo.awsuser.article.model.Article;
import com.kubernetesdemo.awsuser.article.model.ArticleDto;
import com.kubernetesdemo.awsuser.board.model.Board;
import com.kubernetesdemo.awsuser.board.repository.BoardRepository;
import com.kubernetesdemo.awsuser.common.service.CommandService;
import com.kubernetesdemo.awsuser.common.service.QueryService;
import com.kubernetesdemo.awsuser.user.repository.UserRepository;

import java.util.List;

public interface ArticleService extends CommandService<ArticleDto>, QueryService<ArticleDto> {
    // 디폴트 메소드는 로직도 똑같을 때 선언

    List<ArticleDto> findArticlesByTitle(String title);
    List<ArticleDto> findArticlesByContent(String content);
    default Article dtoToEntity(ArticleDto dto, BoardRepository boardRepository, UserRepository userRepository){    //dto 를 entity로 바꾸는 것
        return Article.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .board(boardRepository.findById(dto.getBoardId()).orElseThrow(null))
                .writer(userRepository.findById(dto.getWriterId()).orElseThrow(null))
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
