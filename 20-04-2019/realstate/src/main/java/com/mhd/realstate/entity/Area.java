package com.mhd.realstate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "areas")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String areaName;

    //areas under city
//    @ManyToOne
//    @JoinTable(
//            name = "city_area",
//            joinColumns = @JoinColumn(name = "area_id"),
//            inverseJoinColumns = @JoinColumn(name = "city_id"))
//    private Set<City> cities;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    @JsonIgnore
    private City cities;

    public Area() {
    }

    public Area(String areaName, City cities) {
        this.areaName = areaName;
        this.cities = cities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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
        Area area = (Area) o;
        return Objects.equals(id, area.id) &&
                Objects.equals(areaName, area.areaName) &&
                Objects.equals(cities, area.cities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, areaName, cities);
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", areaName='" + areaName + '\'' +
                ", cities=" + cities +
                '}';
    }
}