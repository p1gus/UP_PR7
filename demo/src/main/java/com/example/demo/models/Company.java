package com.example.demo.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {
    public Company(Long id, String namecompany, Collection<Jornalisti> jornalisti, int views, Director director) {
        this.id = id;
        this.namecompany = namecompany;
        this.jornalisti = jornalisti;
        this.views = views;
        this.director = director;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 1, max = 255, message = "От 1 до 255 символов")
    private String namecompany;

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<Jornalisti> jornalisti;

    private int views;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "director_id")
    private Director director;



    public Company() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamecompany() {
        return namecompany;
    }

    public void setNamecompany(String namecompany) {
        this.namecompany = namecompany;
    }

    public Collection<Jornalisti> getJornalisti() {
        return jornalisti;
    }

    public void setJornalisti(Collection<Jornalisti> jornalisti) {
        this.jornalisti = jornalisti;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public void add(Company company2) {
    }
}
