package com.jpa;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="MEMBER")
public class Member {

    @Id
    @Column(name="ID")
    private String id;

    @Column(name="NAME")
    private String username;

    private Integer age;

    @Enumerated
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
