package com.example.clothshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Table(name = "languages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "short_name", nullable = false)
    private String short_name;

    @Column(name = "full_name", nullable = false)
    private String full_name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "language_id")
    @Column(name = "category_good_list", nullable = false)
    private List<CategoryGood> categoryGoodList;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "language_id")
    @Column(name = "status_list", nullable = false)
    private List<Status> statusList;

}
