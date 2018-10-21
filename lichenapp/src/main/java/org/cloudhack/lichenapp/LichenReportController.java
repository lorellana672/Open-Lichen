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

    @RequestMapping(value="/report", method=RequestMethod.POST)
    public ResponseEntity reportResponse(@RequestBody LichenReport report){
        logger.info(report.toString());
        lichenReportRepositories.insert(report);
        return ResponseEntity.ok()
                .build();
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
}