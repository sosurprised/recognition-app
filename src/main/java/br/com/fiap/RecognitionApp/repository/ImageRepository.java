package br.com.fiap.RecognitionApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.RecognitionApp.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}