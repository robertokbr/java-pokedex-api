package com.robertojr.pokedex.models;

import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Pokemon {
  @Id
  private String id;
  private String name;
  private String category;
  private String hability;
  private double weight;

  public Pokemon(
    String id,
    String name,
    String category,
    String hability,
    double weight
  ) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.hability = hability;
    this.weight = weight;
  }

  @Override
  public String toString() {
    return "Pokemon{" +
      "id='" + id + '\'' +
      ", nome='" + name + '\'' +
      ", categoria='" + category + '\'' +
      ", habilidades='" + hability + '\'' +
      ", peso=" + weight +
    "}";
  }

  @Override
  public boolean equals(Object toCompare_object) {
      if (this == toCompare_object) return true;
      if (!(toCompare_object instanceof Pokemon)) return false;
      Pokemon pokemon = (Pokemon) toCompare_object;
      return Objects.equals(id, pokemon.id) &&
              Objects.equals(name, pokemon.name) &&
              Objects.equals(category, pokemon.category) &&
              Objects.equals(hability, pokemon.hability) &&
              Objects.equals(weight, pokemon.weight);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, category, hability, weight);
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return this.category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getHability() {
    return this.hability;
  }

  public void setHability(String hability) {
    this.hability = hability;
  }

  public double getweight() {
    return this.weight;
  }

  public void setweight(double weight) {
    this.weight = weight;
  }
}