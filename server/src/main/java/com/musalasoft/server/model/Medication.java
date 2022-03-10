package com.musalasoft.server.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
public class Medication implements Serializable {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;

@Column
private String name;

@Column
private int weight;

@Column
private String code;

@Column
private String imageURI;

@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(
    name="serialNumber",
    referencedColumnName="serialNumber"
)
private Drone drone;


}
