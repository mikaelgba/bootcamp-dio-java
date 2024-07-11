package com.dio.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "hospitalclinics")
@Table(name = "hospitalclinics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class HospitalClinic {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String description;

    private double capMax;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Person> employees;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<HospitalSupplies> hospitalSupplies;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Equipment> equipments;

    private boolean active;

    private LocalDateTime created;

    private LocalDateTime updated;

}
