package minesweeper;

public class Cell {
    private boolean isRevealed;
    private boolean isVisited;
    private boolean isFlagged;
    private boolean isMine;

    int numberOfMines;

    Cell() {
        this.isRevealed = false;
        this.isVisited = false;
        this.isFlagged = false;
        this.isMine = false;
        this.numberOfMines = 0;
    }

    boolean isVisited() {
        return isVisited;
    }

    void setVisited() {
        isVisited = true;
    }

    boolean isFlagged() {
        return isFlagged;
    }

    void setFlagged() {
        isFlagged = !isFlagged;
    }

    boolean isRevealed() {
        return isRevealed;
    }

    void setRevealed() {
        isRevealed = true;
    }

    boolean isMine() {
        return isMine;
    }

    void setMine() {
        isMine = true;
    }

    int getNumberOfMines() {
        return numberOfMines;
    }

    void setNumberOfMines(int number) {
        numberOfMines = number;
    }

    void show() {
        if (isRevealed) {
            if (isMine()) {
                System.out.print("X");
            } else if (getNumberOfMines() > 0) {
                System.out.print(getNumberOfMines());
            } else {
                System.out.print("/");
            }
        } else if (isFlagged()) {
            System.out.print("*");
        } else if (Game.gameOver && isMine()) {
            System.out.print("X");
        } else {
            System.out.print(".");
        }
    }
}
