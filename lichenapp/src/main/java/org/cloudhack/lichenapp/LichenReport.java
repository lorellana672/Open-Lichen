package org.cloudhack.lichenapp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Larita
 */

@Getter
@ToString
@AllArgsConstructor
public class LichenReport {
    @Id
    private String reportId;
    private double lat;
    private double lng;
    private int datetime;
    private List<Map<String,SpecimenData>> samples;
}

/*
Candelaria Concolor //// tolerante /  nitrofila http://lichenportal.org/portal/taxa/index.php?taxon=53261
Rimelia Reticulata ///// nada http://lichenportal.org/portal/taxa/index.php?taxon=54609
Hyperphyscia syncolla //// altamente sensible  http://lichenportal.org/portal/taxa/index.php?taxon=55078
Physcia undulata ////// sensible http://eol.org/pages/996570/overview
Usnea amblyoclada ////// recontra sensible
 */

