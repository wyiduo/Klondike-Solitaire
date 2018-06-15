import java.awt.*;

public class StartLoseScreen extends EndScreen
{
    static Font score_font;
    static FontMetrics score_font_metrics;
    static String str_points;
    static double points_buffer = -1;

    // same as init(), but using a different name since don't want to override init() and cannot call super.init() since static
    public static void start (Graphics g)
    {
        init (g);
        setStr_header ("You Lose");
        score_font = new Font ("SansSerif", Font.PLAIN, 50);
        score_font_metrics = g.getFontMetrics (score_font);

        str_points = "Score: " + points_buffer;
        draw (g);
        draw_rest (g);
    }


    public static void draw_rest (Graphics g)
    {
        g.setFont (score_font);
        g.setColor (Color.black);
        g.drawString (str_points, (int) (AppletFinal.get_screen_x () / 2) - (int) (score_font_metrics.stringWidth (str_points) / 2), (int) (AppletFinal.get_screen_y () / 4) + header_font_metrics.getHeight ());
    }


    public static void draw_all (Graphics g)
    {
        draw (g);
        draw_rest (g);
    }


    public static void erase_rest (Graphics g)
    {
        g.setFont (score_font);
        g.setColor (Color.white);
        g.drawString (str_points, (int) (AppletFinal.get_screen_x () / 2) - (int) (score_font_metrics.stringWidth (str_points) / 2), (int) (AppletFinal.get_screen_y () / 4) + header_font_metrics.getHeight ());
    }


    public static void set_points (double double_arg)
    {
        points_buffer = double_arg;
    }


    public static double get_points ()
    {
        return points_buffer;
    }
}
