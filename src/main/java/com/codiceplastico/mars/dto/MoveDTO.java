package com.codiceplastico.mars.dto;

import java.util.List;

import com.codiceplastico.mars.model.Command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoveDTO {
    private List<Command> commands;
}
