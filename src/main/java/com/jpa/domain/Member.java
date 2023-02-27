package com.jpa.domain;

import com.jpa.RoleType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="MEMBER", uniqueConstraints = {@UniqueConstraint(
        name = "NAME_AGE_UNIQUE",
        columnNames = {"NAME", "AGE"}
)})
public class Member extends BaseEntity{

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public Member(){

    }
    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID")
    private String id;

    @Column(name="NAME", nullable = false, length = 10)
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public List<Order> getOrders() {
        return orders;
    }

}
