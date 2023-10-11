package com.turkcell.spring.starter.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class Territory {
    @Id
    @Column(name="territory_id")
    private String territoryId;
    @Column(name="territory_description")
    private String territoryDescription;
    @Column(name="region_id")
    private int regionId;
}