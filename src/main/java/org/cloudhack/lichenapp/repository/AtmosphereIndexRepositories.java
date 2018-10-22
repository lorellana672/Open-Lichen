package org.cloudhack.lichenapp.repository;

import org.cloudhack.lichenapp.model.AtmosphereIndexes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AtmosphereIndexRepositories extends MongoRepository<AtmosphereIndexes, Integer> {
}
