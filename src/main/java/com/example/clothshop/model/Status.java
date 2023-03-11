package com.example.clothshop.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "statuses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "s_name", nullable = false)
    private String name;

    @Column(name = "language_id", nullable = false)
    private Integer language_id;


}
