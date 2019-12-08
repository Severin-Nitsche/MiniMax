package com.github.severinnitsche.game.exceutable;

import com.github.severinnitsche.game.abstracts.Turn;
import com.github.severinnitsche.game.interfaces.Player;

public class TicTacToeTurn extends Turn {
    Player player;
    int x;
    int y;

    public TicTacToeTurn(Player p, int x, int y) {
        player = p;
        this.x = x;
        this.y = y;
    }
}
