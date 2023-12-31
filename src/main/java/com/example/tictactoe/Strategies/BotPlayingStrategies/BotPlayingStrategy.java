package com.example.tictactoe.Strategies.BotPlayingStrategies;

import com.example.tictactoe.Models.Board;
import com.example.tictactoe.Models.Bot;
import com.example.tictactoe.Models.Move;

public interface BotPlayingStrategy {
    public Move decideMove(Bot bot,Board board);
}
