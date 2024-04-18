package com.kubernetesdemo.awsuser.board.repository;


import com.kubernetesdemo.awsuser.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
