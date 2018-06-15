import java.awt.*;
import java.util.Vector;

public class StartMainGame
{
    static String score_str, moves_left_str;
    static Font score_font, moves_left_font;
    static FontMetrics score_metrics, moves_left_metrics;

    public static FoundationPile foundation_pile_1, foundation_pile_2, foundation_pile_3, foundation_pile_4;
    public static Deck std_deck;
    public static DiscardPile d_pile;
    public static Tableau tableau_1, tableau_2, tableau_3, tableau_4, tableau_5, tableau_6, tableau_7;
    static Vector card_buffer = new Vector (0, 1); // this is for when the mouse picks up a card(s), the card(s) will go into this buffer and this buffer will be flushed when cards are placed
    // note: card_buffer will be a queue

    final static boolean deck_shuffle = true; // should be true, unless you are debugging

    // initializes the main game's variables
    public static void init (Graphics g)
    {
        // G.U.I.
        score_str = "Score";
        moves_left_str = "Moves Left";

        score_font = new Font ("Dialog", Font.PLAIN, 40);
        moves_left_font = new Font ("Dialog", Font.PLAIN, 40);

        score_metrics = g.getFontMetrics (score_font);
        moves_left_metrics = g.getFontMetrics (moves_left_font);

        // foundation piles
        foundation_pile_1 = new FoundationPile ();
        foundation_pile_2 = new FoundationPile ();
        foundation_pile_3 = new FoundationPile ();
        foundation_pile_4 = new FoundationPile ();

        // the scaling of the foundation piles will be the first foundation pile will be at screen_x / 2 and screen_y * 12 / 96
        // each foundation pile afterwards will be screen_x + foundation_x / 16 and the same screen_y
        // keep in mind that there is a line of code at the end of set_foundation_x() which scales, assuming that all foundations will be the same size

        foundation_pile_1.set_foundation_x ((int) AppletFinal.get_screen_x () / 2);
        foundation_pile_1.set_foundation_y ((int) AppletFinal.get_screen_y () / 8);
        foundation_pile_1.set_foundation_size ('X');
        // foundation_pile_1.setFoundation_suit('D');

        foundation_pile_2.set_foundation_x ((int) AppletFinal.get_screen_x () / 2 + (((int) foundation_pile_2.get_foundation_x () / 16) * 1) + (foundation_pile_2.get_foundation_x () * 1));
        foundation_pile_2.set_foundation_y ((int) AppletFinal.get_screen_y () / 8);
        foundation_pile_2.set_foundation_size ('X');
        // foundation_pile_1.setFoundation_suit('C');

        foundation_pile_3.set_foundation_x ((int) AppletFinal.get_screen_x () / 2 + (((int) foundation_pile_3.get_foundation_x () / 16) * 2) + (foundation_pile_3.get_foundation_x () * 2));
        foundation_pile_3.set_foundation_y ((int) AppletFinal.get_screen_y () / 8);
        foundation_pile_3.set_foundation_size ('X');
        // foundation_pile_1.setFoundation_suit('H');

        foundation_pile_4.set_foundation_x ((int) AppletFinal.get_screen_x () / 2 + (((int) foundation_pile_4.get_foundation_x () / 16) * 3) + (foundation_pile_4.get_foundation_x () * 3));
        foundation_pile_4.set_foundation_y ((int) AppletFinal.get_screen_y () / 8);
        foundation_pile_4.set_foundation_size ('X');
        // foundation_pile_1.setFoundation_suit('S');

        // deck and discard pile
        std_deck = new Deck ();
        std_deck.set_deck_colour (Color.blue);
        std_deck.set_size ('X');
        std_deck.set_deck_x ((int) (AppletFinal.get_screen_x () / 4) - (std_deck.get_deck_x () * 2));
        std_deck.set_deck_y ((int) AppletFinal.get_screen_y () / 8);
        std_deck.init_std_deck ();
        // checks whether to shuffle the deck or not
        if (deck_shuffle)
            std_deck.shuffle ();

        final int DECK_DISCARD_SPACING = 5; // should be const, but java doesn't like const
        d_pile = new DiscardPile ();
        d_pile.set_size ('X');
        d_pile.setDiscard_colour (Color.black);
        d_pile.set_discard_x ((int) (AppletFinal.get_screen_x () / 4) + DECK_DISCARD_SPACING);
        d_pile.set_discard_y ((int) AppletFinal.get_screen_y () / 8);

        // tableaus
        tableau_1 = new Tableau ();
        tableau_2 = new Tableau ();
        tableau_3 = new Tableau ();
        tableau_4 = new Tableau ();
        tableau_5 = new Tableau ();
        tableau_6 = new Tableau ();
        tableau_7 = new Tableau ();

        tableau_1.set_size ('X');
        tableau_1.setColour (Color.black);
        tableau_2.set_size ('X');
        tableau_2.setColour (Color.black);
        tableau_3.set_size ('X');
        tableau_3.setColour (Color.black);
        tableau_4.set_size ('X');
        tableau_4.setColour (Color.black);
        tableau_5.set_size ('X');
        tableau_5.setColour (Color.black);
        tableau_6.set_size ('X');
        tableau_6.setColour (Color.black);
        tableau_7.set_size ('X');
        tableau_7.setColour (Color.black);

        // this code below assumes that all tableau widths are the same as all width calculations for the tableaus are seeded off of tableau_1
        final int CARD_HEIGHT = 120, TAB_FOUND_SPACING = 30, TAB_TAB_SPACING = 20; // suppose to be const, but Java doesn't like const variables
        tableau_1.setStart_x ((int) (AppletFinal.get_screen_x () - (tableau_1.getWidth () * 7) - (TAB_TAB_SPACING * 7)) / 2);
        tableau_1.setStart_y ((int) AppletFinal.get_screen_y () / 8 + CARD_HEIGHT + TAB_FOUND_SPACING);
        tableau_2.setStart_x (((int) (AppletFinal.get_screen_x () - (tableau_1.getWidth () * 7) - (TAB_TAB_SPACING * 6)) / 2) + tableau_1.getWidth () + TAB_TAB_SPACING);
        tableau_2.setStart_y ((int) AppletFinal.get_screen_y () / 8 + CARD_HEIGHT + TAB_FOUND_SPACING);
        tableau_3.setStart_x (((int) (AppletFinal.get_screen_x () - (tableau_1.getWidth () * 7) - (TAB_TAB_SPACING * 6)) / 2) + ((tableau_1.getWidth () + TAB_TAB_SPACING) * 2));
        tableau_3.setStart_y ((int) AppletFinal.get_screen_y () / 8 + CARD_HEIGHT + TAB_FOUND_SPACING);
        tableau_4.setStart_x (((int) (AppletFinal.get_screen_x () - (tableau_1.getWidth () * 7) - (TAB_TAB_SPACING * 6)) / 2) + ((tableau_1.getWidth () + TAB_TAB_SPACING) * 3));
        tableau_4.setStart_y ((int) AppletFinal.get_screen_y () / 8 + CARD_HEIGHT + TAB_FOUND_SPACING);
        tableau_5.setStart_x (((int) (AppletFinal.get_screen_x () - (tableau_1.getWidth () * 7) - (TAB_TAB_SPACING * 6)) / 2) + ((tableau_1.getWidth () + TAB_TAB_SPACING) * 4));
        tableau_5.setStart_y ((int) AppletFinal.get_screen_y () / 8 + CARD_HEIGHT + TAB_FOUND_SPACING);
        tableau_6.setStart_x (((int) (AppletFinal.get_screen_x () - (tableau_1.getWidth () * 7) - (TAB_TAB_SPACING * 6)) / 2) + ((tableau_1.getWidth () + TAB_TAB_SPACING) * 5));
        tableau_6.setStart_y ((int) AppletFinal.get_screen_y () / 8 + CARD_HEIGHT + TAB_FOUND_SPACING);
        tableau_7.setStart_x (((int) (AppletFinal.get_screen_x () - (tableau_1.getWidth () * 7) - (TAB_TAB_SPACING * 6)) / 2) + ((tableau_1.getWidth () + TAB_TAB_SPACING) * 6));
        tableau_7.setStart_y ((int) AppletFinal.get_screen_y () / 8 + CARD_HEIGHT + TAB_FOUND_SPACING);

        // initalizes the tableaus
        init_tableaus (g);
    }


