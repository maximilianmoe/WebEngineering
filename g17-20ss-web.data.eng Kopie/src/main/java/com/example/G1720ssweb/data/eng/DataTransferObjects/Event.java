package com.example.G1720ssweb.data.eng.DataTransferObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Event implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventId;

    private String name;

    private String description;

    private Date date;

    private String  eventType;

    private Integer interest;

    private Integer disinterest;

    private String location;

    private Boolean isLiked;

    private Boolean isDisliked;

}
