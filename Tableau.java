import java.awt.*;
import java.util.*;

public class Tableau
{
    char size = 'S';
    int start_x = 100, start_y = 100, width = 50, height = 50;
    Color colour = Color.black;

    Vector pile = new Vector (0, 1);

    // pushes onto the tableau
    public void no_constraint_push (Card card_arg)
    {
        card_arg.setStart_x (start_x);
        card_arg.setStart_y (start_y + (int) (pile.size () * (height / 4)));
        if (pile.size () == 0)
        {
            pile.addElement (card_arg);
        }
        else
        {
            pile.insertElementAt (card_arg, pile.size ());
        }
    }


    // checks if push is allowed
    public boolean check_push (Card card_arg)
    {
        if (pile.isEmpty ())
        {
            // if the pile is empty, checks if the card wanting to be pushed is a K
            if (card_arg.get_face_value_reference () == 13)
            {
                return true;
            }
        }
        // checks the top of pile (this is physically the lowest most card) and sees if the card added to it is equal to it + 1
        else if (get_pile_top ().get_face_value_reference () - 1 == card_arg.get_face_value_reference ())
        {
            // checks if the card being added is of the right suit, red goes on black, black goes on red
            if (get_pile_top ().get_suit () == 'D' || get_pile_top ().get_suit () == 'H')
            {
                if (card_arg.get_suit () == 'C' || card_arg.get_suit () == 'S')
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else if (get_pile_top ().get_suit () == 'C' || get_pile_top ().get_suit () == 'S')
            {
                if (card_arg.get_suit () == 'D' || card_arg.get_suit () == 'H')
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        return false;
    }


    /* public boolean check_push (Vector vect_arg)
    {

    } */

    // pushes cards into the tableau
    public void push (Card card_arg)
    {
        no_constraint_push (card_arg);
    }


    // not used
    public void push (Vector vect_arg)
    {

    }


    // pops cards from the tableau
    public Card pop ()
    {
        return (Card) pile.remove (pile.size () - 1);
    }


    /* public Vector pop ()
    {
        // one idea is to get coords and params and pop out the appropriate Vector of Cards
        // this Vector of Cards will be stored somewhere while it is being held by the mouse
        // if the mouse is released, then the Vector will be pushed back to the appropriate Tableau
    } */

    // checks if the top card of tableau is facedown
    public boolean check_if_pile_top_facedown ()
    {
        return get_pile_top ().get_is_face_down ();
    }


    /* public boolean check_pile_member_is_facedown (int pos)
    {
        return ((Card)pile.get(pos)).get_is_face_down();
    } */

    // sets the top card of tableau faceup
    public void set_pile_top_faceup ()
    {
        get_pile_top ().set_is_face_down (false);
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


    public int getWidth ()
    {
        return width;
    }


    public int getHeight ()
    {
        return height;
    }


    public int getStart_x ()
    {
        return start_x;
    }


    public void setStart_x (int start_x)
    {
        this.start_x = start_x;
    }


    public int getStart_y ()
    {
        return start_y;
    }


    public void setStart_y (int start_y)
    {
        this.start_y = start_y;
    }


    public char getSize ()
    {
        return size;
    }


    public Color getColour ()
    {
        return colour;
    }


    public void setColour (Color colour)
    {
        this.colour = colour;
    }


    // gets the top card on the tableau
    public Card get_pile_top ()
    {
        return (Card) pile.get (pile.size () - 1);
    }


    public boolean is_empty ()
    {
        return pile.size () == 0;
    }


    public int get_pile_size ()
    {
        return pile.size ();
    }


    // this finds if the mouse is inside the top card of tableau
    public boolean is_point_inside (int x, int y)
    {
        if (pile.isEmpty ())
        {
            if (x > start_x && x < start_x + width)
            {
                if (y > start_y && y < start_y + height)
                {
                    return true;
                }
            }
        }
        else
        {
            if (x > get_pile_top ().getStart_x () && x < (get_pile_top ().getStart_x () + get_pile_top ().getWidth ()))
            {
                if (y > get_pile_top ().getStart_y () && y < (get_pile_top ().getStart_y () + get_pile_top ().getHeight ()))
                {
                    return true;
                }
            }
        }
        return false;
    }


    // finds if the mouse is inside the entire tableau, from the first card to the last
    public boolean is_point_inside_full_tableau (int x, int y)
    {
        if (pile.isEmpty ())
        {
            if (x > start_x && x < start_x + width)
            {
                if (y > start_y && y < start_y + height)
                {
                    return true;
                }
            }
        }
        else
        {
            if (x > start_x && x < (get_pile_top ().getStart_x () + get_pile_top ().getWidth ()))
            {
                if (y > start_y && y < (get_pile_top ().getStart_y () + get_pile_top ().getHeight ()))
                {
                    if (AppletFinal.isDebugMode ())
                        System.out.println ("Mouse is inside full tableau.");
                    return true;
                }
            }
        }
        return false;
    }


    // finds if the mouse is inside the area of the tableau which is faceup
    public boolean is_point_inside_faceup_tableau (int x, int y)
    {
        if (pile.isEmpty ())
        {
            if (x > start_x && x < start_x + width)
            {
                if (y > start_y && y < start_y + height)
                {
                    return true;
                }
            }
        }
        else
        {
            int temp_int = pile.size (), first_faceup_index = -1;
            for (int i = 0 ; i < temp_int ; ++i)
            {
                if (!(((Card) pile.get (i)).get_is_face_down ()))
                {
                    first_faceup_index = i;
                    break;
                }
            }

            if (x > start_x && x < (((Card) pile.get (first_faceup_index)).getStart_x () + ((Card) pile.get (first_faceup_index)).getWidth ()))
            {
                if (y > start_y && y < (((Card) pile.get (first_faceup_index)).getStart_y () + ((Card) pile.get (first_faceup_index)).getHeight ()))
                {
                    if (AppletFinal.isDebugMode ())
                        System.out.println ("Mouse is inside faceup tableau.");
                    return true;
                }
            }
        }
        return false;
    }


    // finds which card was clicked by the mouse
    // this will also output error codes
    public int index_of_card_clicked (int x, int y)
    {
        // will output -1 if the mouse is not even in the full tableau
        if (!is_point_inside_full_tableau (x, y))
        {
            return -1;
        }
        else
        {
            // iterates through the entire tableau and finds which card the mouse has clicked
            for (int i = pile.size () - 1 ; i >= 0 ; --i) // >= 0 is more intuitive, but > -1 is faster
            {
                if (((Card) pile.get (i)).is_point_inside (x, y))
                {
                    // will return -2 if the mouse clicks a card that is facedown
                    if (((Card) pile.get (i)).get_is_face_down ())
                    {
                        return -2;
                    }
                    else
                    {
                        return i;
                    }
                }
            }
        }
        return -3;
    }


    public Vector getPile ()
    {
        return pile;
    }


    public void setPile (Vector pile)
    {
        this.pile = pile;
    }


    public void draw (Graphics g)
    {
        if (pile.size () == 0)
        {
            g.setColor (colour);
            g.drawRect (start_x, start_y, width, height);
        }
        else
        {
            for (int i = 0 ; i < pile.size () ; ++i)
            {
                // go back here to see if any other data needs to be specified
                Card temp_card = (Card) pile.get (i); // i will never be equal to pile.size()
                temp_card.draw (g);
            }
        }
    }


    public void erase (Graphics g)
    {
        g.setColor (Color.white);
        g.fillRect (start_x, start_y, width, AppletFinal.get_screen_y () - start_y);
    }
}
