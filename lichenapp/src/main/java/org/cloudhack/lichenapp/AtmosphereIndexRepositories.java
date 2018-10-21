package org.cloudhack.lichenapp;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AtmosphereIndexRepositories extends MongoRepository<AtmosphereIndexes, Integer> {
}
