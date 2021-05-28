package com.robertojr.pokedex.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Pokemon {
  @Id
  private String id;

  private String nome;

  private String category;

  private String hability;

  private double height;
}