package org.cloudhack.lichenapp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class AtmosphereIndexes {
    @Id
    private final int id = 1;
    private float iapq;
    private float iapf;
}
