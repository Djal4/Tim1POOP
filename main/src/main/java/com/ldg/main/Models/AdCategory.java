package com.ldg.main.Models;

import javax.persistence.*;

@Entity
@Table(name = "advertisment_categories")
public class AdCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    public AdCategory() {
    }

    public AdCategory(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public AdCategory(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
