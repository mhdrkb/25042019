package com.mhd.realstate.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "agents")
public class Agents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Enter First Name")
    @Size(min = 1, max = 50, message = "Hey, Size must be between 1 and 50")
    @Column(nullable = false)
    private String agentName;


    @NotBlank(message = "Enter your email")
    @Column(nullable = false)
    private String email;

    @Column(unique = true)
    private String mobile;

    //////File Upload==============
    @Column(nullable = true)
    private long fileSize;
    private String fileName;
    //  @Lob
    // private byte[] file;
    private String filePath;
    private String fileExtension;

    public Agents() {
    }

    public Agents(@NotEmpty(message = "Enter First Name") @Size(min = 1, max = 50, message = "Hey, Size must be between 1 and 50") String agentName, @NotBlank(message = "Enter your email") String email, String mobile, long fileSize, String fileName, String filePath, String fileExtension) {
        this.agentName = agentName;
        this.email = email;
        this.mobile = mobile;
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

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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
        Agents agents = (Agents) o;
        return fileSize == agents.fileSize &&
                Objects.equals(id, agents.id) &&
                Objects.equals(agentName, agents.agentName) &&
                Objects.equals(email, agents.email) &&
                Objects.equals(mobile, agents.mobile) &&
                Objects.equals(fileName, agents.fileName) &&
                Objects.equals(filePath, agents.filePath) &&
                Objects.equals(fileExtension, agents.fileExtension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, agentName, email, mobile, fileSize, fileName, filePath, fileExtension);
    }

    @Override
    public String toString() {
        return "Agents{" +
                "id=" + id +
                ", agentName='" + agentName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", fileSize=" + fileSize +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                '}';
    }
}
