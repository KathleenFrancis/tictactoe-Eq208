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

    /**
     * 
     * @param board Le jeu actuelle.
     * @param isMax Boolean qui détemine s'il l'on calcule un min ou un max.
     * @return Retourne la valeur min ou max.
     */
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
            // numExploredNodes++;
        } else {
            return board.getHeuristique(cpu);
        }

        // max & min
        if (isMax) {
            int maxScore = Integer.MIN_VALUE;
            for (Move move : possibleMoves) {
                numExploredNodes++;
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
                numExploredNodes++;
                board.play(move, board.getOppositeMark(cpu));
                int score = minMax(board, true);
                minScore = Math.min(score, minScore);
                // enlevez le coup
                board.play(move, Mark.EMPTY);
            }

            System.out.println(evaluation + " & " + numExploredNodes);
            return (minScore);
        }
    }

    // Retourne la liste des coups possibles. Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board) {
        numExploredNodes = 0;
        ArrayList<Move> possibleMoves = board.possibleMoves();
        int bestScore = Integer.MIN_VALUE;
        ArrayList<Move> bestMoves = new ArrayList<>();
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        for (Move move : possibleMoves) {
            numExploredNodes++;
            board.play(move, cpu);
            int score = alphaBeta(board, false, alpha, beta);
            board.play(move, Mark.EMPTY);
            if (score > bestScore) {
                alpha = score;
                bestScore = score;
                bestMoves.clear();
                bestMoves.add(move);
            } else if (score == bestScore) {
                bestMoves.add(move);
            }
        }
        return bestMoves;

    }

    /**
     * 
     * @param board Le jeu actuelle.
     * @param isMax Boolean qui détemine s'il l'on calcule un min ou un max.
     * @param alpha La valeur associée à chaque noeud Max. Coût du best successeur.
     * @param beta  La valeur associée à chaque noeud Min. Coût du pire successeur.
     * @return Retourne la valeur alpha ou beta.
     */
    private int alphaBeta(Board board, Boolean isMax, int alpha, int beta) {
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
            // numExploredNodes++;
        } else {
            return board.getHeuristique(cpu);
        }

        if (isMax) {
            int maxScore = Integer.MIN_VALUE;
            for (Move move : possibleMoves) {
                numExploredNodes++;
                board.play(move, cpu);
                int score = alphaBeta(board, false, alpha, beta);
                maxScore = Math.max(score, maxScore);
                alpha = Math.max(alpha, maxScore);
                board.play(move, Mark.EMPTY);
                if (alpha > beta) {
                    return alpha;

                }
            }
            return maxScore;
        } else {
            int minScore = Integer.MAX_VALUE;
            for (Move move : possibleMoves) {
                numExploredNodes++;
                board.play(move, board.getOppositeMark(cpu));
                int score = alphaBeta(board, true, alpha, beta);
                minScore = Math.min(score, minScore);
                beta = Math.min(beta, minScore);
                board.play(move, Mark.EMPTY);
                if (beta < alpha) {
                    return beta;
                }
            }
            return minScore;
        }
    }

}