    // initializes the tableaus
    public static void init_tableaus (Graphics g)
    {
        Card temp_card = std_deck.dealCard (-1);
        temp_card.set_is_face_down (false);
        tableau_1.no_constraint_push (temp_card);

        tableau_2.no_constraint_push (std_deck.dealCard (-1));
        temp_card = std_deck.dealCard (-1);
        temp_card.set_is_face_down (false);
        tableau_2.no_constraint_push (temp_card);

        tableau_3.no_constraint_push (std_deck.dealCard (-1));
        tableau_3.no_constraint_push (std_deck.dealCard (-1));
        temp_card = std_deck.dealCard (-1);
        temp_card.set_is_face_down (false);
        tableau_3.no_constraint_push (temp_card);

        // tableau 4 needs a lot more cards than tableau 1 so for loops make it easier
        for (int i = 0 ; i < 3 ; ++i)
        {
            tableau_4.no_constraint_push (std_deck.dealCard (-1));
        }
        temp_card = std_deck.dealCard (-1);
        temp_card.set_is_face_down (false);
        tableau_4.no_constraint_push (temp_card);

        for (int i = 0 ; i < 4 ; ++i)
        {
            tableau_5.no_constraint_push (std_deck.dealCard (-1));
        }
        temp_card = std_deck.dealCard (-1);
        temp_card.set_is_face_down (false);
        tableau_5.no_constraint_push (temp_card);

        for (int i = 0 ; i < 5 ; ++i)
        {
            tableau_6.no_constraint_push (std_deck.dealCard (-1));
        }
        temp_card = std_deck.dealCard (-1);
        temp_card.set_is_face_down (false);
        tableau_6.no_constraint_push (temp_card);

        for (int i = 0 ; i < 6 ; ++i)
        {
            tableau_7.no_constraint_push (std_deck.dealCard (-1));
        }
        temp_card = std_deck.dealCard (-1);
        temp_card.set_is_face_down (false);
        tableau_7.no_constraint_push (temp_card);
    }


