package com.github.severinnitsche.algorithm;

import com.github.severinnitsche.game.abstracts.Game;
import com.github.severinnitsche.game.abstracts.Turn;
import com.github.severinnitsche.game.enums.GameState;
import com.github.severinnitsche.game.interfaces.Player;

import java.util.ArrayList;
import java.util.List;

public class MiniMax {
    public Turn miniMax(Game game) {
        return miniMax(game.clone(), new MiniMaxInformation(null,0,null,game.next())).turn;
    }

    private class MiniMaxInformation {
        Turn turn;
        int depth;
        GameState gameState;
        Player player;

        MiniMaxInformation(Turn turn, int depth, GameState gameState, Player player) {
            this.turn = turn;
            this.depth = depth;
            this.gameState = gameState;
            this.player = player;
        }
    }

    private MiniMaxInformation miniMax(Game game, MiniMaxInformation info) {
        if(!game.next().isEqual(info.player)) throw new IllegalArgumentException();
        if(game.gameState()!=GameState.UNDETERMINED) return new MiniMaxInformation(info.turn,info.depth,game.gameState(),game.next());
        List<MiniMaxInformation> stats = new ArrayList<>();
        for(Turn t : game.possibleMoves()) {
            Game clone = game.clone();
            clone.turn(t);
            MiniMaxInformation nInfo = miniMax(clone,new MiniMaxInformation(t,info.depth+1,game.gameState(),clone.next()));
            nInfo.turn = t;
            stats.add(nInfo);
        }
        MiniMaxInformation best = stats.get(0);
        for(MiniMaxInformation stat : stats) {
            if (best == null) {
                best = stat;
                continue;
            }
            if (stat.gameState.isWorseThan(best.gameState) || stat.gameState.equal(best.gameState) && stat.depth > best.depth)
                best = stat;
        }
        best.gameState = best.gameState.invert();
        return best;
    }
}
