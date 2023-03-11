package com.example.clothshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private  Integer user_id;

    @Column(name = "status_id", nullable = false)
    private String status_id;

    @Column(name = "date_start", nullable = false)
    private String date_start;

    @Column(name = "date_change_last_start", nullable = false)
    private String date_change_last_start;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    @Column(name = "order_details", nullable = false)
    private List<OrderDetail> orderDetails;

}
