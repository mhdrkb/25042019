package com.mhd.realstate.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "construction_type")
public class ConstructionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String constTypeName;

    public ConstructionType() {
    }

    public ConstructionType(String constTypeName) {
        this.constTypeName = constTypeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConstTypeName() {
        return constTypeName;
    }

    public void setConstTypeName(String constTypeName) {
        this.constTypeName = constTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConstructionType that = (ConstructionType) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(constTypeName, that.constTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, constTypeName);
    }

    @Override
    public String toString() {
        return "ConstructionType{" +
                "id=" + id +
                ", constTypeName='" + constTypeName + '\'' +
                '}';
    }
}