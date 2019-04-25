package com.mhd.realstate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "housing_area")
public class Housing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String housingName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "area_id", nullable = false)
    @JsonIgnore
    private Area areas;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    @JsonIgnore
    private City cities;

    public Housing() {
    }

    public Housing(String housingName, Area areas, City cities) {
        this.housingName = housingName;
        this.areas = areas;
        this.cities = cities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHousingName() {
        return housingName;
    }

    public void setHousingName(String housingName) {
        this.housingName = housingName;
    }

    public Area getAreas() {
        return areas;
    }

    public void setAreas(Area areas) {
        this.areas = areas;
    }

    public City getCities() {
        return cities;
    }

    public void setCities(City cities) {
        this.cities = cities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Housing housing = (Housing) o;
        return Objects.equals(id, housing.id) &&
                Objects.equals(housingName, housing.housingName) &&
                Objects.equals(areas, housing.areas) &&
                Objects.equals(cities, housing.cities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, housingName, areas, cities);
    }

    @Override
    public String toString() {
        return "Housing{" +
                "id=" + id +
                ", housingName='" + housingName + '\'' +
                ", areas=" + areas +
                ", cities=" + cities +
                '}';
    }
}
