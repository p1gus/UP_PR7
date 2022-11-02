package com.example.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "directors")
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 255, message = "От 1 до 255 символов")
    private String namedirector;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "director")
    private Company company;

    private int views;

    public Director (Long id, String namedirector, Company company) {
        this.id = id;
        this.namedirector = namedirector;
        this.company = company;
    }
    public Director(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getNamedirector() {
        return namedirector;
    }

    public void setNamedirector(String namedirector) {
        this.namedirector = namedirector;
    }
}
