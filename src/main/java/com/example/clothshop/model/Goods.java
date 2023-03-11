package com.example.clothshop.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.sql.In;


@Entity
@Data
@Table(name = "goods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "g_name", nullable = false)
    private  String name;

    @Column(name = "g_description", nullable = false)
    private String description;

    @Column(name = "g_cost", nullable = false)
    private String cost;

    @Column(name = "is_active")
    private Boolean is_active;

    @Column(name = "category_id", nullable = false)
    private Integer category_id;

    @Column(name = "country_id", nullable = false)
    private Integer country_id;

}
