import java.awt.*;

public class StartDifficultyScreen
{
    static Font header_font;
    static FontMetrics header_font_metrics;
    static String str_header;

    // Initializes the difficulty screen's variables
    public static void init (Graphics g)
    {
        header_font = new Font ("Monospaced", Font.PLAIN, 70);
        header_font_metrics = g.getFontMetrics (header_font);
        str_header = "Set Time Limit";
    }


    public static void draw (Graphics g)
    {
        g.setFont (header_font);
        g.setColor (Color.black);
        g.drawString (str_header, (int) (AppletFinal.get_screen_x () / 2) - (int) (header_font_metrics.stringWidth (str_header) / 2), (int) (AppletFinal.get_screen_y () / 4));

        // can not outsource the combo box
    }


    public static void erase (Graphics g)
    {
        g.setFont (header_font);
        g.setColor (Color.white);
        g.drawString (str_header, (int) (AppletFinal.get_screen_x () / 2) - (int) (header_font_metrics.stringWidth (str_header) / 2), (int) (AppletFinal.get_screen_y () / 4));

    }
}
