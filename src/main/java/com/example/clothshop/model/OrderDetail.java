package com.example.clothshop.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "order_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_id", nullable = false)
    private Integer order_id;
    @Column(name = "good_id", nullable = false)
    private Integer good_id;

    @Column(name = "od_cost", nullable = false)
    private Integer cost;
}
