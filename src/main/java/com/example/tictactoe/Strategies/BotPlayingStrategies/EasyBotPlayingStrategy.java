package com.example.tictactoe.Strategies.BotPlayingStrategies;

import com.example.tictactoe.Enums.CellStatus;
import com.example.tictactoe.Models.Board;
import com.example.tictactoe.Models.Bot;
import com.example.tictactoe.Models.Cell;
import com.example.tictactoe.Models.Move;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move decideMove(Bot bot, Board board) {
        int dimension = board.getSize();
        for(int i=0;i<dimension;i++){
            for(int j=0;j<dimension;j++){
                if(board.getBoard().get(i).get(j).getCellStatus().equals(CellStatus.EMPTY)){
                   Cell cell = new Cell(i,j);
                   return new Move(bot,cell);
                }
            }
        }
        Cell cell = new Cell(0,0);
        return new Move(bot,cell);
    }
}
