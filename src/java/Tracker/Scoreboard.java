package Tracker;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maple
 */
public class Scoreboard {
    public int round;
    public String currentWinner;
    public String move;
    public String servletMove;
    public int[] scores;
    
    public Scoreboard() {
        round = 0;
        currentWinner = "";
        move = "";
        servletMove = "";
        scores = new int[]{0, 0, 0, 0};
    }
    
    public void concludeMatch() {
        if(round % 5 == 0) {
            if(scores[0] > 3) {
                scores[2]++;
            } else {
                scores[3]++;
            }
            scores[0] = 0;
            scores[1] = 0;
        }
    }
    
    public void playOutcome(int ganada) {
        if(ganada <= 1) {
            scores[ganada]++;
        }
        /*
        0 = ganada 
        1 = empatada
        Ya que no se cuentan derrotas
        */  
    }
    
    public void nextRound() {
        round++;
    }
}
