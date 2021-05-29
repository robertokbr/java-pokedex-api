package com.robertojr.pokedex.controllers;

import com.robertojr.pokedex.models.Pokemon;
import com.robertojr.pokedex.repositories.PokedexRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pokemons")
public class PokemonsController {
  private PokedexRepository pokedexRepository;

  public PokemonsController(PokedexRepository pokedexRepository) {
    this.pokedexRepository = pokedexRepository;
  }

  @GetMapping
  public Flux<Pokemon> index() {
    return this.pokedexRepository.findAll();
  } 

  @GetMapping("/{id}")
  public Mono<ResponseEntity<Pokemon>> show(@PathVariable String id) {
    return this.pokedexRepository.findById(id)
      .map(entity -> ResponseEntity.ok(entity))
      .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Pokemon> create(@RequestBody Pokemon pokemon) {
    return this.pokedexRepository.save(pokemon);
  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<Pokemon>> update(
    @PathVariable String id, 
    @RequestBody Pokemon newDataPokemon
  ) {
    return this.pokedexRepository.findById(id)
      .flatMap(pokemon -> {
        pokemon.setName(newDataPokemon.getName());
        pokemon.setCategory(newDataPokemon.getCategory());
        pokemon.setHability(newDataPokemon.getHability());
        pokemon.setWeight(newDataPokemon.getWeight());
        return this.pokedexRepository.save(pokemon);
      })
      .map(entity -> ResponseEntity.ok(entity))
      .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> delete(
    @PathVariable String id
  ) {
    return this.pokedexRepository.findById(id)
      .flatMap(pokemon -> 
        this.pokedexRepository.delete(pokemon)
        .then(Mono.just(ResponseEntity.ok().<Void>build()))
        .defaultIfEmpty(ResponseEntity.notFound().build())
      );
  }

  @DeleteMapping()
  public Mono<Void> deleteAll() {
    return this.pokedexRepository.deleteAll();
  }
}
