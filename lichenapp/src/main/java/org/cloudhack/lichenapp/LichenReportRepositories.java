package org.cloudhack.lichenapp;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LichenReportRepositories extends MongoRepository<LichenReport, String>{

    public List<LichenReport> findTop1000ByOrderByDatetimeDesc();

}
