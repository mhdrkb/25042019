package com.mhd.realstate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "buildings")
public class Buildings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String buildingName;

    private int bedRooms;

    private int bathRooms;

    private String flatSize;

    private Double price;

    private String streetAddress;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date builtInDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registrationDate;

    private String otherFeatures;

    private String otherDetailing;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "available_id", nullable = false)
    @JsonIgnore
    private Availablilty availableFor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    @JsonIgnore
    private City cities;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "area_id", nullable = false)
    @JsonIgnore
    private Area areas;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "housing_id", nullable = false)
    @JsonIgnore
    private Housing housings;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "catagory_id", nullable = false)
    @JsonIgnore
    private Catagory catagories;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "construction_id", nullable = false)
    @JsonIgnore
    private ConstructionType constructionType;

    //////File Upload==============
    @Column(nullable = true)
    private long fileSize;
    private String fileName;
    //  @Lob
    // private byte[] file;
    private String filePath;
    private String fileExtension;

    public Buildings() {
    }

    public Buildings(String buildingName, int bedRooms, int bathRooms, String flatSize, Double price, String streetAddress, Date builtInDate, Date registrationDate, String otherFeatures, String otherDetailing, Availablilty availableFor, City cities, Area areas, Housing housings, Catagory catagories, ConstructionType constructionType, long fileSize, String fileName, String filePath, String fileExtension) {
        this.buildingName = buildingName;
        this.bedRooms = bedRooms;
        this.bathRooms = bathRooms;
        this.flatSize = flatSize;
        this.price = price;
        this.streetAddress = streetAddress;
        this.builtInDate = builtInDate;
        this.registrationDate = registrationDate;
        this.otherFeatures = otherFeatures;
        this.otherDetailing = otherDetailing;
        this.availableFor = availableFor;
        this.cities = cities;
        this.areas = areas;
        this.housings = housings;
        this.catagories = catagories;
        this.constructionType = constructionType;
        this.fileSize = fileSize;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileExtension = fileExtension;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getBedRooms() {
        return bedRooms;
    }

    public void setBedRooms(int bedRooms) {
        this.bedRooms = bedRooms;
    }

    public int getBathRooms() {
        return bathRooms;
    }

    public void setBathRooms(int bathRooms) {
        this.bathRooms = bathRooms;
    }

    public String getFlatSize() {
        return flatSize;
    }

    public void setFlatSize(String flatSize) {
        this.flatSize = flatSize;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public Date getBuiltInDate() {
        return builtInDate;
    }

    public void setBuiltInDate(Date builtInDate) {
        this.builtInDate = builtInDate;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getOtherFeatures() {
        return otherFeatures;
    }

    public void setOtherFeatures(String otherFeatures) {
        this.otherFeatures = otherFeatures;
    }

    public String getOtherDetailing() {
        return otherDetailing;
    }

    public void setOtherDetailing(String otherDetailing) {
        this.otherDetailing = otherDetailing;
    }

    public Availablilty getAvailableFor() {
        return availableFor;
    }

    public void setAvailableFor(Availablilty availableFor) {
        this.availableFor = availableFor;
    }

    public City getCities() {
        return cities;
    }

    public void setCities(City cities) {
        this.cities = cities;
    }

    public Area getAreas() {
        return areas;
    }

    public void setAreas(Area areas) {
        this.areas = areas;
    }

    public Housing getHousings() {
        return housings;
    }

    public void setHousings(Housing housings) {
        this.housings = housings;
    }

    public Catagory getCatagories() {
        return catagories;
    }

    public void setCatagories(Catagory catagories) {
        this.catagories = catagories;
    }

    public ConstructionType getConstructionType() {
        return constructionType;
    }

    public void setConstructionType(ConstructionType constructionType) {
        this.constructionType = constructionType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buildings buildings = (Buildings) o;
        return bedRooms == buildings.bedRooms &&
                bathRooms == buildings.bathRooms &&
                fileSize == buildings.fileSize &&
                Objects.equals(id, buildings.id) &&
                Objects.equals(buildingName, buildings.buildingName) &&
                Objects.equals(flatSize, buildings.flatSize) &&
                Objects.equals(price, buildings.price) &&
                Objects.equals(streetAddress, buildings.streetAddress) &&
                Objects.equals(builtInDate, buildings.builtInDate) &&
                Objects.equals(registrationDate, buildings.registrationDate) &&
                Objects.equals(otherFeatures, buildings.otherFeatures) &&
                Objects.equals(otherDetailing, buildings.otherDetailing) &&
                Objects.equals(availableFor, buildings.availableFor) &&
                Objects.equals(cities, buildings.cities) &&
                Objects.equals(areas, buildings.areas) &&
                Objects.equals(housings, buildings.housings) &&
                Objects.equals(catagories, buildings.catagories) &&
                Objects.equals(constructionType, buildings.constructionType) &&
                Objects.equals(fileName, buildings.fileName) &&
                Objects.equals(filePath, buildings.filePath) &&
                Objects.equals(fileExtension, buildings.fileExtension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, buildingName, bedRooms, bathRooms, flatSize, price, streetAddress, builtInDate, registrationDate, otherFeatures, otherDetailing, availableFor, cities, areas, housings, catagories, constructionType, fileSize, fileName, filePath, fileExtension);
    }

    @Override
    public String toString() {
        return "Buildings{" +
                "id=" + id +
                ", buildingName='" + buildingName + '\'' +
                ", bedRooms=" + bedRooms +
                ", bathRooms=" + bathRooms +
                ", flatSize='" + flatSize + '\'' +
                ", price=" + price +
                ", streetAddress='" + streetAddress + '\'' +
                ", builtInDate=" + builtInDate +
                ", registrationDate=" + registrationDate +
                ", otherFeatures='" + otherFeatures + '\'' +
                ", otherDetailing='" + otherDetailing + '\'' +
                ", availableFor=" + availableFor +
                ", cities=" + cities +
                ", areas=" + areas +
                ", housings=" + housings +
                ", catagories=" + catagories +
                ", constructionType=" + constructionType +
                ", fileSize=" + fileSize +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                '}';
    }
}
