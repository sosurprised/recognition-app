package br.com.fiap.RecognitionApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.RecognitionApp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	 Optional<User>  findByEmail(String email);
}