    // erases and draws
    public static void update (Graphics g)
    {
        erase (g);
        draw (g);
    }


    public static void draw (Graphics g)
    {
        // background
        g.setColor (Color.green);
        g.fillRect (0, 0, AppletFinal.get_screen_x (), AppletFinal.get_screen_y ());

        // G.U.I.
        g.setFont (score_font);
        g.setColor (Color.black);
        g.drawString (score_str, (int) (AppletFinal.get_screen_x () / 8) - (int) (score_metrics.stringWidth (score_str) / 2), (int) (AppletFinal.get_screen_y () / 12));

        g.setFont (moves_left_font);
        g.setColor (Color.black);
        if (AppletFinal.getDifficulty () != 0)
            g.drawString (moves_left_str, (int) (AppletFinal.get_screen_x () * 1 / 2) - (int) (moves_left_metrics.stringWidth (moves_left_str) / 2), (int) (AppletFinal.get_screen_y () / 12));

        // g.drawLine (0, (int) (AppletFinal.get_screen_x () / 16), AppletFinal.get_screen_x (), (int) (AppletFinal.get_screen_x () / 16));
        // design choice to not draw the line

        // foundation piles
        foundation_pile_1.draw (g);
        foundation_pile_2.draw (g);
        foundation_pile_3.draw (g);
        foundation_pile_4.draw (g);

        // deck and discard pile
        std_deck.draw (g);
        d_pile.draw (g);

        // tableaus
        tableau_1.draw (g);
        tableau_2.draw (g);
        tableau_3.draw (g);
        tableau_4.draw (g);
        tableau_5.draw (g);
        tableau_6.draw (g);
        tableau_7.draw (g);

        // card_buffer
        if (!card_buffer_is_empty ())
        {
            if (AppletFinal.is_card_buffer_single ())
                get_top_card_buffer ().draw (g);
            else if (AppletFinal.is_card_buffer_not_single ())
            {
                // draws the entire card_buffer
                int temp_int = card_buffer.size () - 1;
                for (int i = temp_int ; i >= 0 ; --i)
                {
                    get_card_buffer_element (i).draw (g);
                }
            }
        }
    }


