package com.kubernetesdemo.awsuser.board.repository;


import com.kubernetesdemo.awsuser.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByContent();
}
