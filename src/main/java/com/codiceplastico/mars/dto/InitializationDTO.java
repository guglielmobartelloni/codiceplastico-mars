package com.codiceplastico.mars.dto;

import com.codiceplastico.mars.model.Direction;
import com.codiceplastico.mars.model.Point;

import lombok.Data;

/**
 * InitializationDTO
 */
@Data
public class InitializationDTO {
    private Point startingPoint;
    private Direction direction;
}
