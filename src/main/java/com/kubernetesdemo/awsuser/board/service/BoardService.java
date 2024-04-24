package com.kubernetesdemo.awsuser.board.service;

import com.kubernetesdemo.awsuser.board.model.Board;
import com.kubernetesdemo.awsuser.board.model.BoardDto;
import com.kubernetesdemo.awsuser.common.service.CommandService;
import com.kubernetesdemo.awsuser.common.service.QueryService;

public interface BoardService extends CommandService<BoardDto>, QueryService<BoardDto> {

    default Board dtoToEntity(BoardDto dto){    //dto 를 entity로 바꾸는 것
        return Board.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .description(dto.getDescription())
                .build();
    }

    default BoardDto entityToDto(Board ent){ //entity 를 dto로 바꾸는 것
        return BoardDto.builder()
                .id(ent.getId())
                .title(ent.getTitle())
                .content(ent.getContent())
                .description(ent.getDescription())
                .build();
    }

}
