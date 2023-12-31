package com.example.tictactoe.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Board {
    private int size;
    private List<List<Cell>> board = new ArrayList<>();
    public Board(int size){
        this.size = size;
        for(int i=0;i<size;i++){
            List<Cell> row = new ArrayList<>();
            for(int j=0;j<size;j++){
                row.add(new Cell(i,j));
            }
            board.add(row);
        }
    }
}
