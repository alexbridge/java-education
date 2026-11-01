package hackerrank.algs;

import java.util.*;

public class FourInARow {
    enum Player {
        EMPTY("⚪"), PLAYER1("🎅"), PLAYER2("👨‍");
        private final String avatar;
        Player(String avatar) {
            this.avatar = avatar;
        }

        public String getAvatar() {
            return avatar;
        }
    }

    enum Win {DEF, WIN}

    static class Checker {
        Player player = Player.EMPTY;
        Win win = Win.DEF;
        final int R;
        final int C;

        public Checker(int r, int c) {
            R = r;
            C = c;
        }

        public Player getPlayer() {
            return player;
        }

        public void setPlayer(Player player) {
            this.player = player;
        }

        public Win getWin() {
            return win;
        }

        public void setWin(Win win) {
            this.win = win;
        }

        public int getRow() {
            return R;
        }

        public int getColumn() {
            return C;
        }

        @Override
        public String toString() {
            String out;
            if (this.win == Win.WIN) {
                out = "\033[1;32m" + player.getAvatar() + "\033[0m";
            } else {
                out = player.getAvatar();
            }

            return out;
        }
    }

    static final int MAX_ROW = 6;
    static final int MAX_COLUMN = 7;
    static Checker[][] BOARD;

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            resetBoard();
            runRound();
        }
    }

    static void resetBoard() {
        BOARD = new Checker[MAX_ROW][MAX_COLUMN];
        for (int r = 0; r < BOARD.length; r++) {
            for (int c = 0; c < BOARD[0].length; c++) {
                BOARD[r][c] = new Checker(r, c);
            }
        }
    }

    static void runRound() {
        // Shuffle bots, make initial drops
        List<Player> players = Arrays.asList(Player.PLAYER1, Player.PLAYER2);
        Collections.shuffle(players);
        players.forEach(p -> dropChecker(initialRandomColumn(), p));

        var currentPlayer = players.get(0);
        Checker checker;
        boolean haveWinner = false;
        while (true) {
            // Get next winning combination
            checker = getNextWinStep(currentPlayer, 2);
            if (checker == null) {
                checker = getNextWinStep(currentPlayer, 3);
                if (checker == null) {
                    checker = getNextWinStep(currentPlayer, 4);
                }
            }
            if (checker == null) {
                printBoard();
                System.out.println("Can not get next win step for: " + currentPlayer);
                break;
            }
            dropChecker(checker.getColumn(), currentPlayer);

            haveWinner = checkWinCombination(4);
            if (haveWinner) {
                break;
            }

            currentPlayer = currentPlayer == Player.PLAYER1
                    ? Player.PLAYER2
                    : Player.PLAYER1;

        }

        if (haveWinner) {
            System.out.println("We have wins");
        } else {
            System.out.println("No wins at board");
        }
        printBoard();
    }

    static void printBoard() {
        for (Checker[] inner : BOARD) {
            System.out.println(Arrays.toString(inner));
        }
        System.out.println(" ");
    }

    static int initialRandomColumn() {
        int column;
        do {
            column = new Random().nextInt(BOARD[0].length - 1);
        } while (BOARD[MAX_ROW - 1][column].getPlayer() != Player.EMPTY);
        return column;
    }

    public static boolean dropChecker(int column, Player player) {
        if (column <= BOARD[0].length) {
            for (int r = BOARD.length - 1; r >= 0; r--) {
                if (BOARD[r][column].getPlayer() == Player.EMPTY) {
                    BOARD[r][column].setPlayer(player);
                    return true;
                }
            }
        }
        return false;
    }

    static Checker getNextWinStep(Player player, final int BREADTH) {
        Checker checker;
        var allowedValues = List.of(Player.EMPTY, player);
        for (int row = BOARD.length - 1; row >= 0; row--) {
            for (int column = 0; column <= BOARD[0].length - 1; column++) {

                checker = BOARD[row][column];
                if (checker.getPlayer() == Player.EMPTY || checker.getPlayer() != player) {
                    // Skip empty and not player
                    continue;
                }

                List<Checker> wins = checkByPosition(row, column, BREADTH, allowedValues);
                if (wins != null) {
                    for (var pos : wins) {
                        if (pos.getPlayer() == Player.EMPTY) {
                            return pos;
                        }
                    }
                }
            }
        }
        return null;
    }

    static boolean checkWinCombination(final int BREADTH) {
        Checker checker;
        for (int row = BOARD.length - 1; row >= 0; row--) {
            for (int column = 0; column <= BOARD[0].length - BREADTH; column++) {

                checker = BOARD[row][column];
                if (checker.getPlayer() == Player.EMPTY) {
                    continue;
                }

                List<Checker> wins = checkByPosition(row, column, BREADTH, null);
                if (wins != null) {
                    // Mark as win
                    wins.forEach(w -> w.setWin(Win.WIN));
                    return true;
                }
            }
        }
        return false;
    }

    static List<Checker> checkByPosition(final int R, final int C, final int BREADTH, List<Player> allowed) {
        List<List<Checker>> variants = new ArrayList<>();

        if (R >= BREADTH - 1 && C >= BREADTH - 1) {
            // Up and left
            List<Checker> upAndLeft = new ArrayList<>();
            for (int row = R, col = C; row > R - BREADTH; row--, col--) {
                upAndLeft.add(BOARD[row][col]);
            }
            variants.add(upAndLeft);
        }

        if (R > BREADTH && C < BREADTH) {
            List<Checker> upAndRight = new ArrayList<>();
            for (int row = R, col = C; row > R - BREADTH; row--, col++) {
                upAndRight.add(BOARD[row][col]);
            }
            variants.add(upAndRight);
        }

        Collections.shuffle(variants);

        if (R >= BREADTH - 1) {
            List<Checker> up = new ArrayList<>();
            for (int row = R; row > R - BREADTH; row--) {
                up.add(BOARD[row][C]);
            }
            variants.add(up);
        }

        if (R <= MAX_ROW - BREADTH) {
            // Right and down
            List<Checker> rightAndDown = new ArrayList<>();
            for (int row = R, col = C; row < R + BREADTH; row++, col++) {
                rightAndDown.add(BOARD[row][col]);
            }
            variants.add(rightAndDown);
        }

        if (C < BREADTH) {
            // Right
            List<Checker> right = new ArrayList<>();
            for (int col = C; col < C + BREADTH; col++) {
                right.add(BOARD[R][col]);
            }
            variants.add(right);
        }

        for (var checkers : variants) {
            if (allowed != null && checksAreSame(checkers, allowed)) {
                return checkers;
            }
            if (allowed == null && checksAreSame(checkers)) {
                return checkers;
            }
        }
        return null;
    }

    static boolean checksAreSame(List<Checker> checkers, List<Player> allowed) {
        return checkers.stream().allMatch(c -> allowed.contains(c.getPlayer()));
    }

    static boolean checksAreSame(List<Checker> checkers) {
        Checker first = checkers.get(0);
        return checksAreSame(checkers, List.of(first.getPlayer()));
    }
}
