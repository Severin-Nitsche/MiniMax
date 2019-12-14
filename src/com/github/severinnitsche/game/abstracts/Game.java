package com.github.severinnitsche.game.abstracts;

import com.github.severinnitsche.game.enums.GameState;
import com.github.severinnitsche.game.interfaces.Player;

import java.util.Collection;

public abstract class Game {
    public abstract boolean turn(Turn t);

    public abstract Collection<? extends Turn> possibleMoves();

    /**
     *
     * @return the GameState for the player who's turn is or would be next
     */
    public abstract GameState gameState();

    /**
     *
     * @return the Player who's turn is next
     */
    public abstract Player next();

    @Override public abstract Game clone();
}
