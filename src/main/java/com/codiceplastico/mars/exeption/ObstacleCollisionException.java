package com.codiceplastico.mars.exeption;

import com.codiceplastico.mars.model.Point;

import lombok.Getter;

/**
 * ObstacleCollisionException
 */
public class ObstacleCollisionException extends RuntimeException{

    @Getter
    private Point obstaclePosition;

    public ObstacleCollisionException(String message){
        super(message);
    }
    
    public ObstacleCollisionException(Point obstaclePosition){
        super();
        this.obstaclePosition = obstaclePosition;
    }
    
}
