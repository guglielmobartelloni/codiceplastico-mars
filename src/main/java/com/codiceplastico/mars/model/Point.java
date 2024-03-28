package com.codiceplastico.mars.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class Point {
    private int x;
    private int y;

    public String toString(){
        return "("+ x + ", "+ y + ")";
    }

}
