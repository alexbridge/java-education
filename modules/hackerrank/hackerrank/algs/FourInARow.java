package hackerrank.algs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourInARow {
    enum CheckerValue {EMPTY, PLAYER1, PLAYER2}

    enum CheckerStatus {DEF, WIN}

    static class Checker {
        CheckerValue checkerValue = CheckerValue.EMPTY;
        CheckerStatus checkerStatus = CheckerStatus.DEF;

        public CheckerValue getCheckerValue() {
            return checkerValue;
        }

        public void setCheckerValue(CheckerValue checkerValue) {
            this.checkerValue = checkerValue;
        }

        public void setCheckerStatus(CheckerStatus checkerStatus) {
            this.checkerStatus = checkerStatus;
        }

        @Override
        public String toString() {
            if (this.checkerStatus == CheckerStatus.WIN) {
                return "🔘" + " " + this.checkerValue.name();
            }
            return this.checkerValue.name();
        }
    }

    static final int MAX_ROW = 5;
    static final int MAX_COLUMN = 6;
    static Checker[][] board;

    static {
        board = new Checker[MAX_ROW + 1][MAX_COLUMN + 1];
        for (Checker[] inner : board) {
            for (int i = 0; i < inner.length; i++) {
                inner[i] = new Checker();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Initial board");
        printBoard();

        dropChecker(2, CheckerValue.PLAYER1);
        dropChecker(3, CheckerValue.PLAYER2);
        dropChecker(2, CheckerValue.PLAYER1);
        dropChecker(4, CheckerValue.PLAYER2);
        dropChecker(2, CheckerValue.PLAYER1);
        dropChecker(2, CheckerValue.PLAYER1);

        if (checkWin()) {
            System.out.println("We have wins");
        } else {
            System.out.println("No wins at board");
        }
        printBoard();
    }

    static void printBoard() {
        for (Checker[] inner : board) {
            System.out.println(Arrays.toString(inner));
        }
        System.out.println("");
    }

    public static boolean dropChecker(int column, CheckerValue checkerValue) {
        if (column <= board[0].length) {
            for (int i = board.length - 1, c = column - 1; i >= 0; i--) {
                if (board[i][c].getCheckerValue() == CheckerValue.EMPTY) {
                    board[i][c].setCheckerValue(checkerValue);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkWin() {
        for (int row = board.length - 1; row >= 3; row--) {
            for (int column = 0; column <= board[0].length - 4; column++) {
                List<Checker> wins = checkByPosition(row, column);
                if (wins != null && wins.size() > 0) {
                    wins.forEach(r -> r.setCheckerStatus(CheckerStatus.WIN));
                    return true;
                }
            }
        }
        return false;
    }

    static List<Checker> checkByPosition(int row, int column) {
        List<List<Checker>> variants = new ArrayList<>();
        // Right
        variants.add(List.of(
                board[row][column],
                board[row][column + 1],
                board[row][column + 2],
                board[row][column + 3]
        ));
        if (row >= 3) {
            // Up
            variants.add(List.of(
                    board[row][column],
                    board[row - 1][column],
                    board[row - 2][column],
                    board[row - 3][column]
            ));
            // Up and right
            variants.add(List.of(
                    board[row][column],
                    board[row - 1][column + 1],
                    board[row - 2][column + 2],
                    board[row - 3][column + 3]
            ));
            if (column >= 3) {
                // Up and left
                variants.add(List.of(
                        board[row][column],
                        board[row - 1][column - 1],
                        board[row - 2][column - 2],
                        board[row - 3][column - 3]
                ));
            }
        }

        for (var checkers : variants) {
            if (checksAreSame(checkers)) {
                return checkers;
            }
        }
        return null;
    }

    static boolean checksAreSame(List<Checker> checkers) {
        Checker first = checkers.get(0);
        if (first.getCheckerValue() == CheckerValue.EMPTY) {
            return false;
        }
        return checkers.stream().allMatch(c -> c.getCheckerValue() == first.getCheckerValue());
    }
}
