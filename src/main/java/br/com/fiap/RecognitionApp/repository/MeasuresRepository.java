package br.com.fiap.RecognitionApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.RecognitionApp.model.Measure;

public interface MeasuresRepository extends JpaRepository<Measure, Long> {

}
