import java.awt.*;
import java.util.*;

public class DiscardPile
{
    char size = 'S';
    int discard_x = 100, discard_y = 100, width = 50, height = 50;
    Color discard_colour = Color.black;

    Vector pile = new Vector (0, 1);

    // adds cards to the discard pile and sets their coords
    public void push (Card card_arg)
    {
        card_arg.setStart_x (discard_x);
        card_arg.setStart_y (discard_y);
        card_arg.set_is_face_down (false);
        if (pile.size () == 0)
        {
            pile.addElement (card_arg);
        }
        else
        {
            pile.insertElementAt (card_arg, pile.size ());
        }
    }


    // removes cards from the discard pile
    public Card pop ()
    {
        return (Card) pile.remove (pile.size () - 1);
    }


    public void set_size (char char_arg)
    {
        size = char_arg;

        if (char_arg == 'S')
        {
            height = 60;
            width = 42;
        }
        else if (char_arg == 'M')
        {
            height = 80;
            width = 56;
        }
        else if (char_arg == 'L')
        {
            height = 100;
            width = 70;
        }
        else if (char_arg == 'X')
        {
            height = 120;
            width = 84;
        }
    }


    public int get_discard_x ()
    {
        return discard_x;
    }


    public void set_discard_x (int discard_x)
    {
        this.discard_x = discard_x;
    }


    public int get_discard_y ()
    {
        return discard_y;
    }


    public void set_discard_y (int discard_y)
    {
        this.discard_y = discard_y;
    }


    public char get_size ()
    {
        return size;
    }


    public Color getDiscard_colour ()
    {
        return discard_colour;
    }


    public void setDiscard_colour (Color discard_colour)
    {
        this.discard_colour = discard_colour;
    }


    public void draw (Graphics g)
    {
        if (pile.size () == 0)
        {
            g.setColor (discard_colour);
            g.drawRect (discard_x, discard_y, width, height);
        }
        else
        {
            // check later if this part works as is or not
            Card temp_card = (Card) pile.get (pile.size () - 1);
            temp_card.draw (g);
        }
    }


    public void erase (Graphics g)
    {
        g.setColor (Color.white);
        g.fillRect (discard_x, discard_y, width, height);
    }


    public int get_pile_size ()
    {
        return pile.size ();
    }


    public boolean is_empty ()
    {
        return pile.size () == 0;
    }


    // checks if mouse is inside
    public boolean is_point_inside (int x, int y)
    {
        if (x > discard_x && x < (discard_x + width))
        {
            if (y > discard_y && y < (discard_y + height))
            {
                return true;
            }
        }
        return false;
    }
}
