package com.github.severinnitsche.game.imagery;

public class Imagery {
    public static void circle(int x, int y, double radius, int[] picture, int width, int color) {
        for(double d=0; d<Math.PI*2; d+=Math.PI*2/radius) {
            int cx = x + (int)(Math.cos(d) * radius);
            int cy = y + (int)(Math.sin(d) * radius);
            picture[width*cy+cx] = color;
        }
    }
    public static void line(int x1, int y1, int x2, int y2, int[] picture, int width, int color) {
        double dx = x2-x1;
        double dy = y2-y1;
        double length = Math.sqrt(dx*dx+dy*dy);
        for(double d=0; d<1; d+=.5/length) {
            int x = x1 + (int)(dx*d);
            int y = y1 + (int)(dy*d);
            picture[width*y+x] = color;
        }
    }
}
