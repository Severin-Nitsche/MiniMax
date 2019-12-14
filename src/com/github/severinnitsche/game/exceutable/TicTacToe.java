package com.github.severinnitsche.game.exceutable;

import com.github.severinnitsche.game.abstracts.Game;
import com.github.severinnitsche.game.abstracts.Turn;
import com.github.severinnitsche.game.enums.GameState;
import com.github.severinnitsche.game.enums.Players;
import com.github.severinnitsche.game.interfaces.Player;
import com.github.severinnitsche.game.imagery.Viewable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static com.github.severinnitsche.game.enums.Players.*;
import static java.util.stream.IntStream.*;

public class TicTacToe extends Game implements Viewable {

    private final Player[][] board;

    private Player next;

    public TicTacToe() {
        addNewPlayer(()->"X");
        addNewPlayer(()->"O");
        board = new Player[3][3];
        next = Players.forName("X");
    }

    @Override
    public boolean turn(Turn t) {
        if(!(t instanceof TicTacToeTurn)) throw new IllegalArgumentException("Expected "+t+" to be of Type "+TicTacToeTurn.class);
        TicTacToeTurn turn = (TicTacToeTurn)t;
        if(!turn.player.getClass().equals(next().getClass())) throw new IllegalArgumentException("Expected "+turn.player+" in "+t+" to be of Type "+next().getClass());
        if(board[turn.x][turn.y] != null) return false;
        if(!turn.player.isEqual(next())) return false;
        if(gameState().isTerminal()) return false;
        board[turn.x][turn.y] = turn.player;
        next = next.next();
        return true;
    }

    @Override
    public Collection<TicTacToeTurn> possibleMoves() {
        ArrayList<TicTacToeTurn> turns = new ArrayList<>();
        for(int x = 0; x<board.length; x++) {
            for(int y = 0; y<board[x].length; y++) {
                if(board[x][y]==null) turns.add(new TicTacToeTurn(next(),x,y));
            }
        }
        return turns;
    }

    @Override
    public GameState gameState() {
        for(int i=0; i<3; i++) {
            if(board[i][i]==null) continue;
            if(board[i][0]==board[i][1] && board[i][1]==board[i][2] || board[0][i]==board[1][i] && board[1][i]==board[2][i]) return board[i][i].isEqual(next())?GameState.WON:GameState.LOST;
        }
        if(board[1][1]!=null && (board[0][0]==board[1][1] && board[1][1]==board[2][2] || board[0][2]==board[1][1] && board[1][1]==board[2][0])) return board[1][1].isEqual(next())?GameState.WON:GameState.LOST;

        boolean tie = true;
        board:
        for(Player[] players : board) {
            for(Player p : players) {
                if(p==null)  {
                    tie = false;
                    break board;
                }
            }
        }
        if(tie) return GameState.TIE;
        else return GameState.UNDETERMINED;
    }

    @Override
    public Player next() {
        return next;
    }

    @Override
    public Game clone() {
        TicTacToe clone = new TicTacToe();
        range(0, 3).forEach(i -> System.arraycopy(board[i], 0, clone.board[i], 0, 3));
        clone.next = next();
        return clone;
    }

    @Override
    public String[] displayScalable() {
        String[] grid = {"line 6 0 6 18 FFFFFF",
                "line 12 0 12 18 FFFFFF",
                "line 0 6 18 6 FFFFFF",
                "line 0 12 18 12 FFFFFF"};
        ArrayList<String> ret = new ArrayList<>(Arrays.asList(grid));
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(board[i][j]==null) continue;
                switch(board[i][j].getName()) {
                    case "X":
                        ret.add("line "+(i*6)+" "+(j*6)+" "+(i*6+6)+" "+(j*6+6)+" FFFFFF");
                        ret.add("line "+(i*6)+" "+(j*6+6)+" "+(i*6+6)+" "+(j*6)+" FFFFFF");
                        break;
                    case "O":
                        ret.add("circle "+(i*6+3)+" "+(j*6+3)+" 3 FFFFFF");
                        break;
                }
            }
        }
        String[] retVal = new String[ret.size()];
        ret.toArray(retVal);
        return retVal;
    }

    @Override
    public double aspectratio() {
        return 1;
    }

    @Override
    public int width() {
        return 18;
    }
}
