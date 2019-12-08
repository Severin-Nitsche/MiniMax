package com.github.severinnitsche.game.enums;

import com.github.severinnitsche.game.interfaces.Cycleable;
import com.github.severinnitsche.game.interfaces.Player;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public enum Players implements com.github.severinnitsche.game.interfaces.Player, Cycleable {
    ;

    private static Map<String, Player> playerMap = new TreeMap<>();

    public static Player forName(String name) {
        return playerMap.get(name);
    }

    public static void addNewPlayer(Player player) {
        if(!playerMap.containsKey(player.getName())) playerMap.put(player.getName(),player);
    }

    public static Collection<Player> players() {
        return playerMap.values();
    }

    @Override
    public Player next() {
        return null;
    }

    @Override
    public String getName() {
        return toString();
    }
}
