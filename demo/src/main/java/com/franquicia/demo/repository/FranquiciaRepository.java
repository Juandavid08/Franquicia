package com.franquicia.demo.repository;

import com.franquicia.demo.model.Franquicia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranquiciaRepository extends MongoRepository<Franquicia, String> {
    Franquicia findByNombre(String nombre);
}
