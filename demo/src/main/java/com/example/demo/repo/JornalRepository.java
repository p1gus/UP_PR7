package com.example.demo.repo;
import com.example.demo.models.Jornalisti;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface JornalRepository extends CrudRepository<Jornalisti, Long> {
  Jornalisti findByFamContains(String fam);

  Jornalisti findByNameContains(String fam);
}
