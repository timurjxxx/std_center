package com.backend.studycenter.configs.redis;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//This Entity and Country related logic is added for edu purpose to explain redis caching mechanism
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;


}
