import java.awt.*;

public class StartWinScreen extends EndScreen
{
    static Font score_font;
    static FontMetrics score_font_metrics;
    static String str_points, str_moves_left;
    static int moves_left_buffer = -1;
    static double points_buffer = -1;

    public static void start (Graphics g)
    {
        init (g);
        setStr_header ("You Win");
        score_font = new Font ("SansSerif", Font.PLAIN, 50);
        score_font_metrics = g.getFontMetrics (score_font);

        str_points = "Score: " + points_buffer;
        str_moves_left = "Moves left: " + moves_left_buffer;
        draw (g);
        draw_rest (g);
    }


    public static void draw_rest (Graphics g)
    {
        g.setFont (score_font);
        g.setColor (Color.black);
        g.drawString (str_points, (int) (AppletFinal.get_screen_x () / 2) - (int) (score_font_metrics.stringWidth (str_points) / 2), (int) (AppletFinal.get_screen_y () / 4) + header_font_metrics.getHeight ());
        if (AppletFinal.getDifficulty () != 0)
            g.drawString (str_moves_left, (int) (AppletFinal.get_screen_x () / 2) - (int) (score_font_metrics.stringWidth (str_moves_left) / 2), (int) (AppletFinal.get_screen_y () / 4) + header_font_metrics.getHeight () + score_font_metrics.getHeight ());
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
        if (AppletFinal.getDifficulty () != 0)
            g.drawString (str_moves_left, (int) (AppletFinal.get_screen_x () / 2) - (int) (score_font_metrics.stringWidth (str_moves_left) / 2), (int) (AppletFinal.get_screen_y () / 4) + header_font_metrics.getHeight () + score_font_metrics.getHeight ());
    }


    public static void set_points (double double_arg)
    {
        points_buffer = double_arg;
    }


    public static double get_points ()
    {
        return points_buffer;
    }


    public static int get_moves_left ()
    {
        return moves_left_buffer;
    }


    public static void set_moves_left (int int_arg)
    {
        moves_left_buffer = int_arg;
    }
}
