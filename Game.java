package minesweeper;

import java.util.Scanner;

public class Game {
    private static final Scanner sc = new Scanner(System.in);
    private final Grid grid;
    static boolean gameOver;

    Game(Grid grid) {
        this.grid = grid;
        gameOver = false;
    }

    private void setGameOver() {
        gameOver = true;
    }

    protected void startGame() {
        grid.drawGrid();
        System.out.print("Set/delete mines marks (x and y coordinates): ");
        String[] input = sc.nextLine().trim().split("\\s");
        int col = Integer.parseInt(input[0]) - 1;
        int row = Integer.parseInt(input[1]) - 1;
        grid.createGrid(col, row);
        grid.reveal(col, row);

        while (!gameOver) {
            grid.drawGrid();
            System.out.print("Set/delete mines marks (x and y coordinates): ");
            input = sc.nextLine().trim().split("\\s");
            col = Integer.parseInt(input[0]) - 1;
            row = Integer.parseInt(input[1]) - 1;

            switch (input[2]) {
                case "free":
                    if (grid.getGrid(row, col).isMine()) {
                        setGameOver();
                        grid.drawGrid();
                        System.out.println("You stepped on a mine and failed!");
                    }
                    grid.reveal(col, row);
                    break;
                case "mine":
                    grid.setFlag(col, row);
                    if (grid.allMinesFlagged()) {
                        grid.drawGrid();
                        System.out.println("Congratulations! You found all mines!");
                        setGameOver();
                    }
                    break;
                default:
                    break;
            }
        }
    }
}


