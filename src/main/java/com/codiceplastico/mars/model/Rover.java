package com.codiceplastico.mars.model;

import lombok.Data;

/**
 * Rover
 */
@Data
public class Rover {
    private Point position;
    private Direction direction;
}
