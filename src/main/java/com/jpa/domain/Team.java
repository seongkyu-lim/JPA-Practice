package com.jpa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public Team(){

    }

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private String id;

    private String name;

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

}
