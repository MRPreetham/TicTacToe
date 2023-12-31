package com.example.tictactoe;
import com.example.tictactoe.Controllers.GameController;
import com.example.tictactoe.Enums.BotLevel;
import com.example.tictactoe.Enums.CellStatus;
import com.example.tictactoe.Enums.GameStatus;
import com.example.tictactoe.Enums.PlayerType;
import com.example.tictactoe.Exceptions.InvalidParameterException;
import com.example.tictactoe.Models.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@SpringBootApplication
public class TicTacToeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TicTacToeApplication.class, args);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter size of the board");
        int dimension = scanner.nextInt();

        System.out.println("Do you need Bot in the game? y/n");
        String botExist = scanner.next();

        List<Player> playerList = new ArrayList<>();

        int numberOfPlayer = dimension-1;

        if(botExist.charAt(0)=='y'){
            numberOfPlayer = dimension-2;
            System.out.println("Enter you Bot name");
            String name = scanner.next();
            System.out.println("Choose your symbol");
            String symbol = scanner.next();
            Player bot = new Bot(name,symbol.charAt(0),BotLevel.EASY);
            playerList.add(bot);
        }

        for(int i=0;i<numberOfPlayer;i++){
            System.out.println("Enter the player name");
            String name = scanner.next();
            System.out.println("Choose your symbol");
            String symbol = scanner.next();
            Player player = new Player(name,symbol.charAt(0), PlayerType.HUMAN);
            playerList.add(player);
        }


        GameController gameController = new GameController(dimension,playerList);

        Game game;

        try{
           game = gameController.CreateGame();
        } catch (InvalidParameterException e) {
            System.out.println("Seems like you gave invalid params. Update those.");
            return;
        }

        while(game.getGameStatus().equals(GameStatus.IN_PROGRESS)){

            System.out.println("It's "+game.getPlayerList().get(game.getNextPlayerIndex()).getName()+" move");
            Move move = gameController.makeNextMove(game.getBoard());

            if(!gameController.validateMove(move.getCell())){
                System.out.println("Invalid move try again");
                continue;
            }
            gameController.getGame().getBoard().getBoard()
                    .get(move.getCell().getRow()).get(move.getCell().getCol())
                    .setCellStatus(CellStatus.FILLED);
            gameController.getGame().getBoard().getBoard().
                    get(move.getCell().getRow()).get(move.getCell().getCol())
                    .setPlayer(move.getPlayer());

            gameController.getGame().getMoveList().add(move);

            if(gameController.getGame().getWinningStrategy().checkWinner(move)){
                gameController.getGame().setGameStatus(GameStatus.ENDED);
                gameController.getGame().setWinner(move.getPlayer());
                System.out.println("Winner is "+gameController.getGame().getWinner().getName());
            }
            if(gameController.getGame().getMoveList().size()==dimension*dimension){
                gameController.getGame().setGameStatus(GameStatus.DRAW);
                System.out.println("Game ended in Draw");
            }
            gameController.display(gameController.getGame().getBoard());
        }
    }

}
