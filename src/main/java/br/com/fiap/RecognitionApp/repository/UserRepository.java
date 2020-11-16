package br.com.fiap.RecognitionApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.RecognitionApp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