    public static void erase (Graphics g)  // similar to destroy() to remove everything in the level
    {
        // background
        /* g.setColor (Color.white);
        g.fillRect(0, 0, AppletFinal.get_screen_x (), AppletFinal.get_screen_y ());
        // technically, do not need to erase anything else since we are erasing the whole screen when we erase the background
        */

        g.setFont (score_font);
        g.setColor (Color.white);
        g.drawString (score_str, (int) (AppletFinal.get_screen_x () / 8) - (int) (score_metrics.stringWidth (score_str) / 2), (int) (AppletFinal.get_screen_y () / 12));

        g.setFont (moves_left_font);
        g.setColor (Color.white);
        if (AppletFinal.getDifficulty () != 0)
            g.drawString (moves_left_str, (int) (AppletFinal.get_screen_x () * 1 / 2) - (int) (moves_left_metrics.stringWidth (moves_left_str) / 2), (int) (AppletFinal.get_screen_y () / 12));

        // foundation piles
        foundation_pile_1.erase (g);
        foundation_pile_2.erase (g);
        foundation_pile_3.erase (g);
        foundation_pile_4.erase (g);

        // deck and discard pile
        std_deck.erase (g);
        d_pile.erase (g);

        // tableaus
        tableau_1.erase (g);
        tableau_2.erase (g);
        tableau_3.erase (g);
        tableau_4.erase (g);
        tableau_5.erase (g);
        tableau_6.erase (g);
        tableau_7.erase (g);

        // card_buffer
        if (!card_buffer_is_empty ())
        {
            if (AppletFinal.is_card_buffer_single ())
                get_top_card_buffer ().erase (g);
            else if (AppletFinal.is_card_buffer_not_single ())
            {
                int temp_int = card_buffer.size () - 1;
                for (int i = temp_int ; i >= 0 ; --i)
                {
                    get_card_buffer_element (i).erase (g);
                }
            }
        }
    }


    // draws the top card of the card buffer only
    public static void draw_card_buffer_top (Graphics g)
    {
        if (!card_buffer_is_empty ())
        {
            if (AppletFinal.is_card_buffer_single ())
                get_top_card_buffer ().draw (g);
        }
    }


    public static void erase_card_buffer_top (Graphics g)
    {
        if (!card_buffer_is_empty ())
        {
            if (AppletFinal.is_card_buffer_single ())
                get_top_card_buffer ().erase (g);
        }
    }


    public static void card_buffer_top_update (Graphics g)
    {
        erase_card_buffer_top (g);
        draw_card_buffer_top (g);
    }


    // draws entire card buffer
    public static void draw_card_buffer (Graphics g)
    {
        if (!card_buffer_is_empty ())
        {
            if (AppletFinal.is_card_buffer_not_single ())
                for (int i = card_buffer.size () - 1 ; i >= 0 ; ++i)
                {
                    get_card_buffer_element (i).draw (g);
                }
        }
    }


    // erases entire card buffer
    public static void erase_card_buffer (Graphics g)
    {
        if (!card_buffer_is_empty ())
        {
            if (AppletFinal.is_card_buffer_not_single ())
                for (int i = card_buffer.size () - 1 ; i >= 0 ; ++i)
                {
                    get_card_buffer_element (i).erase (g);
                }
        }
    }


    public static void update_card_buffer (Graphics g)
    {
        erase_card_buffer (g);
        draw_card_buffer (g);
    }


    public static FontMetrics get_score_metrics ()
    {
        return score_metrics;
    }


    public static FontMetrics get_moves_left_metrics ()
    {
        return moves_left_metrics;
    }


