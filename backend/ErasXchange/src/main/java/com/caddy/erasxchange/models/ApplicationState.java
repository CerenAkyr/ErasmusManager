package com.caddy.erasxchange.models;


import com.caddy.erasxchange.services.PlacementStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/*
* this entity holds the state for certian applicaiton states to be used
* */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ApplicationState extends  BaseEntity {
    @Enumerated(EnumType.STRING)
    private Department department;
    @Enumerated(value = EnumType.STRING)
    private PlacementStatus placementStatus = PlacementStatus.NO_FILE;

}
