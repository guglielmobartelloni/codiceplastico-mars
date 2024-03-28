package com.codiceplastico.mars.util;

import com.codiceplastico.mars.model.Point;

/**
 * Utils
 */
public class Utils {

    public static Point addPoints(Point first, Point second){
        return Point.builder()
        .x(first.getX() + second.getX())
        .y(first.getY() + second.getY())
        .build();
    }
}
