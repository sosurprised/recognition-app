package br.com.fiap.RecognitionApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.RecognitionApp.model.Measures;

public interface MeasuresRepository extends JpaRepository<Measures, Long> {

}
