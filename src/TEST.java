
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class TEST {
    public static void main(String[] args) {
        Board board = new Board();
        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(0, 2), Mark.X);
        board.play(new Move(1, 1), Mark.O);
        board.play(new Move(1, 2), Mark.O);
        board.play(new Move(2, 0), Mark.O);
        board.play(new Move(2, 1), Mark.X);

        CPUPlayer cpuPlayer = new CPUPlayer(Mark.X);
        cpuPlayer.getNextMoveMinMax(board);
    }

    @Test
    public void testBoardWinV() {
        Board board_1 = new Board();
        board_1.play(new Move(0, 0), Mark.X);
        board_1.play(new Move(1, 0), Mark.X);
        board_1.play(new Move(2, 0), Mark.X);
        Assert.assertEquals(100, board_1.evaluate(Mark.X));

        Board board_2 = new Board();
        board_2.play(new Move(0, 1), Mark.O);
        board_2.play(new Move(1, 1), Mark.O);
        board_2.play(new Move(2, 1), Mark.O);
        Assert.assertEquals(-100, board_2.evaluate(Mark.X));

        Board board_3 = new Board();
        board_3.play(new Move(0, 2), Mark.X);
        board_3.play(new Move(1, 2), Mark.X);
        board_3.play(new Move(2, 2), Mark.X);
        Assert.assertEquals(100, board_3.evaluate(Mark.X));

    }

    @Test
    public void testBoardWinH() {
        Board board_1 = new Board();
        board_1.play(new Move(0, 0), Mark.X);
        board_1.play(new Move(0, 1), Mark.X);
        board_1.play(new Move(0, 2), Mark.X);
        Assert.assertEquals(100, board_1.evaluate(Mark.X));

        Board board_2 = new Board();
        board_2.play(new Move(1, 0), Mark.O);
        board_2.play(new Move(1, 1), Mark.O);
        board_2.play(new Move(1, 2), Mark.O);
        Assert.assertEquals(-100, board_2.evaluate(Mark.X));

        Board board_3 = new Board();
        board_3.play(new Move(2, 0), Mark.X);
        board_3.play(new Move(2, 1), Mark.X);
        board_3.play(new Move(2, 2), Mark.X);
        Assert.assertEquals(100, board_3.evaluate(Mark.X));
    }

    @Test
    public void testBoardWinD() {
        Board board_1 = new Board();
        board_1.play(new Move(0, 0), Mark.X);
        board_1.play(new Move(1, 1), Mark.X);
        board_1.play(new Move(2, 2), Mark.X);
        Assert.assertEquals(100, board_1.evaluate(Mark.X));

        Board board_2 = new Board();
        board_2.play(new Move(0, 2), Mark.X);
        board_2.play(new Move(1, 1), Mark.X);
        board_2.play(new Move(2, 0), Mark.X);
        Assert.assertEquals(100, board_2.evaluate(Mark.X));
    }

    @Test
    public void testMinMax_1() {
        Board board = new Board();
        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(0, 2), Mark.X);
        board.play(new Move(1, 1), Mark.O);
        board.play(new Move(1, 2), Mark.O);
        board.play(new Move(2, 0), Mark.O);
        board.play(new Move(2, 1), Mark.X);

        CPUPlayer cpuPlayer = new CPUPlayer(Mark.X);
        ArrayList<Move> nextMove = new ArrayList<>();
        nextMove = cpuPlayer.getNextMoveMinMax(board);
        board.play(nextMove.get(0), Mark.X);

        Assert.assertEquals(board.position(0, 1), Mark.X);

        Assert.assertEquals(10, cpuPlayer.getNumOfExploredNodes());
    }

    @Test
    public void testMinMax_2() {
        Board board = new Board();
        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(0, 1), Mark.X);
        board.play(new Move(1, 1), Mark.O);
        board.play(new Move(1, 2), Mark.O);
        board.play(new Move(2, 1), Mark.O);
        board.play(new Move(2, 2), Mark.X);

        CPUPlayer cpuPlayer = new CPUPlayer(Mark.X);
        ArrayList<Move> nextMove = new ArrayList<>();
        nextMove = cpuPlayer.getNextMoveMinMax(board);
        board.play(nextMove.get(0), Mark.X);

        // Assert.assertEqual( Mark. X,board.position(1, 0)

        Assert.assertEquals(10, cpuPlayer.getNumOfExploredNodes());
    }

    @Test
    public void testAB_1() {
        Board board = new Board();
        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(0, 2), Mark.X);
        board.play(new Move(1, 1), Mark.O);
        board.play(new Move(1, 2), Mark.O);
        board.play(new Move(2, 0), Mark.O);
        board.play(new Move(2, 1), Mark.X);

        CPUPlayer cpuPlayer = new CPUPlayer(Mark.X);
        ArrayList<Move> nextMove = new ArrayList<>();
        nextMove = cpuPlayer.getNextMoveAB(board);
        board.play(nextMove.get(0), Mark.X);

        Assert.assertEquals(board.position(0, 1), Mark.X);

        Assert.assertEquals(7, cpuPlayer.getNumOfExploredNodes());
    }

    @Test
    public void testAB_2() {
        Board board = new Board();
        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(0, 1), Mark.X);
        board.play(new Move(1, 1), Mark.O);
        board.play(new Move(1, 2), Mark.O);
        board.play(new Move(2, 1), Mark.O);
        board.play(new Move(2, 2), Mark.X);

        CPUPlayer cpuPlayer = new CPUPlayer(Mark.X);
        ArrayList<Move> nextMove = new ArrayList<>();
        nextMove = cpuPlayer.getNextMoveAB(board);
        board.play(nextMove.get(0), Mark.X);

        // AB= MinMax

        Assert.assertEquals(10, cpuPlayer.getNumOfExploredNodes());
    }

    @Test
    public void testAB_3() {
        Board board = new Board();
        board.play(new Move(0, 0), Mark.X);
        board.play(new Move(0, 1), Mark.X);
        board.play(new Move(0, 2), Mark.O);
        board.play(new Move(1, 1), Mark.O);

        CPUPlayer cpuPlayer = new CPUPlayer(Mark.X);
        ArrayList<Move> nextMove = new ArrayList<>();
        nextMove = cpuPlayer.getNextMoveMinMax(board);
        board.play(nextMove.get(0), Mark.X);

        // Assert.assertEqual( Mark. X,board.position(1, 0)

        Assert.assertEquals(197, cpuPlayer.getNumOfExploredNodes());
    }

}
