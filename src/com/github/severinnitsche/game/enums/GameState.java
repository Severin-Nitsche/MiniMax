package com.github.severinnitsche.game.enums;

public enum GameState {
    TIE,WON,LOST,NOTWON,NOTLOST,UNDETERMINED;

    public boolean equal(GameState o) {
        //condition
        switch(o) {
            case NOTLOST:
            case NOTWON:
                return (this==NOTLOST||this==NOTWON);
            default:
                return o==this;
        }
    }

    /**
     *
     * @param o The GameState to compare this to
     * @return whether this is worse than o
     */
    public boolean isWorseThan(GameState o) {
        switch(this) {
            case WON:
            case UNDETERMINED:
                return false;
            case TIE:
            case NOTWON:
            case NOTLOST:
                return o==WON;
            case LOST:
                return true;
        }
        return false;
    }

    public GameState invert() {
        switch(this) {
            case WON:
                return LOST;
            case LOST:
                return WON;
            default:
                return this;
        }
    }

    public boolean isTerminal() {
        switch(this) {
            case TIE:
            case WON:
            case LOST:
                return true;
            case NOTWON:
            case NOTLOST:
            case UNDETERMINED:
                return false;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}
