package com.codiceplastico.mars.controller;

import com.codiceplastico.mars.dto.InitializationDTO;
import com.codiceplastico.mars.dto.MoveDTO;
import com.codiceplastico.mars.exeption.ObstacleCollisionException;
import com.codiceplastico.mars.model.Command;
import com.codiceplastico.mars.model.Direction;
import com.codiceplastico.mars.model.Point;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MarsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;


    @BeforeEach
    void setup() throws Exception {
        testInitializeWithGoodParams();
    }
    @Test
    void testInitializeWithGoodParams() throws Exception {
        var initializationDTO = InitializationDTO.builder()
                .startingPoint(Point.builder()
                        .x(1)
                        .y(0)
                        .build())
                .direction(Direction.E)
                .build();

        mockMvc.perform(post("/initialize")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(initializationDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"message\": \"Successfully initialized the rover at (1, 0) with direction E\"\n" +
                        "}"))
        ;
    }

    @Test
    void testInitializeOnObstacleShouldException() throws Exception {
        var initializationDTO = InitializationDTO.builder()
                .startingPoint(Point.builder()
                        .x(1)
                        .y(2)
                        .build())
                .direction(Direction.E)
                .build();

        mockMvc.perform(post("/initialize")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(initializationDTO)))
                .andExpect(result -> assertInstanceOf(ObstacleCollisionException.class, result.getResolvedException()))
        ;
    }

    @Test
    void testInitializeWithWrongDirectionShouldException() throws Exception {

        mockMvc.perform(post("/initialize")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"startingPoint\": {\n" +
                                "        \"x\": 1,\n" +
                                "        \"y\": 2\n" +
                                "    },\n" +
                                "    \"direction\": \"F\"\n" +
                                "}"))
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertInstanceOf(RuntimeException.class, result.getResolvedException()))
        ;
    }

    @Test
    void testMovePointWithoutObstacles() throws Exception {
        var moveDTO = MoveDTO.builder()
                .commands(Arrays.asList(Command.f, Command.l, Command.f, Command.f, Command.f))
                        .build();

        mockMvc.perform(post("/move")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(moveDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"Moved at these points [(1, 1), (0, 1), (0, 2), (0, 3), (0, 4)]\"}"))
        ;
    }

    @Test
    void testMovePointWithObstacles() throws Exception {
        var moveDTO = MoveDTO.builder()
                .commands(Arrays.asList(Command.f, Command.f, Command.f, Command.f))
                .build();

        mockMvc.perform(post("/move")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(moveDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"Aborting! Obstacle encountered at (1, 2) . The rover is at (1, 1)\"}"))
        ;
    }


    @Test
    void testCricularGrid() throws Exception {
        var moveDTO = MoveDTO.builder()
                .commands(Arrays.asList(Command.f, Command.l, Command.f, Command.f, Command.f, Command.f))
                .build();

        mockMvc.perform(post("/move")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(moveDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"Moved at these points [(1, 1), (0, 1), (0, 2), (0, 3), (0, 4), (0, 0)]\"}"))
        ;
    }


}