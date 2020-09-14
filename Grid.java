package minesweeper;

import java.util.Random;

public class Grid {
    protected static Cell[][] grid = new Cell[9][9];
    private final int numberOfMines;

    Grid(int numberOfMines) {
        this.numberOfMines = numberOfMines;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    Cell getGrid(int row, int col) {
        return grid[row][col];
    }

    /* Setting mines and numbers on the grid */
    protected void createGrid(int initialCol, int initialRow) {
        Random random = new Random();
        int totalMines = 0;

        /* Set mines */
        while (totalMines < numberOfMines) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            if (!(grid[row][col].isMine() || (row == initialRow && col == initialCol))) {
                grid[row][col].setMine();
                totalMines++;
            }
        }

        /* Set numbers */
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!grid[i][j].isMine()) {
                    grid[i][j].setNumberOfMines(mineCounter(i, j));
                }
            }
        }
    }

    private int mineCounter(int row, int col) {
        int totalMines = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                try {
                    if (grid[row + i][col + j].isMine()) {
                        totalMines++;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
        return totalMines;
    }

    protected void reveal(int col, int row) {
        grid[row][col].setRevealed();
        if (grid[row][col].numberOfMines == 0) {
            floodFill(row, col);
        }
    }

    private static void floodFill(int row, int col) {
        if (row < 0 || row > 8 || col < 0 || col > 8) {
            return;
        }
        if (grid[row][col].isMine()) {
            return;
        }
        if (grid[row][col].isVisited() || grid[row][col].numberOfMines != 0) {
            return;
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                try {
                    if (!grid[row + i][col + j].isMine()) {
                        grid[row + i][col + j].setRevealed();
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }

        grid[row][col].setRevealed();
        grid[row][col].setVisited();

        floodFill(row - 1, col - 1);
        floodFill(row, col - 1);
        floodFill(row + 1, col - 1);
        floodFill(row - 1, col);
        floodFill(row + 1, col);
        floodFill(row - 1, col + 1);
        floodFill(row, col + 1);
        floodFill(row + 1, col + 1);


    }

    protected void setFlag(int col, int row) {
        grid[row][col].setFlagged();
    }

    protected boolean allMinesFlagged() {
        int matchCounter = 0;
        int flagCounter = 0;

        for (Cell[] row : grid) {
            for (Cell cellValue : row) {
                if (cellValue.isFlagged()) {
                    flagCounter++;
                }
                if (cellValue.isMine() && cellValue.isFlagged()) {
                    matchCounter++;
                }
            }
        }
        return matchCounter == numberOfMines && flagCounter == numberOfMines;
    }

    protected void drawGrid() {
        int rowCounter = 1;
        System.out.println("\n |123456789| \n-|---------|");
        for (Cell[] row : grid) {
            System.out.print((rowCounter++) + "|");
            for (Cell cellValue : row) {
                cellValue.show();
            }
            System.out.println("|");
        }
        System.out.println("-|---------|");
    }
}
