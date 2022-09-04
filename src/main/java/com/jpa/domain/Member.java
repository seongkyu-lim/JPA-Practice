package com.jpa.domain;

import com.jpa.RoleType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="MEMBER", uniqueConstraints = {@UniqueConstraint(
        name = "NAME_AGE_UNIQUE",
        columnNames = {"NAME", "AGE"}
)})
public class Member extends BaseEntity{

    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }
    public Member(){

    }
    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID")
    private String id;

    @Column(name="NAME", nullable = false, length = 10)
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        if (this.team != null){
            this.team.getMembers().remove(this);
        }
        this.team = team;
        team.getMembers().add(this);
    }

    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    private Team team;

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
