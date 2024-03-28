package com.codiceplastico.mars.dto;

import java.util.List;

import com.codiceplastico.mars.model.Command;

import lombok.Data;

@Data
public class MoveDTO {
    private List<Command> commands;
}
