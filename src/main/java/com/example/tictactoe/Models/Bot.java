package com.example.tictactoe.Models;

import com.example.tictactoe.Enums.BotLevel;
import com.example.tictactoe.Enums.PlayerType;
import com.example.tictactoe.Factories.BotFactory.BotStrategyFactory;
import com.example.tictactoe.Strategies.BotPlayingStrategies.BotPlayingStrategy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bot extends Player{
    private BotLevel botLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String name, Character symbol,BotLevel botLevel){
        super(name,symbol,PlayerType.BOT);
        this.botLevel = botLevel;
    }

    public void getBotPlayingStrategy(){
        botPlayingStrategy =  BotStrategyFactory.getBotPlayingStrategy(botLevel);
    }


    public Move makeNextMove(Board board){
        getBotPlayingStrategy();
        return botPlayingStrategy.decideMove(this,board);
    }

}
