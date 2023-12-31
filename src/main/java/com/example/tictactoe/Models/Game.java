package com.example.tictactoe.Models;

import com.example.tictactoe.Enums.CellStatus;
import com.example.tictactoe.Enums.GameStatus;
import com.example.tictactoe.Enums.PlayerType;
import com.example.tictactoe.Exceptions.InvalidMoveException;
import com.example.tictactoe.Exceptions.InvalidParameterException;
import com.example.tictactoe.Strategies.WinningStrategies.OrderOneWinningStrategy;
import com.example.tictactoe.Strategies.WinningStrategies.WinningStrategy;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Game {
    private Board board;
    private GameStatus gameStatus;
    private List<Player> playerList;
    private Player winner;
    private List<Move> moveList;
    private int nextPlayerIndex;
    private WinningStrategy winningStrategy;

    private Game(Builder builder){
        this.board = new Board(builder.getDimension());
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.playerList = builder.getPlayerList();
        this.moveList = new ArrayList<>();
        this.nextPlayerIndex = 0;
        this.winningStrategy = new OrderOneWinningStrategy(builder.getDimension());
    }

    public static Builder getBuilder(){
        return new Builder();
    }
    @Getter
    @Setter
    public static class Builder{
        private List<Player> playerList;
        private int dimension;

        private Builder(){}

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayerList(List<Player> playerList) {
            this.playerList = playerList;
            return this;
        }

        public boolean isValid(){
            int numberOfPlayer = playerList.size();
            if(dimension<3 || numberOfPlayer>dimension-1){
                return false;
            }
            int botCount = 0;
            for(Player player:playerList){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount+=1;
                }
            }

            if(botCount>1){
                return false;
            }

            return true;
        }

        public Game build() throws InvalidParameterException {
            if(!isValid()){
                throw new InvalidParameterException("Invalid parameter entered");
            }
            return new Game(this);
        }
    }

    public void display(Board board){
        for(int i=0;i<board.getSize();i++){
            List<Cell> row = board.getBoard().get(i);
            for(int j=0;j<board.getSize();j++){
                System.out.print("|");
                if(row.get(j).getCellStatus().equals(CellStatus.EMPTY)){
                    System.out.print(" ");
                }
                else{
                    System.out.print(row.get(j).getPlayer().getSymbol());
                }
            }
            System.out.print("|");
            System.out.println();
        }
    }

    public Move makeNextMove(Board board){
        Player player = playerList.get(nextPlayerIndex);
        return player.makeNextMove(board);
    }

    public boolean validateMove(Cell cell){
        if(board.getBoard().get(cell.getRow()).get(cell.getCol()).getCellStatus().equals(CellStatus.FILLED)){
            return false;
        }
        nextPlayerIndex = (nextPlayerIndex+1)%playerList.size();
        return true;
    }
}
