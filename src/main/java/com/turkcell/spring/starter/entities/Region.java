package com.turkcell.spring.starter.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="region")
public class Region {
    @Id
    @Column(name="region_id")
    private int regionId;

    @Column(name="region_description")
    private String regionDescription;
}
