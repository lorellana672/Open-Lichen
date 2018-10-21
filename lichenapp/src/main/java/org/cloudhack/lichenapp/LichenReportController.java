package org.cloudhack.lichenapp;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class LichenReportController {
    public final static Logger logger = LoggerFactory.getLogger(LichenReportController.class);

    @Autowired
    private LichenReportRepositories lichenReportRepositories;
    @Autowired
    private AtmosphereIndexRepositories atmosphereIndexRepositories;
    @Autowired
    private LichenAnalyticsService lichenAnalyticsService;

    @RequestMapping(value="/report", method=RequestMethod.POST)
    public ResponseEntity<AtmosphereIndexes> reportResponse(@RequestBody LichenReport report){
        logger.info(report.toString());
        lichenReportRepositories.save(report);
        AtmosphereIndexes atmosphereIndexes = AtmosphereIndexes.builder()
                .iapf(lichenAnalyticsService.getIAPF(report))
                .iapq(lichenAnalyticsService.getIAPQ(report))
                .build();
        atmosphereIndexRepositories.save(atmosphereIndexes);
        return ResponseEntity.ok()
                .body(atmosphereIndexes);
    }

    @RequestMapping(value="/report", method=RequestMethod.GET)
    public ResponseEntity<List<LichenReport>> getLastReports(@RequestParam("last") Integer lasts){
        logger.info("Retrieving lasts {} reports from Database",lasts);
        return ResponseEntity.ok()
                .body(lichenReportRepositories.findTop1000ByOrderByDatetimeDesc()
                        .stream()
                        .limit(lasts)
                        .collect(Collectors.toList()));
    }


    @RequestMapping(value="/atmos", method=RequestMethod.GET)
    public ResponseEntity<AtmosphereIndexes> getLastIndex(){
        logger.info("Retrieving index actual atmosphere reports from Database");
        return ResponseEntity.ok()
                .body(atmosphereIndexRepositories.findById(1)
                        .orElseGet(() -> AtmosphereIndexes.builder()
                                        .iapf(0)
                                        .iapq(0).build()));
    }

}