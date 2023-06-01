import java.util.ArrayList;

import javax.swing.border.Border;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class CPUPlayer {

    // Contient le nombre de noeuds visités (le nombre
    // d'appel à la fonction MinMax ou Alpha Beta)
    // Normalement, la variable devrait être incrémentée
    // au début de votre MinMax ou Alpha Beta.
    private int numExploredNodes;
    private Mark cpu;

    // Le constructeur reçoit en paramètre le
    // joueur MAX (X ou O)
    public CPUPlayer(Mark cpu) {
        this.cpu = cpu;
    }

    // Ne pas changer cette méthode
    public int getNumOfExploredNodes() {
        return numExploredNodes;
    }

    // Retourne la liste des coups possibles. Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveMinMax(Board board) {
        numExploredNodes = 0;

        int maxScore = Integer.MIN_VALUE;
        ArrayList<Move> possibleMoves = board.possibleMoves();
        ArrayList<Move> bestMoves = new ArrayList<>();

        for (Move move : possibleMoves) {
            numExploredNodes++;
            board.play(move, cpu);
            int score = minMax(board, false);
            board.play(move, Mark.EMPTY);
            if (score > maxScore) {
                maxScore = score;
                bestMoves.clear();
                bestMoves.add(move);
            } else if (score == maxScore) {
                bestMoves.add(move);
            }
        }

        return bestMoves;
    }

    private int minMax(Board board, boolean isMax) {
        // condition d'arret
        int evaluation = board.evaluate(cpu);

        if (evaluation != 1) {
            return evaluation;
        }

        ArrayList<Move> possibleMoves = board.possibleMoves();
        if (possibleMoves.isEmpty()) {
            return 0;
        }

        if (numExploredNodes < Integer.MAX_VALUE) {
            numExploredNodes++;
        } else {
            return board.getHeuristique(cpu);
        }

        // max & min
        if (isMax) {
            int maxScore = Integer.MIN_VALUE;
            for (Move move : possibleMoves) {
                board.play(move, cpu);
                int score = minMax(board, false);
                maxScore = Math.max(score, maxScore);
                // enlevez le coup
                board.play(move, Mark.EMPTY);
            }
            return (maxScore);

        } else {
            int minScore = Integer.MAX_VALUE;
            for (Move move : possibleMoves) {
                board.play(move, cpu);
                int score = minMax(board, false);
                minScore = Math.min(score, minScore);
                // enlevez le coup
                board.play(move, Mark.EMPTY);
            }
            return (minScore);
        }
    }

    // Retourne la liste des coups possibles. Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board) {
        numExploredNodes = 0;

        return;

    }

}
