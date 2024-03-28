package com.codiceplastico.mars.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.codiceplastico.mars.exeption.ObstacleCollisionException;
import com.codiceplastico.mars.model.Command;
import com.codiceplastico.mars.model.Direction;
import com.codiceplastico.mars.model.Point;
import com.codiceplastico.mars.model.Rover;
import com.codiceplastico.mars.util.Utils;

@Service
public class MarsService {

    public final static int MAX_X = 10;
    public final static int MAX_Y = 4;
    public final static Set<Point> obstacles = new HashSet<>(Arrays.asList(Point.builder().x(5).y(2).build()));

    private Rover rover;

    public void initialize(Point roverStartingPoint, Direction roverDirection) {
        if (obstacles.contains(roverStartingPoint)) {
            throw new ObstacleCollisionException("Cannot place the rover on an obstacle");
        }

        this.rover = Rover.builder()
                .position(normalizePoint(roverStartingPoint))
                .direction(roverDirection)
                .build();
    }

    public Point move(Command command) {
        Point moveDelta = switch (command) {
            case f -> rover.getDeltaForward();
            case b -> rover.getDeltaBackword();
            case l -> rover.getDeltaLeft();
            case r -> rover.getDeltaRight();
        };

        var newPosition = normalizePoint(Utils.addPoints(rover.getPosition(), moveDelta));
        if (obstacles.contains(newPosition)) {
            throw new ObstacleCollisionException(newPosition);
        }
        rover.setPosition(newPosition);

        return newPosition;
    }

    private Point normalizePoint(Point point) {
        int x = point.getX();
        int y = point.getY();
        return Point.builder()
                .x(x % MAX_X)
                .y(y % MAX_Y)
                .build();
    }

}
