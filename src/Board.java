import java.util.ArrayList;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class Board {
    private Mark[][] board;

    // Ne pas changer la signature de cette méthode
    public Board() {
        this.board = new Mark[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Mark.EMPTY;
            }
        }
    }

    // Place la pièce 'mark' sur le plateau, à la
    // position spécifiée dans Move
    //
    // Ne pas changer la signature de cette méthode
    public void play(Move m, Mark mark) {

        if (board[m.getRow()][m.getCol()] == Mark.EMPTY) {
            board[m.getRow()][m.getCol()] = mark;
        }

    }

    // retourne 100 pour une victoire
    // -100 pour une défaite
    // 0 pour un match nul
    // Ne pas changer la signature de cette méthode
    public int evaluate(Mark mark) {
        if (win(mark)) {
            return 100;
        }
        if (win(getOppositeMark(mark))) {
            return -100;
        }
        if (isFull()) {
            return 0;
        }

        return 1;
    }

    private boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.board[i][j] == Mark.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private Mark getOppositeMark(Mark mark) {
        if (mark == Mark.O) {
            return Mark.X;
        } else {
            return Mark.O;
        }
    }

    private boolean win(Mark mark) {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == mark && board[1][i] == mark && board[2][i] == mark) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[i][0] == mark && board[i][1] == mark && board[i][2] == mark) {
                return true;
            }
        }

        if (board[1][1] == mark) {
            if (board[0][0] == mark && board[2][2] == mark) {
                return true;
            }

            if (board[0][2] == mark && board[2][0] == mark) {
                return true;
            }

        }

        return false;
    }

    // méthode pour générer les coups possibles
    public ArrayList<Move> possibleMoves() {
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.board[i][j] == Mark.EMPTY) {
                    moves.add(new Move(i, j));
                }
            }
        }
        return moves;
    }

    // attribut un point a chaque
    public int getHeuristique(Mark mark) {
        int plateauCPU = 0;
        int plateauOpposant = 0;

        plateauCPU = countHeuristiquePossibility(mark);
        plateauOpposant = countHeuristiquePossibility(getOppositeMark(mark));
        return plateauCPU - plateauOpposant;
    }

    private int countHeuristiquePossibility(Mark mark) {
        int nbWinning = 0;
        Mark oppositMark = getOppositeMark(mark);
        for (int i = 0; i < 3; i++) {
            if (board[0][i] != oppositMark && board[1][i] != oppositMark && board[2][i] != oppositMark) {
                nbWinning++;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[i][0] != oppositMark && board[i][1] != oppositMark && board[i][2] != oppositMark) {
                nbWinning++;
            }
        }

        if (board[1][1] != oppositMark) {
            if (board[0][0] != oppositMark && board[2][2] != oppositMark) {
                nbWinning++;
            }

            if (board[0][2] != oppositMark && board[2][0] != oppositMark) {
                nbWinning++;
            }
        }
        return nbWinning;
    }

    public void printBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

}
