package com.example.demo.repo;
import com.example.demo.models.Company;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CompanyRepository extends CrudRepository<Company, Long>{
   Company findByNamecompanyContains(String namecompany);
}
