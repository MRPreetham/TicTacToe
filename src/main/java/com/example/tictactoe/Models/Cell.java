package com.example.tictactoe.Models;

import com.example.tictactoe.Enums.CellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cell {
    private int row;
    private int col;
    private Player player;
    private CellStatus cellStatus;

    public Cell(int i,int j){
        this.row = i;
        this.col = j;
        this.cellStatus = CellStatus.EMPTY;
    }
}
