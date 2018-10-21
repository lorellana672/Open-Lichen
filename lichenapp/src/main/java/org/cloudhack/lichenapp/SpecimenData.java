package org.cloudhack.lichenapp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;


@Getter
@ToString
@AllArgsConstructor
public class SpecimenData {
    private int milimetersCovered;
    private List<String> tilesCovered;
}
