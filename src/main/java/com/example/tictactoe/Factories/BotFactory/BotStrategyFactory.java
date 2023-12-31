package com.example.tictactoe.Factories.BotFactory;


import com.example.tictactoe.Enums.BotLevel;
import com.example.tictactoe.Strategies.BotPlayingStrategies.BotPlayingStrategy;
import com.example.tictactoe.Strategies.BotPlayingStrategies.EasyBotPlayingStrategy;
import com.example.tictactoe.Strategies.BotPlayingStrategies.HardBotPlayingStrategy;
import com.example.tictactoe.Strategies.BotPlayingStrategies.MediumBotPlayingStrategy;

public class BotStrategyFactory {
   public static BotPlayingStrategy getBotPlayingStrategy(BotLevel botLevel){
       switch (botLevel){
           case EASY -> {
               return new EasyBotPlayingStrategy();
           }
           case MEDIUM -> {
               return new MediumBotPlayingStrategy();
           }
           case HARD -> {
               return new HardBotPlayingStrategy();
           }
       }
       return new EasyBotPlayingStrategy();
   }
}
