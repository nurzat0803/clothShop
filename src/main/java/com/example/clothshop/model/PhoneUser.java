package com.example.clothshop.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "phone_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PhoneUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer user_id;

    @Column(name = "phone_number", nullable = false)
    private Integer phone_number;

}
