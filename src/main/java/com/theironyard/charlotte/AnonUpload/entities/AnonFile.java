package com.theironyard.charlotte.AnonUpload.entities;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class AnonFile{
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String filename;

    @Column(nullable = false)
    String originalFilename;

    @Column
    int length;



    public AnonFile() {
    }

    public AnonFile(String filename, String originalFilename, int length) {
        this.filename = filename;
        this.originalFilename = originalFilename;
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}