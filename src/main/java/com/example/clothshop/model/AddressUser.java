package com.example.clothshop.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "address_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "user_id", nullable = false)
    private Integer user_id;


    @Column(name = "address", nullable = false)
    private String address;
}
