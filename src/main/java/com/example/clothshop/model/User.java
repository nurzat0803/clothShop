package com.example.clothshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Data
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "u_login", nullable = false)
    private  String login;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "u_password", nullable = false)
    private String password;

    @Column(name = "birthday", nullable = false)
    private String birthday;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @Column(name = "orders", nullable = false)
    private List<Order> orders;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @Column(name = "address_user_list", nullable = false)
    private List<AddressUser> addressUserList;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @Column(name = "phone_user_list", nullable = false)
    private List<PhoneUser> phoneUserList;


}
