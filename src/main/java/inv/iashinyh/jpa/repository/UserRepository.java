package inv.iashinyh.jpa.repository;

import inv.iashinyh.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findOneByUsername(String username);
    Boolean existsByUsername(String username);
}
