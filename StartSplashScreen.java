import java.awt.*;

public class StartSplashScreen
{
    static Font start_screen_font, instructions_font;
    static FontMetrics start_screen_metrics, instructions_metrics;
    static String screen_title, instructions;

    public static void init (Graphics g)
    {
        start_screen_font = new Font ("Serif", Font.BOLD, 80);
        start_screen_metrics = g.getFontMetrics (start_screen_font);
        screen_title = "Klondike";

        instructions_font = new Font ("Dialog", Font.PLAIN, 30);
        instructions_metrics = g.getFontMetrics (instructions_font);
        instructions = "Read the instruction manual if you don't know how to play.";
    }


    public static void draw (Graphics g)
    {
        g.setFont (start_screen_font);
        g.setColor (Color.black);
        g.drawString (screen_title, (int) (AppletFinal.get_screen_x () / 2) - (int) (start_screen_metrics.stringWidth (screen_title) / 2), (int) (AppletFinal.get_screen_y () / 4));

        g.setFont (instructions_font);
        g.setColor (Color.red);
        g.drawString (instructions, (int) (AppletFinal.get_screen_x () / 2) - (int) (instructions_metrics.stringWidth (instructions) / 2), (int) (AppletFinal.get_screen_y () / 20 * 7));

        // can not outsource the play button for the splash screen, play button will be coded in main class
    }


    public static void erase (Graphics g)
    {
        g.setFont (start_screen_font);
        g.setColor (Color.white);
        g.drawString (screen_title, (int) (AppletFinal.get_screen_x () / 2) - (int) (start_screen_metrics.stringWidth (screen_title) / 2), (int) (AppletFinal.get_screen_y () / 4));

        g.setFont (instructions_font);
        g.setColor (Color.white);
        g.drawString (instructions, (int) (AppletFinal.get_screen_x () / 2) - (int) (instructions_metrics.stringWidth (instructions) / 2), (int) (AppletFinal.get_screen_y () / 20 * 7));
    }
}
