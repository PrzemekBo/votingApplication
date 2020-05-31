package com.voting.voting.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "object")
public class Object {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //TODO AUTO
    private int id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    private String name;

    @Column(name = "votes")
    private int votes;

    public Object() {
    }

    public Object(String name,int votes) {
        this.name = name;
        this.votes = votes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}

