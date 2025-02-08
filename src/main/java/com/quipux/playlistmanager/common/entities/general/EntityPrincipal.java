package com.quipux.playlistmanager.common.entities.general;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class EntityPrincipal {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", insertable = false)
    private Date creationDate;

    @Column(name = "active")
    private Boolean active;

    @PrePersist
    @PreUpdate
    public void setDefaultActiveValue() {
        if (active == null)
            active = Boolean.TRUE;
    }
}
