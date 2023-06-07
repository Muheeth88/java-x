package com.xapp.xjava.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @ElementCollection
    private List<String> tags = new ArrayList<>();

    @Embedded
    private SubTask subTask;

    @OneToOne(cascade = CascadeType.ALL
    // fetch = FetchType.LAZY
    )
    @JoinColumn(name = "asset_id", referencedColumnName = "assetId")
    private Assets assets;

}
