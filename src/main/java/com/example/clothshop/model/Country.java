package com.example.clothshop.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Table(name = "countries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "short_name", nullable = false)
    private String short_name;

    @Column(name = "full_name", nullable = false)
    private String full_name;

    @Column(name = "language_id", nullable = false)
    private String language_id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    @Column(name = "goods", nullable = false)
    private List<Goods> goods;



}
