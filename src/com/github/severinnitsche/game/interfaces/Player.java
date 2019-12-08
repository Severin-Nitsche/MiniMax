package com.github.severinnitsche.game.interfaces;

import com.github.severinnitsche.game.enums.Players;

import java.util.ArrayList;

public interface Player extends Cycleable {
    String getName();
    default boolean isEqual(Player p) {
        return p.getName().equals(getName());
    }
    default Player next() {
        ArrayList<Player> players = new ArrayList<>(Players.players());
        return players.get((players.indexOf(this)+1)%players.size());
    }
}
