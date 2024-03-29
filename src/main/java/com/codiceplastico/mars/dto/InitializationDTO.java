package com.codiceplastico.mars.dto;

import com.codiceplastico.mars.model.Direction;
import com.codiceplastico.mars.model.Point;

import lombok.Builder;
import lombok.Data;

/**
 * InitializationDTO
 */
@Data
@Builder
public class InitializationDTO {
    private Point startingPoint;
    private Direction direction;
}
