package com.example.photoapp.repositories;

import com.example.photoapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    // Aqui você pode adicionar métodos adicionais para consultas específicas do usuário, se necessário
}
