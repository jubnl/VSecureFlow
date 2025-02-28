package ch.jubnl.vsecureflow.backend.service;

import ch.jubnl.vsecureflow.backend.dto.UserDto;
import ch.jubnl.vsecureflow.backend.entity.User;
import ch.jubnl.vsecureflow.backend.mapper.UserMapper;
import ch.jubnl.vsecureflow.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends BaseService<User, UserDto, UserRepository, UserMapper> {
    public UserService(UserRepository repository, UserMapper mapper) {
        super(repository, mapper);
    }

    public Optional<UserDto> findByEmail(String email) {
        return this.repository.findByEmail(email).map(mapper::toDto);
    }
}
