package com.mhd.realstate.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "catagories")
public class Catagory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String catagoryName;

    public Catagory() {
    }

    public Catagory(String catagoryName) {
        this.catagoryName = catagoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Catagory catagory = (Catagory) o;
        return Objects.equals(id, catagory.id) &&
                Objects.equals(catagoryName, catagory.catagoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, catagoryName);
    }

    @Override
    public String toString() {
        return "Catagory{" +
                "id=" + id +
                ", catagoryName='" + catagoryName + '\'' +
                '}';
    }
}
