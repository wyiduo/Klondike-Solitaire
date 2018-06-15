import java.awt.*;

public abstract class EndScreen
{
    static Font header_font;
    static FontMetrics header_font_metrics;
    static String str_header;

    public static void init (Graphics g)
    {
        header_font = new Font ("Monospaced", Font.PLAIN, 80);
        header_font_metrics = g.getFontMetrics (header_font);
    }


    public static void draw (Graphics g)
    {
        g.setFont (header_font);
        g.setColor (Color.black);
        g.drawString (str_header, (int) (AppletFinal.get_screen_x () / 2) - (int) (header_font_metrics.stringWidth (str_header) / 2), (int) (AppletFinal.get_screen_y () / 4));
    }


    public static void erase (Graphics g)
    {
        g.setFont (header_font);
        g.setColor (Color.white);
        g.drawString (str_header, (int) (AppletFinal.get_screen_x () / 2) - (int) (header_font_metrics.stringWidth (str_header) / 2), (int) (AppletFinal.get_screen_y () / 4));
    }


    public static String getStr_header ()
    {
        return str_header;
    }


    public static void setStr_header (String str_header)
    {
        EndScreen.str_header = str_header;
    }
}
