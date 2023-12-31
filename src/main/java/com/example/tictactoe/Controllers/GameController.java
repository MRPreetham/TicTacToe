package com.example.tictactoe.Controllers;

import com.example.tictactoe.Exceptions.InvalidParameterException;
import com.example.tictactoe.Models.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class GameController {
    private int dimension;
    private List<Player> playerList;
    private Game game;

    public GameController(int dimension,List<Player> playerList) {
        this.dimension = dimension;
        this.playerList = playerList;
    }

    public Game CreateGame() throws InvalidParameterException {
        game = Game.getBuilder().setDimension(dimension)
                .setPlayerList(playerList)
                .build();
        return game;
    }

    public void display(Board board){
        game.display(board);
    }

    public Move makeNextMove(Board board){
        return game.makeNextMove(board);
    }

    public boolean validateMove(Cell cell){
        return game.validateMove(cell);
    }

}
