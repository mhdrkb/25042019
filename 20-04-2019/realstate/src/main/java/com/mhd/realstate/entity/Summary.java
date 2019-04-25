package com.mhd.realstate.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Summary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Enter Name")
    @Size(min = 1, max = 50, message = "Hey, Size must be between 1 and 50")
    @Column(nullable = false)
    private String contactName;

    @NotBlank(message = "Enter your email")
    @Column(nullable = false)
    private String email;

    @NotEmpty(message = "Enter  comment")
    @Size(min = 1, max = 50, message = "Hey, Size must be between 1 and 50")
    @Column(nullable = false)
    private String comment;

    public Summary() {
    }

    public Summary(@NotEmpty(message = "Enter Name") @Size(min = 1, max = 50, message = "Hey, Size must be between 1 and 50") String contactName, @NotBlank(message = "Enter your email") String email, @NotEmpty(message = "Enter  comment") @Size(min = 1, max = 50, message = "Hey, Size must be between 1 and 50") String comment) {
        this.contactName = contactName;
        this.email = email;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Summary summary = (Summary) o;
        return Objects.equals(id, summary.id) &&
                Objects.equals(contactName, summary.contactName) &&
                Objects.equals(email, summary.email) &&
                Objects.equals(comment, summary.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contactName, email, comment);
    }

    @Override
    public String toString() {
        return "Summary{" +
                "id=" + id +
                ", contactName='" + contactName + '\'' +
                ", email='" + email + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
