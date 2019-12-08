package com.github.severinnitsche.game.imagery;

import static com.github.severinnitsche.game.imagery.Imagery.*;
import static java.lang.Integer.*;

public interface Viewable {
    /**
     * @return the color values for the image with the first two indicies specifying the width and height of the picture
     */
    default int[] display(int size) {
        int factor = size / width();
        int[] display = new int[(int)Math.ceil(size * size * aspectratio())];
        for(String s : displayScalable()) {
            String[] strings = s.split("\\W");
            switch(strings[0].toLowerCase()) {
                case "circle":
                    circle(parseInt(strings[1])*factor, parseInt(strings[2])*factor, parseInt(strings[3])*factor,display,size, parseUnsignedInt(strings[4],16));
                    break;
                case "line":
                    line(parseInt(strings[1])*factor, parseInt(strings[2])*factor, parseInt(strings[3])*factor, parseInt(strings[4])*factor,display,size, parseUnsignedInt(strings[5],16));
                    break;
            }
        }
        return display;
    }

    String[] displayScalable();

    double aspectratio();

    int width();
}
