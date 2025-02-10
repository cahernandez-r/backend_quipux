package com.quipux.playlistmanager.common.entities;

import com.quipux.playlistmanager.common.entities.general.EntityPrincipal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_genre")
public class Genre extends EntityPrincipal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genre")
    private Long id;

    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description", nullable = false, length = 200)
    private String description;
}
