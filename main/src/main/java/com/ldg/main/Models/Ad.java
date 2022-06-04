package com.ldg.main.Models;

import javax.persistence.*;

@Entity
@Table(name = "advertisments")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "flat_id")
    private int flatId;

    @Column(name = "advertisment_category_id")
    private int adCategoryId;

    @Column(name = "owner_id")
    private int ownerId;
}
