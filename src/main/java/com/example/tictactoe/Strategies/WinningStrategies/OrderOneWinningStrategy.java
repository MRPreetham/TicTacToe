package com.example.tictactoe.Strategies.WinningStrategies;

import com.example.tictactoe.Models.Board;
import com.example.tictactoe.Models.Move;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Getter
@Setter
public class OrderOneWinningStrategy implements WinningStrategy{
    private List<HashMap<Character,Integer>> rowSymbolCount = new ArrayList<>();
    private List<HashMap<Character,Integer>> colSymbolCount = new ArrayList<>();
    private HashMap<Character,Integer> leftDiagonalCount = new HashMap<>();
    private HashMap<Character,Integer> rightDiagonalCount = new HashMap<>();
    private int dimension;

    public OrderOneWinningStrategy(int size){
        this.dimension = size;
        for(int i=0;i<dimension;i++){
            rowSymbolCount.add(new HashMap<>());
            colSymbolCount.add(new HashMap<>());
        }
    }

    @Override
    public boolean checkWinner(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Character symbol = move.getPlayer().getSymbol();

        if(!rowSymbolCount.get(row).containsKey(symbol)){
            rowSymbolCount.get(row).put(symbol,1);
        } else {
            rowSymbolCount.get(row).put(symbol,rowSymbolCount.get(row).get(symbol)+1);
        }


        if(!colSymbolCount.get(col).containsKey(symbol)){
            colSymbolCount.get(col).put(symbol,1);
        }
        else{
            colSymbolCount.get(col).put(symbol,colSymbolCount.get(col).get(symbol)+1);
        }


        if(row==col && !leftDiagonalCount.containsKey(symbol)){
            leftDiagonalCount.put(symbol,1);
        } else if (row==col) {
            leftDiagonalCount.put(symbol,leftDiagonalCount.get(symbol)+1);
        }

        if(row+col==dimension-1 && !rightDiagonalCount.containsKey(symbol)){
            rightDiagonalCount.put(symbol,1);
        } else if (row+col==dimension-1) {
            rightDiagonalCount.put(symbol,rightDiagonalCount.get(symbol)+1);
        }

        if(rowSymbolCount.get(row).get(symbol)==dimension || colSymbolCount.get(col).get(symbol)==dimension){
            return true;
        }

        if(row==col && leftDiagonalCount.get(symbol)==dimension){
            return true;
        }

        if(row+col==dimension-1 && rightDiagonalCount.get(symbol)==dimension){
            return true;
        }

        return false;
    }
}
