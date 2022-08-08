package com.jpa.domain;

import com.jpa.OrderStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "ACCOUNT_ID")
    private Long AccountId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    private OrderStatus status;
}
