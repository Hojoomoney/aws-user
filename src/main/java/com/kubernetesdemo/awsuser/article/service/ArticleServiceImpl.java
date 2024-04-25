package com.kubernetesdemo.awsuser.article.service;

import com.kubernetesdemo.awsuser.article.model.Article;
import com.kubernetesdemo.awsuser.article.model.ArticleDto;
import com.kubernetesdemo.awsuser.article.repository.ArticleRepository;
import com.kubernetesdemo.awsuser.board.repository.BoardRepository;
import com.kubernetesdemo.awsuser.common.component.Messenger;
import com.kubernetesdemo.awsuser.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository repository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Override
    public Messenger save(ArticleDto t) throws SQLException {
        Article ent = repository.save(dtoToEntity(t,boardRepository, userRepository));
        return Messenger.builder()
                .message(repository.existsByTitle(t.getTitle()) ? "SUCCESS"  : "FAILURE")
                .id(ent.getBoard().getId())
                .build();
    }

    @Override
    public Messenger deleteById(Long id) {
        repository.deleteById(id);
        return Messenger.builder()
                .message(repository.existsById(id) ? "FAILURE" : "SUCCESS")
                .build();
    }

    @Override
    public Messenger modify(ArticleDto articleDto) {
        Article article = repository.findById(articleDto.getId()).orElseThrow(null);
        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());
        repository.save(article);
        return Messenger.builder()
                .message(repository.existsById(article.getId()) ? "SUCCESS" : "FAILURE")
                .build();
    }

    @Override
    public List<ArticleDto> findAll() throws SQLException {
        return repository.findAllByOrderByIdDesc().stream().map(this::entityToDto).collect(Collectors.toList());
    }


    @Override
    public Optional<ArticleDto> findById(Long id) {
        return repository.findById(id).map(this::entityToDto);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public boolean existById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public List<ArticleDto> findArticlesByTitle(String title) {
        throw new UnsupportedOperationException("Unimplemented method 'updatePassword'");
    }

    @Override
    public List<ArticleDto> findArticlesByContent(String content) {
        throw new UnsupportedOperationException("Unimplemented method 'updatePassword'");
    }

    @Override
    public List<ArticleDto> getArticlesByBoardId(Long boardId) {
        return repository.getArticlesByBoardId(boardId).stream().map(this::entityToDto).toList();
    }

}
