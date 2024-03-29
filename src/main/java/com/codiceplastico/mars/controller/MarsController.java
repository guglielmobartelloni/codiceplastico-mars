package com.codiceplastico.mars.controller;

import com.codiceplastico.mars.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codiceplastico.mars.dto.InitializationDTO;
import com.codiceplastico.mars.dto.MoveDTO;
import com.codiceplastico.mars.exeption.ObstacleCollisionException;
import com.codiceplastico.mars.service.MarsService;

import lombok.extern.slf4j.Slf4j;

/**
 * MarsController
 */
@RestController
@Slf4j
public class MarsController {

    @Autowired
    private MarsService marsService;

    @PostMapping("/initialize")
    public ApiResponse initialize(@RequestBody InitializationDTO body) {
        marsService.initialize(body.getStartingPoint(), body.getDirection());
        return new ApiResponse("Successfully initialized the rover at " + body.getStartingPoint() + " with direction "
                + body.getDirection());
    }

    @PostMapping("/move")
    public ApiResponse move(@RequestBody MoveDTO body) {
        try {
            var movePointsList = body.getCommands().stream()
                    .map(command -> marsService.move(command))
                    .toList();
            return new ApiResponse("Moved at these points " + movePointsList);
        } catch (ObstacleCollisionException ex) {
            return new ApiResponse("Aborting! Obstacle encountered at " + ex.getObstaclePosition() + " . The rover is at " + marsService.getRover().getPosition());
        }
    }

}
