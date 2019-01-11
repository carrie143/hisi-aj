package com.hisi.model;

import javax.persistence.*;

@Table(name = "hisi_carry_person")
public class HisiCarryPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "carry_person")
    private String carryPerson;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return carry_person
     */
    public String getCarryPerson() {
        return carryPerson;
    }

    /**
     * @param carryPerson
     */
    public void setCarryPerson(String carryPerson) {
        this.carryPerson = carryPerson;
    }
}