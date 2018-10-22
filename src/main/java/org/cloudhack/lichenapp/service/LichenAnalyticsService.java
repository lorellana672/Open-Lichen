package org.cloudhack.lichenapp.service;

import org.cloudhack.lichenapp.model.LichenReport;
import org.cloudhack.lichenapp.model.SpecimenData;
import org.cloudhack.lichenapp.repository.LichenReportRepositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class LichenAnalyticsService {
    private final static Logger logger = LoggerFactory.getLogger(LichenAnalyticsService.class);
    private static final int TTH = 10000;

    @Autowired
    private LichenReportRepositories lichenReportRepositories;

    private List<List<Map<String,SpecimenData>>> getSamples(List<LichenReport> lichenReports){
        return lichenReports.stream()
                .map(lichenReport -> lichenReport.getSamples())
                .collect(Collectors.toList());
    }

    private float calculateSensivity(List<LichenReport> lichenReports, String species){
        float a = 0, n = 0;
        for(LichenReport lichenReport : lichenReports){
            for(Map<String, SpecimenData> sample : lichenReport.getSamples()){
                if(sample.containsKey(species)){
                    a += sample.size();
                    n += 1;
                }
            }
        }
        float r = ((a-n) / n);
        return r;
    }

    public float getIAPF(LichenReport lichenReport){
        FloatCounter iap = new FloatCounter();
        lichenReport.getSamples()
                .forEach(sample -> sample.forEach(
                        (species,specimenData) -> {
                            iap.counter += ((float)specimenData.getTilesCovered()/10);
                        }
                ));
        return iap.counter / lichenReport
                .getSamples()
                .size();
    }

    public float getIAPQ(LichenReport lichenReport){
        FloatCounter iap = new FloatCounter();
        Map<String, Integer> speciesFs = new HashMap<>();
        lichenReport.getSamples()
                .forEach(sample -> sample.forEach(
                    (species,specimenData) -> {
                        float mmCovered = specimenData.getMilimetersCovered();
                        int braunBlanquet = (mmCovered / TTH < 5) ? 1 :
                                (mmCovered / TTH < 25) ? 2 :
                                (mmCovered / TTH < 50) ? 3 :
                                (mmCovered / TTH < 75) ? 4 : 5;
                        if(speciesFs.containsKey(species)){
                            braunBlanquet += speciesFs.get(species);
                        }
                        speciesFs.put(species,braunBlanquet);
                    }
            ));
        speciesFs.forEach(
                (species, braunBlanquet) ->
                        iap.counter += braunBlanquet * calculateSensivity(lichenReportRepositories.findAll(), species)
        );
        return iap.counter / lichenReport
                .getSamples()
                .size();
    }

    public static class FloatCounter {
        public float counter = 0;
    }
}
