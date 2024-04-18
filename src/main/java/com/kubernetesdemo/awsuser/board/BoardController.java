package com.kubernetesdemo.awsuser.board;

import com.kubernetesdemo.awsuser.board.model.BoardDto;
import com.kubernetesdemo.awsuser.board.service.BoardService;
import com.kubernetesdemo.awsuser.common.component.Messenger;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/boards")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService service;
    @PostMapping(path = "/save")
    public ResponseEntity<Messenger> save(BoardDto dto) throws SQLException {
        return ResponseEntity.ok(service.save(dto));
    }
    @DeleteMapping(path = "/delete")
    public ResponseEntity<Messenger> deleteById(@RequestParam Long id){
        return ResponseEntity.ok(service.deleteById(id));
    }
    @GetMapping(path = "/list")
    public ResponseEntity<List<BoardDto>> findAll() throws SQLException {
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping(path = "/detail")
    public ResponseEntity<Optional<BoardDto>> findById(@RequestParam("id") Long id){
        log.info("입력받은 정보 : {}", id );
        return ResponseEntity.ok(service.findById(id));
    }
    @GetMapping(path = "/count")
    public ResponseEntity<Long> count(){
        return ResponseEntity.ok(service.count());
    }
    @GetMapping(path = "/exists")
    public ResponseEntity<Boolean> existById(@RequestParam Long id){
        return ResponseEntity.ok(service.existById(id));
    }

    @PutMapping("/modify")
    public ResponseEntity<Messenger> modify(@RequestBody BoardDto boardDto) {
        log.info("입력받은 정보 : {}", boardDto );
        return ResponseEntity.ok(service.modify(boardDto));
    }
}
