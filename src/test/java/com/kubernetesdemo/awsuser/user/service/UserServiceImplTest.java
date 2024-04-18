package com.kubernetesdemo.awsuser.user.service;

import com.kubernetesdemo.awsuser.common.component.Messenger;
import com.kubernetesdemo.awsuser.user.model.User;
import com.kubernetesdemo.awsuser.user.model.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class UserServiceImplTest {
    @Autowired UserService service;
    @Test
    @DisplayName("가입한_회원_수")
    void count() {
        long expected = service.count();
        assertThat(expected).isEqualTo(15L);
    }

    @Test
    @DisplayName("회원가입")
    void save() throws SQLException {
        UserDto expected = UserDto.builder()
                .username("saveTest")
                .password("test1234")
                .build();
        service.save(expected);
        Optional<User> actual = service.findUserByUsername("saveTest");
        assertThat(actual.get().getUsername()).isEqualTo(expected.getUsername());
    }

    @Test
    @DisplayName("회원삭제")
    void deleteById() throws SQLException {
        UserDto expected = UserDto.builder()
                .username("deleteTest")
                .password("test1234")
                .build();
        service.save(expected);
        Optional<UserDto> user = service.findById(service.findUserByUsername("deleteTest").get().getId());
        Messenger messenger = service.deleteById(user.get().getId());
        assertThat(messenger.getMessage()).isEqualTo("Success");
    }

    @Test
    @DisplayName("회원전체_조회")
    void findAll() throws SQLException {
        List<UserDto> list = service.findAll();
        long count = service.count();
        assertThat(count).isEqualTo(list.size());
    }

    @Test
    @DisplayName("회원정보_수정")
    void modify() {
        UserDto userDto = UserDto.builder()
                            .id(1L)
                            .password("testPassword")
                            .phone("000-0000-0000")
                            .job("tester")
                            .build();

        service.modify(userDto);
        UserDto modified = service.findById(1L).get();
        assertThat(userDto.getPassword()).isEqualTo(modified.getPassword());
        assertThat(userDto.getPhone()).isEqualTo(modified.getPhone());
        assertThat(userDto.getJob()).isEqualTo(modified.getJob());
    }
}