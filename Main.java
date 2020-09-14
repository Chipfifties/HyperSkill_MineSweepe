package minesweeper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many mines do you want on the field? ");
        int numberOfMines = Integer.parseInt(sc.nextLine());
        Grid grid = new Grid(numberOfMines);
        Game game = new Game(grid);

        game.startGame();
    }
}






