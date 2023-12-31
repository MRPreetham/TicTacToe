package com.example.tictactoe.Strategies.WinningStrategies;

import com.example.tictactoe.Models.Board;
import com.example.tictactoe.Models.Move;

public interface WinningStrategy {
    public boolean checkWinner(Move move);
}
