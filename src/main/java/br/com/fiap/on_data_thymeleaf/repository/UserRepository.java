package br.com.fiap.on_data_thymeleaf.repository;

import br.com.fiap.on_data_thymeleaf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
