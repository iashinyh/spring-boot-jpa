package inv.iashinyh.jpa.service;

import inv.iashinyh.jpa.dto.User;
import inv.iashinyh.jpa.entity.RoleEntity;
import inv.iashinyh.jpa.entity.UserEntity;
import inv.iashinyh.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(entity -> convertUserEntityToDto(entity)).collect(Collectors.toList());
    }

    public User findOne(String username) {
        UserEntity userEntity = findUserEntity(username);

        return convertUserEntityToDto(userEntity);
    }

    public User create(User user) {
        if(userRepository.existsByUsername(user.getUsername()))
            throw new RuntimeException("User is already exists");

        UserEntity userEntity = userRepository.save(convertUserDtoToEntity(user));

        return convertUserEntityToDto(userEntity);
    }

    public User modify(String username, User user) {
        UserEntity userEntity = findUserEntity(username);
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setRoles(user.getRoles().stream().map(role -> convertRoleDtoToEntity(user.getUsername(), role)).collect(Collectors.toList()));

        return convertUserEntityToDto(userEntity);
    }

    public User delete(String username) {
        UserEntity userEntity = findUserEntity(username);

        userRepository.delete(userEntity);

        return convertUserEntityToDto(userEntity);
    }

    private UserEntity findUserEntity(String username) {
        Optional<UserEntity> optional = userRepository.findOneByUsername(username);
        optional.orElseThrow(() -> new RuntimeException("User not found"));

        return optional.get();
    }

    private User convertUserEntityToDto(UserEntity entity) {
        return User.builder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .roles(entity.getRoles().stream().map(role->role.getRole()).collect(Collectors.toList()))
                .build();

    }

    private UserEntity convertUserDtoToEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setRoles(user.getRoles().stream().map(role -> convertRoleDtoToEntity(user.getUsername(), role)).collect(Collectors.toList()));

        return userEntity;
    }

    private RoleEntity convertRoleDtoToEntity(String username, String role) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setUsername(username);
        roleEntity.setRole(role);

        return roleEntity;
    }
}
