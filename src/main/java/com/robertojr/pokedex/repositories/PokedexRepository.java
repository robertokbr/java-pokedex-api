package com.robertojr.pokedex.repositories;

import com.robertojr.pokedex.models.Pokemon;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PokedexRepository extends ReactiveMongoRepository<Pokemon, String> {}
