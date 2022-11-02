package com.example.demo.repo;

import com.example.demo.models.Director;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DirRepository extends CrudRepository<Director, Long> {
    List<Director> findByNamedirectorContains(String namedirector);
}
