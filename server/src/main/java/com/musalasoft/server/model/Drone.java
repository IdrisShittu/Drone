package com.musalasoft.server.model;

import javax.persistence.Column;
import javax.validation.constraints.Max;
//import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.musalasoft.server.enumeration.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class Drone implements Serializable {
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    
    //@Column(unique = true, nullable = false)
   // @NotEmpty(message = "serial number cannot be empty")
    @Size(max=100, message ="Serial Number is too long")
    private String serialNumber; //(100 characters max);

    @Column()
    @Enumerated(EnumType.ORDINAL)
    private DroneModel model;

    @Column()
    private Integer weight; //limit (500gr max);

    @Column()
    @Max(100)
    private Integer battery;// capacity (percentage);

    @Column
    @Enumerated(EnumType.ORDINAL)
    private DroneState state;

    @JsonIgnore
    @OneToMany(
        mappedBy = "drone"
    )
    private List<Medication> medications;

   

}