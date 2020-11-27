//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class SOS {
    public static final int INVALID_ROWCOL = -1;
    public static final int ROWCOL_NOT_EMPTY = -2;
    public static final int INVALID_LETTER = -3;
    final char EMPTY = '.';
    private char[][] board;
    private int playerScore1;
    private int playerScore2;
    private int turn;
    private int availableCells;
    private int dimension;

    public SOS(int dimension) {
        this.dimension = dimension;
        this.board = new char[dimension][dimension];

        for(int i = 0; i < dimension; ++i) {
            for(int j = 0; j < dimension; ++j) {
                this.board[i][j] = '.';
            }
        }

        this.playerScore1 = 0;
        this.playerScore2 = 0;
        this.turn = 1;
        this.availableCells = dimension * dimension;
    }

    public int getPlayerScore1() {
        return this.playerScore1;
    }

    public int getPlayerScore2() {
        return this.playerScore2;
    }

    private void increasePlayerScore(int increment) {
        if (this.turn == 1) {
            this.playerScore1 += increment;
        } else {
            this.playerScore2 += increment;
        }

    }

    private void changeTurn() {
        if (this.turn == 1) {
            this.turn = 2;
        } else {
            this.turn = 1;
        }

    }

    public int getTurn() {
        return this.turn;
    }

    public boolean isGameOver() {
        return this.availableCells == 0;
    }

    public int play(char letter, int rowIndex, int columnIndex) {
        int roundScore = 0;
        --rowIndex;
        --columnIndex;
        if (rowIndex >= 0 && rowIndex < this.dimension && columnIndex >= 0 && columnIndex < this.dimension) {
            if (this.board[rowIndex][columnIndex] != '.') {
                return -2;
            } else {
                if (letter == 's') {
                    this.board[rowIndex][columnIndex] = 's';
                    --this.availableCells;
                    if (this.dimension - rowIndex > 2 && this.board[rowIndex + 1][columnIndex] == 'o' && this.board[rowIndex + 2][columnIndex] == 's') {
                        ++roundScore;
                    }

                    if (this.dimension - rowIndex > 2 && columnIndex >= 2 && this.board[rowIndex + 1][columnIndex - 1] == 'o' && this.board[rowIndex + 2][columnIndex - 2] == 's') {
                        ++roundScore;
                    }

                    if (columnIndex >= 2 && this.board[rowIndex][columnIndex - 1] == 'o' && this.board[rowIndex][columnIndex - 2] == 's') {
                        ++roundScore;
                    }

                    if (rowIndex >= 2 && columnIndex >= 2 && this.board[rowIndex - 1][columnIndex - 1] == 'o' && this.board[rowIndex - 2][columnIndex - 2] == 's') {
                        ++roundScore;
                    }

                    if (rowIndex >= 2 && this.board[rowIndex - 1][columnIndex] == 'o' && this.board[rowIndex - 2][columnIndex] == 's') {
                        ++roundScore;
                    }

                    if (rowIndex >= 2 && this.dimension - columnIndex > 2 && this.board[rowIndex - 1][columnIndex + 1] == 'o' && this.board[rowIndex - 2][columnIndex + 2] == 's') {
                        ++roundScore;
                    }

                    if (this.dimension - columnIndex > 2 && this.board[rowIndex][columnIndex + 1] == 'o' && this.board[rowIndex][columnIndex + 2] == 's') {
                        ++roundScore;
                    }

                    if (this.dimension - rowIndex > 2 && this.dimension - columnIndex > 2 && this.board[rowIndex + 1][columnIndex + 1] == 'o' && this.board[rowIndex + 2][columnIndex + 2] == 's') {
                        ++roundScore;
                    }
                } else {
                    if (letter != 'o') {
                        return -3;
                    }

                    this.board[rowIndex][columnIndex] = 'o';
                    --this.availableCells;
                    if (this.dimension - rowIndex > 1 && rowIndex >= 1 && this.board[rowIndex - 1][columnIndex] == 's' && this.board[rowIndex + 1][columnIndex] == 's') {
                        ++roundScore;
                    }

                    if (this.dimension - columnIndex > 1 && columnIndex >= 1 && this.board[rowIndex][columnIndex - 1] == 's' && this.board[rowIndex][columnIndex + 1] == 's') {
                        ++roundScore;
                    }

                    if (this.dimension - rowIndex > 1 && rowIndex >= 1 && this.dimension - columnIndex > 1 && columnIndex >= 1) {
                        if (this.board[rowIndex - 1][columnIndex - 1] == 's' && this.board[rowIndex + 1][columnIndex + 1] == 's') {
                            ++roundScore;
                        }

                        if (this.board[rowIndex - 1][columnIndex + 1] == 's' && this.board[rowIndex + 1][columnIndex - 1] == 's') {
                            ++roundScore;
                        }
                    }
                }

                if (roundScore == 0) {
                    this.changeTurn();
                } else {
                    this.increasePlayerScore(roundScore);
                }

                return roundScore;
            }
        } else {
            return -1;
        }
    }

    public void printBoard() {
        System.out.print("  ");

        int i;
        for(i = 1; i <= this.dimension; ++i) {
            System.out.print(i + " ");
        }

        System.out.println();

        for(i = 0; i < this.dimension; ++i) {
            System.out.print(i + 1 + " ");

            for(int j = 0; j < this.dimension; ++j) {
                System.out.print(this.board[i][j] + " ");
            }

            System.out.println();
        }

    }

    public char getCellContents(int x, int y) {
        return this.board[x][y];
    }

    public int getDimension() {
        return this.dimension;
    }
}
