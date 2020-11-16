package br.com.fiap.RecognitionApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.RecognitionApp.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
