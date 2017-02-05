package jp.ac.uryukyu.ie.e165739;

import org.junit.Test;

import static org.junit.Assert.*;

public class SolverTest {
    @Test
    public void show() throws Exception {
        Solver solver = new Solver();
        solver.init();
        solver.show();
        solver.black_white_turn();
        solver.shift();
        solver.black_white_turn();
        solver.show();
        solver.shift();
        solver.black_white_turn();
        solver.show();
        solver.shift();
        solver.black_white_turn();
        solver.show();
        solver.shift();
        solver.black_white_turn();

        assertEquals(1,1);
    }

}