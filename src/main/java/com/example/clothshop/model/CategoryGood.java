package com.example.clothshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Table(name = "category_goods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryGood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryGood_id")
    @Column(name = "languages", nullable = false)
    private List<Language> languages;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @Column(name = "goods", nullable = false)
    private List<Goods> goods;

}
