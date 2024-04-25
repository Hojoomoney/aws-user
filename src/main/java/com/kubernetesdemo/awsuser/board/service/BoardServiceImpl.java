package com.kubernetesdemo.awsuser.board.service;

import com.kubernetesdemo.awsuser.board.model.Board;
import com.kubernetesdemo.awsuser.board.model.BoardDto;
import com.kubernetesdemo.awsuser.board.repository.BoardRepository;
import com.kubernetesdemo.awsuser.common.component.Messenger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository repository;


    @Override
    public Messenger save(BoardDto t) throws SQLException {
        repository.save(dtoToEntity(t));
        return Messenger.builder()
                .message(repository.existsById(t.getId()) ? "SUCCESS" : "FAILURE")
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
    public Messenger modify(BoardDto boardDto) {
        Board board = repository.findById(boardDto.getId()).orElseThrow(null);
        board.setTitle(boardDto.getTitle());
        board.setDescription(boardDto.getDescription());
        repository.save(board);
        return Messenger.builder()
                .message(repository.existsById(board.getId()) ? "SUCCESS" : "FAILURE")
                .build();
    }

    @Override
    public List<BoardDto> findAll() throws SQLException {
        return repository.findAllByOrderByContent().stream().map(this::entityToDto).toList();
    }

    @Override
    public Optional<BoardDto> findById(Long id) {
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
}
