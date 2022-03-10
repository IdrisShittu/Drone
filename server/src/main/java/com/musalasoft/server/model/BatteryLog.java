package com.musalasoft.server.model;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.*;


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
public class BatteryLog implements Serializable {
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    
    @Column(unique = true, nullable = false)
    @NotEmpty(message = "serial number cannot be empty")
    @Size(max=100, message ="Serial Number is too long")
    private String serialNumber;

    @Column()
    @Max(100)
    private Integer battery;// capacity (percentage);

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date timestamp;


}