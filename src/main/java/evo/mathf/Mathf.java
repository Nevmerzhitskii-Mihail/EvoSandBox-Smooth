package evo.mathf;

import evo.Main;

public class Mathf {
    public static double getDeltaX(double x1, double x2){
        double sign = Math.signum(x2 - x1);
        double xt1 = Math.min(x1, x2);
        double xt2 = Math.max(x1, x2);
        double d1 = xt2 - xt1;
        double d2 = Main.screen_width - d1;
        return Math.min(d1, d2) * sign;
    }

    public static double getDeltaY(double y1, double y2){
        double sign = Math.signum(y2 - y1);
        double yt1 = Math.min(y1, y2);
        double yt2 = Math.max(y1, y2);
        double d1 = yt2 - yt1;
        double d2 = Main.screen_width - d1;
        if (d1 == d2) return 0;
        return Math.min(d1, d2) * sign;
    }

    public static double distance(double x1, double y1, double x2, double y2){
        double dx = getDeltaX(x1, x2);
        double dy = getDeltaY(y1, y2);
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double mod(double a, double b){
        double f = (Math.abs(a) - b * (int)(Math.abs(a) / b)) * Math.signum(a) + b;
        return (f - b * (int)(f / b));
    }
}
