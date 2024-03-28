package com.codiceplastico.mars.model;

import lombok.Builder;
import lombok.Data;

/**
 * Rover
 */
@Data
@Builder
public class Rover {
    private Point position;
    private Direction direction;

    public Point getDeltaForward() {
        return switch (direction) {
            case N -> Point.builder().x(0).y(-1).build();
            case S -> Point.builder().x(0).y(1).build();
            case E -> Point.builder().x(1).y(0).build();
            case W -> Point.builder().x(-1).y(0).build();
        };

    }

    public Point getDeltaBackword() {
        return switch (direction) {
            case N -> Point.builder().x(0).y(1).build();
            case S -> Point.builder().x(0).y(-1).build();
            case E -> Point.builder().x(-1).y(0).build();
            case W -> Point.builder().x(1).y(0).build();
        };
    }

    public Point getDeltaLeft() {
        return switch (direction) {
            case N -> Point.builder().x(1).y(0).build();
            case S -> Point.builder().x(0).y(1).build();
            case E -> Point.builder().x(0).y(-1).build();
            case W -> Point.builder().x(0).y(1).build();
        };
    }

    public Point getDeltaRight() {
        return switch (direction) {
            case N -> Point.builder().x(1).y(0).build();
            case S -> Point.builder().x(-1).y(0).build();
            case E -> Point.builder().x(0).y(1).build();
            case W -> Point.builder().x(0).y(-1).build();
        };
    }
}