    public static String get_score_str ()
    {
        return score_str;
    }


    public static String get_moves_left_str ()
    {
        return moves_left_str;
    }


    public static FoundationPile get_foundation_pile_1 ()
    {
        return foundation_pile_1;
    }


    public static FoundationPile get_foundation_pile_2 ()
    {
        return foundation_pile_2;
    }


    public static FoundationPile get_foundation_pile_3 ()
    {
        return foundation_pile_3;
    }


    public static FoundationPile get_foundation_pile_4 ()
    {
        return foundation_pile_4;
    }


    public static Deck get_std_deck ()
    {
        return std_deck;
    }


    public static DiscardPile getD_pile ()
    {
        return d_pile;
    }


    public static Tableau getTableau_1 ()
    {
        return tableau_1;
    }


    public static Tableau getTableau_2 ()
    {
        return tableau_2;
    }


    public static Tableau getTableau_3 ()
    {
        return tableau_3;
    }


    public static Tableau getTableau_4 ()
    {
        return tableau_4;
    }


    public static Tableau getTableau_5 ()
    {
        return tableau_5;
    }


    public static Tableau getTableau_6 ()
    {
        return tableau_6;
    }


    public static Tableau getTableau_7 ()
    {
        return tableau_7;
    }


    public static Vector getCard_buffer ()
    {
        return card_buffer;
    }


    public static void setCard_buffer (Vector card_buffer)
    {
        StartMainGame.card_buffer = card_buffer;
    }


    // pushes or enqueues cards onto the card buffer
    public static void push_card_buffer (Card card_arg)  // enqueue
    {
        if (card_buffer.size () == 0)
        {
            card_buffer.addElement (card_arg);
        }
        else
        {
            card_buffer.insertElementAt (card_arg, card_buffer.size ());
        }
    }


    // pops or dequeues cards from the card buffer
    public static Card pop_card_buffer (int pos)
    {
        if (pos == -1)
        {
            return (Card) card_buffer.remove (card_buffer.size () - 1);
        }
        else
        {
            return (Card) card_buffer.remove (pos);
        }
    }


    public static void enqueue_card_buffer (Card card_arg)
    {
        push_card_buffer (card_arg);
    }


    public static Card dequeue_card_buffer ()
    {
        return pop_card_buffer (0);
    }


    // gets a member of the card buffer at a certain position
    public static Card get_top_card_buffer (int pos)
    {
        return (Card) card_buffer.get (pos);
    }


    // gets the top card of the card buffer
    public static Card get_top_card_buffer ()  // overloaded function
    {
        return (Card) card_buffer.get (card_buffer.size () - 1);
    }


    // flushes card buffer
    public static void flush_card_buffer ()
    {
        StartMainGame.card_buffer = new Vector ();
    }


    public static boolean card_buffer_is_empty ()
    {
        return card_buffer.isEmpty ();
    }


    // gets a card in the card buffer
    public static Card get_card_buffer_element (int index)
    {
        return (Card) card_buffer.get (index);
    }


    public static FoundationPile getFoundation_pile_1 ()
    {
        return foundation_pile_1;
    }


    public static FoundationPile getFoundation_pile_2 ()
    {
        return foundation_pile_2;
    }


    public static FoundationPile getFoundation_pile_3 ()
    {
        return foundation_pile_3;
    }


    public static FoundationPile getFoundation_pile_4 ()
    {
        return foundation_pile_4;
    }


    public static Deck getStd_deck ()
    {
        return std_deck;
    }


    // checks if the player wins, loses, or the game still continues
    public static int win_condition ()  // 0 = not finished game, 1 = won game, 2 = lose game
    {
        if (foundation_pile_1.check_complete ())
        {
            if (foundation_pile_2.check_complete ())
            {
                if (foundation_pile_3.check_complete ())
                {
                    if (foundation_pile_4.check_complete ())
                    {
                        return 1;
                    }
                }
            }
        }
        if (AppletFinal.getMoves_left () <= 0)
        {
            return 2;
        }
        return 0;
    }
}
