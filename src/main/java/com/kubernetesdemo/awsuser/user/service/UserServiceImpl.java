package com.kubernetesdemo.awsuser.user.service;

import com.kubernetesdemo.awsuser.common.component.JwtProvider;
import com.kubernetesdemo.awsuser.common.component.Messenger;
import com.kubernetesdemo.awsuser.user.model.User;
import com.kubernetesdemo.awsuser.user.model.UserDto;
import com.kubernetesdemo.awsuser.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final JwtProvider jwtProvider;

    @Override
    public Messenger save(UserDto t) throws SQLException {
        entityToDto((repository.save(dtoToEntity(t))));
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
//        return Messenger.builder()
//                .message("Success")
//                .build();
        // return Messenger.builder()
        //        .message(
        //            Stream.of(id)
        //            .filter(i -> userRepository.existsById(i))
        //            .peek(i -> userRepository.deleteById(i))
        //            .map(i -> "SUCCESS")
        //            .findAny()
        //            .orElseGet(() -> "FAILURE")
        //        )
        //        .build();
    }

    @Override
    public List<UserDto> findAll() throws SQLException {
        return repository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public Optional<UserDto> findById(Long id) {
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
    public Messenger modify(UserDto userDto) {
   //     User user = dtoToEntity(userDto);
        User user = repository.findById(userDto.getId()).orElseThrow(null);
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        user.setJob(userDto.getJob());
        repository.save(user);
        return Messenger.builder()
                .message(repository.existsById(user.getId()) ? "SUCCESS" : "FAILURE")
                .build();

        //return Messenger.builder()
        //        .message(
        //            findUserByUsername(userDto.getUsername()).stream()
        //            .peek(i -> i.setPassword(userDto.getPassword()))
        //            .peek(i -> i.setName(userDto.getName()))
        //            .peek(i -> i.setPhone(userDto.getPhone()))
        //            .peek(i -> i.setJob(userDto.getJob()))
        //            .peek(i -> userRepository.save(i))
        //            .map(i -> "SUCCESS").findAny()
        //            .orElseGet(() -> "FAILURE")
        //        )
        //        .build();
    }

    @Override
    public List<UserDto> findUsersByName(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'findUsersByName'");
    }

    @Override
    public List<UserDto> findUsersByJob(String job) {
        throw new UnsupportedOperationException("Unimplemented method 'findUsersByJob'");
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Messenger login(UserDto dto) {
//        Optional<User> user = Optional.ofNullable(findUserByUsername(dto.getUsername())
//                .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_FAILED)));

        boolean flag = repository.findByUsername(dto.getUsername()).get().getPassword().equals(dto.getPassword());

        return Messenger.builder()
                .message(flag ? "SUCCESS" : "FAILURE")
                .token(flag ? jwtProvider.createToken(dto) : "none")
                .build();
    }
}
