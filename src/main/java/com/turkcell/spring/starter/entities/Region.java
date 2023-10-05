package com.turkcell.spring.starter.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="region")
public class Region {
    @Id
    @Column(name="region_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int regionId;

    @Column(name="region_description")
    private String regionDescription;
}
