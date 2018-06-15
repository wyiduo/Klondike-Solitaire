import java.awt.*;
import java.util.*;

public class FoundationPile
{
    char foundation_size = 'S', foundation_suit = 'S';
    int foundation_x = 100, foundation_y = 100, width = 50, height = 50;
    final boolean usePresetSuit = false; // determines if the preset suit should be used or a variable suit should be used

    Vector pile = new Vector (0, 1);

    // checks something can be pushed to the foundation pile
    public boolean check_push (Card card_arg)
    {
        // sets constraints
        // checks if the pile is empty
        if (pile.isEmpty ())
        {
            // checks if the card being added is an Ace
            if (card_arg.get_face_value_reference () == 1)
            {
                foundation_suit = card_arg.get_suit ();
                foundation_size = card_arg.get_size ();
                // pile.addElement (card_arg);
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            if (AppletFinal.isDebugMode ())
                System.out.println ("card_arg.get_suit () == foundation_suit = " + (card_arg.get_suit () == foundation_suit));
            // checks if the card being added is the same as the foundation suit
            if (card_arg.get_suit () == foundation_suit)
            {
                // checks if the card being added is equal to the top card of the foundation pile + 1
                if (card_arg.get_face_value_reference () == get_pile_top ().get_face_value_reference () + 1)
                {
                    // pile.insertElementAt (card_arg, pile.size ()); // check only, no pushing
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
    }


    // pushes cards into the foundation pile
    public void push (Card card_arg)
    {
        card_arg.setStart_x (foundation_x);
        card_arg.setStart_y (foundation_y);
        if (pile.size () == 0)
        {
            pile.addElement (card_arg);
        }
        else
        {
            pile.insertElementAt (card_arg, pile.size ());
        }
    }


    public Card pop ()
    {
        return (Card) pile.remove (pile.size () - 1); // it is pile.size() - 1 not pile.size()
    }


    // gets the top card of the foundation pile
    private Card get_pile_top ()
    {
        return (Card) pile.get (pile.size () - 1);
    }


    public void set_foundation_size (char char_arg)
    {
        foundation_size = char_arg;

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


    public int get_foundation_x ()
    {
        return foundation_x;
    }


    public int get_foundation_y ()
    {
        return foundation_y;
    }


    public void set_foundation_x (int int_arg)
    {
        foundation_x = int_arg;
    }


    public void set_foundation_y (int int_arg)
    {
        foundation_y = int_arg;
    }


    public char getFoundation_suit ()
    {
        return foundation_suit;
    }


    public void setFoundation_suit (char foundation_suit)
    {
        this.foundation_suit = foundation_suit;
    }


    public boolean is_point_inside (int x, int y)
    {
        if (x > foundation_x && x < (foundation_x + width))
        {
            if (y > foundation_y && y < (foundation_y + height))
            {
                return true;
            }
        }
        return false;
    }


    // checks to see if the foundation pile is complete
    public boolean check_complete ()
    {
        if (pile.isEmpty ())
        {
            return false;
        }
        return get_pile_top ().get_face_value () == 'K';
    }


    public boolean is_empty ()
    {
        return pile.size () == 0;
    }


    public void draw (Graphics g)
    {
        if (pile.size () == 0)
        {
            g.setColor (Color.black);
            switch (foundation_size)
            {
                case 'S':
                    g.drawRect (foundation_x, foundation_y, 42, 60);
                    break;
                case 'M':
                    g.drawRect (foundation_x, foundation_y, 56, 80);
                    break;
                case 'L':
                    g.drawRect (foundation_x, foundation_y, 70, 100);
                    break;
                case 'X':
                    g.drawRect (foundation_x, foundation_y, 84, 120);
                    break;
            }
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
        g.fillRect (foundation_x, foundation_y, width, height);
    }
}
