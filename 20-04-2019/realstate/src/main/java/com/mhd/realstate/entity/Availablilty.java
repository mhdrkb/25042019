package com.mhd.realstate.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "availability")
public class Availablilty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String availName;

    public Availablilty() {
    }

    public Availablilty(String availName) {
        this.availName = availName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvailName() {
        return availName;
    }

    public void setAvailName(String availName) {
        this.availName = availName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Availablilty that = (Availablilty) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(availName, that.availName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, availName);
    }

    @Override
    public String toString() {
        return "Availablilty{" +
                "id=" + id +
                ", availName='" + availName + '\'' +
                '}';
    }
}
