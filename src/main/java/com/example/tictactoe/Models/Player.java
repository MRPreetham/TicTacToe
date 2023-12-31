package com.example.tictactoe.Models;

import com.example.tictactoe.Enums.PlayerType;
import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
public class Player {
    private String name;
    private Character symbol;
    private PlayerType playerType;

    Scanner scanner = new Scanner(System.in);

    public Player(String name,Character symbol,PlayerType playerType){
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public Move makeNextMove(Board board){
        System.out.println("Enter row");
        int row = scanner.nextInt();
        System.out.println("Enter col");
        int col = scanner.nextInt();
        Cell cell =  new Cell(row,col);
        return new Move(this,cell);
    }
}
