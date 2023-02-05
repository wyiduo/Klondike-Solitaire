/* Klondike Solitaire
 *
 * Essentially, the goal of the game to to drag all the cards to the foundation piles, and the foundation piles go from A to K and must be of the same suit.
 * There are also 7 tableaus, which contain from 1 to 7 cards respectively and go from K to A and must alternate coloured suits. So, it must alternate D and H to C and S.
 * On tableaus, if you have a red suit, you can place any black suit, and vice versa.
 * There is also a scoring system that is more further explained in the manual.
 * There are also 4 difficultly modes that are more further explained in the manual.
 */

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AppletFinal extends Applet implements ActionListener, MouseListener, MouseMotionListener
{
    // having a serialVersionUID is helpful in that you can change this everything you modify the file and this reminds you to check important functions or of the like
    private static final long serialVersionUID = 5715119091639564156L;
    int program_state = 0;
    final static int screen_x = 900, screen_y = 950;
    final static boolean debug_mode = false; // 0 = no debug mode, 1 = debug mode
    Graphics g;
    // int mouse_x = (int) (screen_x / 2), mouse_y = (int) (screen_y / 2);

    // for splashscreen
    Button button_play = new Button ("Play");

    // difficulty screen
    Choice choice_difficulty = new Choice ();
    boolean program_state_run_once = false;
    Button button_start = new Button ("Start");

    // main game - code 10
    // G.U.I.
    TextField field_score = new TextField (4);
    TextField field_moves_left = new TextField (4);
    boolean add_text_boxes_run_once = false, move_flag = false;
    static int paint_card_buffer_code = 0, moves_left = 1;
    static double points = 0;
    static int difficulty; // 0 = free play, 1 = easy, 2 = normal, 3 = hard
    static String card_buff_last_location; // dpile, found1, found2, ..., tab1, tab2, ...

    // win screen - code 15
    static boolean win_screen_run_once = false;
    //static int final_moves_left_win = 0;
    //static double final_points_win = 0;

    // lose screen - code 16
    static boolean lose_screen_run_once = false;
    //static double final_points_lose = 0;

    // this initializes the StartSplashScreen
    public void init ()
    {
        // if debug_mode checks if the program is running in debug mode
        if (debug_mode)
        {
            System.out.println ("This program is running in debug mode.");
        }

        setSize (screen_x, screen_y);
        g = getGraphics ();

        setLayout (null);
        StartSplashScreen.init (g);

        add (button_play);
        int size_x = (int) (screen_x / 4), size_y = (int) (screen_y / 4);
        button_play.setLocation ((int) (screen_x / 2) - (int) (size_x / 2), (int) (screen_y * 3 / 4) - (int) (size_y / 2));
        button_play.setSize (size_x, size_y);
        Font button_font = new Font ("Monospaced", Font.BOLD, 100);
        button_play.setFont (button_font);
    }


    // paint if the function used to paint to the screen
    public void paint (Graphics g)
    {
        /* // wanted to use this to delay the program to only paint in certain intervals
        while (System.currentTimeMillis () % 10 != 0)
        {

        } */
		
        super.paint (g);
		
        // checks which screen the program is on and from there does different tasks
        if (program_state == 0)
        {
            StartSplashScreen.draw (g);
        }
        else if (program_state == 1)
        {
            remove (button_play);
            StartSplashScreen.erase (g);

            StartDifficultyScreen.init (g);
            StartDifficultyScreen.draw (g);

            // this is just a run_once if statement
            if (program_state_run_once == false)
            {
                choice_difficulty.add ("Free Play"); // unlimited moves, score multiplier x0
                choice_difficulty.add ("Easy"); // 320 moves, score multiplier x0.5
                choice_difficulty.add ("Normal"); // 240 moves, score multiplier x1
                choice_difficulty.add ("Hard"); // 120 moves, score multiplier x2
                add (choice_difficulty);

                int size_x = (int) (screen_x / 8), size_y = (int) (screen_y / 16);
                choice_difficulty.setLocation ((int) (screen_x / 2) - (int) (size_x / 2), (int) (screen_y * 3 / 8) - (int) (size_y / 2));
                choice_difficulty.setSize (size_x, size_y);

                add (button_start);
                int size_x_2 = (int) (screen_x / 6), size_y_2 = (int) (screen_y / 12);
                button_start.setLocation ((int) (screen_x / 2) - (int) (size_x_2 / 2), (int) (screen_y * 3 / 4) - (int) (size_y_2 / 2));
                button_start.setSize (size_x_2, size_y_2);

                program_state_run_once = true;
            }
        }
        else if (program_state == 10)
        {
            // run once
            if (!add_text_boxes_run_once)
            {
                remove (button_start);

                difficulty = choice_difficulty.getSelectedIndex ();
                remove (choice_difficulty);

                StartDifficultyScreen.erase (g);

                // starts main game
                StartMainGame.init (g);
                // sets the difficulty
                switch (difficulty)
                {
                    case 0:
                        moves_left = Integer.MAX_VALUE;
                        points = 0;
                        break;
                    case 1:
                        moves_left = 300;
                        points = 0;
                        break;
                    case 2:
                        moves_left = 230;
                        points = 0;
                        break;
                    case 3:
                        moves_left = 160;
                        points = 0;
                        break;
                }

                int size_x = (int) (screen_x / 12), size_y = (int) (screen_y / 16); // size_x and size_y are the sizes for both the text boxes; design choice to make both text boxes the same size
                Font text_box_font = new Font ("Serif", Font.PLAIN, 25);

                add (field_score);
                field_score.setLocation ((int) (screen_x / 8) + StartMainGame.get_score_metrics ().stringWidth (StartMainGame.get_score_str ()) + (int) (size_x / 2), (int) (screen_y / 16) - (int) (size_y / 2));
                field_score.setSize (size_x, size_y);
                field_score.setFont (text_box_font);
                field_score.setText (Double.toString (points));

                // tells the program to not include a field_moves_left if on free play mode
                if (difficulty != 0)
                {
                    add (field_moves_left);
                    field_moves_left.setLocation ((int) (screen_x * 1 / 2) + StartMainGame.get_moves_left_metrics ().stringWidth (StartMainGame.get_moves_left_str ()) + (int) (size_x / 2), (int) (screen_y / 16) - (int) (size_y / 2));
                    field_moves_left.setSize (size_x, size_y);
                    field_moves_left.setFont (text_box_font);
                    field_moves_left.setText (Integer.toString (moves_left));
                }
                // adds the mouse listener and the mouse motion listener
                addMouseListener (this);
                addMouseMotionListener (this);

                add_text_boxes_run_once = true;
            }

            // this is so that everything doesn't need to be drawn at once, if something wants to only update the card, then they can do that
            if (paint_card_buffer_code == 0)
            {
                StartMainGame.update (g);
            }
            else if (paint_card_buffer_code == 1)
            {
                StartMainGame.card_buffer_top_update (g);
            }
            else if (paint_card_buffer_code == 2)
            {
                StartMainGame.update_card_buffer (g);
            }
        }
        else if (program_state == 15)
        {
            // run once
            if (!win_screen_run_once)
            {
                StartMainGame.erase (g);

                StartWinScreen.set_moves_left (moves_left);
                StartWinScreen.set_points (points);

                remove (field_score);
                remove (field_moves_left);

                StartWinScreen.start (g);

                win_screen_run_once = true;
            }

            StartWinScreen.draw_all (g);
        }
        else if (program_state == 16)
        {
            // run once
            if (!lose_screen_run_once)
            {
                StartMainGame.erase (g);

                StartLoseScreen.set_points (points);

                remove (field_score);
                remove (field_moves_left);

                StartLoseScreen.start (g);

                lose_screen_run_once = true;
            }

            StartLoseScreen.draw_all (g);
        }
    }


    // actionPerformed function with the same body
    public void actionPerformed (ActionEvent e)
    {
        // repaint ();
    }


    // checks for if specific actions are performed
    public boolean action (Event e, Object o)
    {
        // program state
        if (program_state == 0)
        {
            // checks if press button_play
            if (e.target == button_play)
            {
                program_state = 1;
                repaint ();
            }
        }
        else if (program_state == 1)
        {
            // checks if press button_start
            if (e.target == button_start)
            {
                program_state = 10;
                repaint ();
            }
        }
        return true;
    }


    // updates int32 moves_left
    private void update_moves_left ()
    {
        --moves_left;
        switch (StartMainGame.win_condition ())
        {
            case 0:
                break;
            case 1:
                program_state = 15;
                repaint ();
                break;
            case 2:
                program_state = 16;
                repaint ();
                break;
        }
    }


    // updates double points
    private static void add_points (int int_arg)
    {
        // debug mode
        if (debug_mode)
        {
            System.out.println ("Points = " + points);
            System.out.println ("difficulty = " + difficulty);
            System.out.println ("int_arg = " + int_arg);
        }

        // updates based on difficulty
        switch (difficulty)
        {
            case 0:
                break;
            case 1:
                if (int_arg == -100)
                {
                    points += int_arg;
                }
                else
                {
                    points += int_arg * 0.5;
                }
                break;
            case 2:
                if (int_arg == -100)
                {
                    points += int_arg;
                }
                else
                {
                    points += int_arg;
                }
                break;
            case 3:
                if (int_arg == -100)
                {
                    points += int_arg;
                }
                else
                {
                    points += int_arg * 2;
                }
                break;
            default:
                System.out.println ("Reached default statement in add points.");
                break;
        }

        // makes sure that minimum score is 0
        if (points <= 0)
        {
            points = 0;
        }
    }


    // checks for mouse clicked
    public void mouseClicked (MouseEvent e)
    {
        // checks if mouse is inside the deck
        if (StartMainGame.get_std_deck ().is_point_inside (e.getX (), e.getY ()) == true)
        {
            if (debug_mode)
            {
                System.out.println ("mouse clicked in std_deck box");
            }
            // checks if std deck is empty
            if (StartMainGame.get_std_deck ().is_empty ())
            {
                // checks if discard pile is empty
                if (!StartMainGame.getD_pile ().is_empty ())
                {
                    add_points (-100);
                    field_score.setText (Double.toString (points));
                }

                // places the cards in discard pile back into the deck in the same order
                int temp_const_buffer = StartMainGame.d_pile.get_pile_size (); // this is used since the d_pile.get_pile_size() will always be changing
                for (int i = 0 ; i < temp_const_buffer ; ++i)
                {
                    StartMainGame.std_deck.addCard (StartMainGame.d_pile.pop (), 0);
                }
            }
            else
            {
                StartMainGame.d_pile.push (StartMainGame.std_deck.dealCard (-1));
            }
            if (!StartMainGame.getD_pile ().is_empty ())
            {
                update_moves_left ();
                field_moves_left.setText (Integer.toString (moves_left));
            }
        }
        repaint ();
    }


    public void mouseEntered (MouseEvent e)
    {
    }


    public void mouseExited (MouseEvent e)
    {
    }


    // checks if the temp card that the mouse is holding is not from a tableau
    public static boolean is_card_buffer_single ()
    {
        return card_buff_last_location == "dpile" || card_buff_last_location == "found1" || card_buff_last_location == "found2" || card_buff_last_location == "found3" || card_buff_last_location == "found4";
    }


    // checks if the temp card that the mouse is holding is from a tableau
    public static boolean is_card_buffer_not_single ()
    {
        return card_buff_last_location == "tableau1" || card_buff_last_location == "tableau2" || card_buff_last_location == "tableau3" || card_buff_last_location == "tableau4" || card_buff_last_location == "tableau5" || card_buff_last_location == "tableau6" || card_buff_last_location == "tableau7";
    }


    // checks if any tableaus can have their top card flipped face up and flips the top card face up
    private void check_flip_if_tableau_top_facedown (String tableau_target)
    {
        // due to the use of Java 1.4, strings cannot be used in switch statements
        // checks which location did the cards come from (so this is where to flip the top faceup) and checks to make sure that users cannot just take cards from a tableau and put them back and expect the tableau to flip
        if (card_buff_last_location == "tableau1" && tableau_target != "tableau1")
        {
            if (!StartMainGame.getTableau_1 ().is_empty ())
            {
                // checks if the top card (this is the lowermost card) of tableau 1 is facedown
                if (StartMainGame.getTableau_1 ().check_if_pile_top_facedown ())
                {
                    StartMainGame.getTableau_1 ().get_pile_top ().set_is_face_down (false);
                    add_points (5);
                    field_score.setText (Double.toString (points));
                }
            }
        }
        else if (card_buff_last_location == "tableau2" && tableau_target != "tableau2")
        {
            if (!StartMainGame.getTableau_2 ().is_empty ())
            {
                if (StartMainGame.getTableau_2 ().check_if_pile_top_facedown ())
                {
                    StartMainGame.getTableau_2 ().get_pile_top ().set_is_face_down (false);
                    add_points (5);
                    field_score.setText (Double.toString (points));
                }
            }
        }
        else if (card_buff_last_location == "tableau3" && tableau_target != "tableau3")
        {
            if (!StartMainGame.getTableau_3 ().is_empty ())
            {
                if (StartMainGame.getTableau_3 ().check_if_pile_top_facedown ())
                {
                    StartMainGame.getTableau_3 ().get_pile_top ().set_is_face_down (false);
                    add_points (5);
                    field_score.setText (Double.toString (points));
                }
            }
        }
        else if (card_buff_last_location == "tableau4" && tableau_target != "tableau4")
        {
            if (!StartMainGame.getTableau_4 ().is_empty ())
            {
                if (StartMainGame.getTableau_4 ().check_if_pile_top_facedown ())
                {
                    StartMainGame.getTableau_4 ().get_pile_top ().set_is_face_down (false);
                    add_points (5);
                    field_score.setText (Double.toString (points));
                }
            }
        }
        else if (card_buff_last_location == "tableau5" && tableau_target != "tableau5")
        {
            if (!StartMainGame.getTableau_5 ().is_empty ())
            {
                if (StartMainGame.getTableau_5 ().check_if_pile_top_facedown ())
                {
                    StartMainGame.getTableau_5 ().get_pile_top ().set_is_face_down (false);
                    add_points (5);
                    field_score.setText (Double.toString (points));
                }
            }
        }
        else if (card_buff_last_location == "tableau6" && tableau_target != "tableau6")
        {
            if (!StartMainGame.getTableau_6 ().is_empty ())
            {
                if (StartMainGame.getTableau_6 ().check_if_pile_top_facedown ())
                {
                    StartMainGame.getTableau_6 ().get_pile_top ().set_is_face_down (false);
                    add_points (5);
                    field_score.setText (Double.toString (points));
                }
            }
        }
        else if (card_buff_last_location == "tableau7" && tableau_target != "tableau7")
        {
            if (!StartMainGame.getTableau_7 ().is_empty ())
            {
                if (StartMainGame.getTableau_7 ().check_if_pile_top_facedown ())
                {
                    StartMainGame.getTableau_7 ().get_pile_top ().set_is_face_down (false);
                    add_points (5);
                    field_score.setText (Double.toString (points));
                }
            }
        }
    }


    // checks for mouse presses
    public void mousePressed (MouseEvent e)
    {
        // checks if there is currently no temp cards that the mouse is holding
        if (StartMainGame.card_buffer_is_empty ())
        {
            // checks to see if the mouse is in the discard pile
            if (StartMainGame.getD_pile ().is_point_inside (e.getX (), e.getY ()))
            {
                if (debug_mode)
                {
                    System.out.println ("mouse pressed in d_pile");
                }
                // checks to make sure if the discard pile is not empty, otherwise you cannot get cards from empty pile
                if (!StartMainGame.getD_pile ().is_empty ())
                {
                    if ((StartMainGame.card_buffer_is_empty ()))
                    {
                        Vector temp_vec = new Vector ();
                        temp_vec.addElement (StartMainGame.getD_pile ().pop ());
                        StartMainGame.setCard_buffer (temp_vec);
                        card_buff_last_location = "dpile";
                        move_flag = true;
                        repaint ();
                    }
                }
            }
            else if (StartMainGame.get_foundation_pile_1 ().is_point_inside (e.getX (), e.getY ()))
            {
                if (!StartMainGame.get_foundation_pile_1 ().is_empty ())
                {
                    if ((StartMainGame.card_buffer_is_empty ()))
                    {
                        Vector temp_vec = new Vector ();
                        temp_vec.addElement (StartMainGame.get_foundation_pile_1 ().pop ());
                        StartMainGame.setCard_buffer (temp_vec);
                        card_buff_last_location = "found1";
                        move_flag = true;
                        repaint ();
                    }
                }
            }
            else if (StartMainGame.get_foundation_pile_2 ().is_point_inside (e.getX (), e.getY ()))
            {
                if (!StartMainGame.get_foundation_pile_2 ().is_empty ())
                {
                    if ((StartMainGame.card_buffer_is_empty ()))
                    {
                        Vector temp_vec = new Vector ();
                        temp_vec.addElement (StartMainGame.get_foundation_pile_2 ().pop ());
                        StartMainGame.setCard_buffer (temp_vec);
                        card_buff_last_location = "found2";
                        move_flag = true;
                        repaint ();
                    }
                }
            }
            else if (StartMainGame.get_foundation_pile_3 ().is_point_inside (e.getX (), e.getY ()))
            {
                if (!StartMainGame.get_foundation_pile_3 ().is_empty ())
                {
                    if ((StartMainGame.card_buffer_is_empty ()))
                    {
                        Vector temp_vec = new Vector ();
                        temp_vec.addElement (StartMainGame.get_foundation_pile_3 ().pop ());
                        StartMainGame.setCard_buffer (temp_vec);
                        card_buff_last_location = "found3";
                        move_flag = true;
                        repaint ();
                    }
                }
            }
            else if (StartMainGame.get_foundation_pile_4 ().is_point_inside (e.getX (), e.getY ()))
            {
                if (!StartMainGame.get_foundation_pile_4 ().is_empty ())
                {
                    if ((StartMainGame.card_buffer_is_empty ()))
                    {
                        Vector temp_vec = new Vector ();
                        temp_vec.addElement (StartMainGame.get_foundation_pile_4 ().pop ());
                        StartMainGame.setCard_buffer (temp_vec);
                        card_buff_last_location = "found4";
                        move_flag = true;
                        repaint ();
                    }
                }
            }
            // checks if the user has clicked inside the full tableau (includes the facedown cards)
            else if (StartMainGame.getTableau_1 ().is_point_inside_full_tableau (e.getX (), e.getY ()))
            {
                if (debug_mode)
                {
                    System.out.println ("mouse pressed in Tableau_1");
                }
                // checks if tableau 1 is not empty
                if (!StartMainGame.getTableau_1 ().is_empty ())
                {
                    // checks if the the user has not clicked a facedown card or out of bounds card
                    if (StartMainGame.getTableau_1 ().index_of_card_clicked (e.getX (), e.getY ()) >= 0)
                        // checks if there are no temp cards
                        if ((StartMainGame.card_buffer_is_empty ()))
                        {
                            if (debug_mode)
                                System.out.println ("StartMainGame.getTableau_1().index_of_card_clicked() = " + StartMainGame.getTableau_1 ().index_of_card_clicked (e.getX (), e.getY ()));
                            // adds to the buffer all the cards from the user clicked card to the top most card (which is the lowest card in terms of the dimensions of the screen)
                            int temp_int = StartMainGame.getTableau_1 ().get_pile_size () - StartMainGame.getTableau_1 ().index_of_card_clicked (e.getX (), e.getY ());
                            for (int i = 0 ; i < temp_int ; ++i)
                            {
                                if (debug_mode)
                                    System.out.println ("Inside for (int i = 0; i < StartMainGame.getTableau_1().get_pile_size() - StartMainGame.getTableau_1().index_of_card_clicked(e.getX (), e.getY ()); ++i)");
                                StartMainGame.push_card_buffer (StartMainGame.getTableau_1 ().pop ());
                            }
                            card_buff_last_location = "tableau1";
                            move_flag = true;
                            repaint ();
                        }
                }
            }
            else if (StartMainGame.getTableau_2 ().is_point_inside_full_tableau (e.getX (), e.getY ()))
            {
                if (!StartMainGame.getTableau_2 ().is_empty ())
                {
                    if (debug_mode)
                    {
                        System.out.println ("StartMainGame.getTableau_2().index_of_card_clicked(e.getX (), e.getY ()) = " + StartMainGame.getTableau_2 ().index_of_card_clicked (e.getX (), e.getY ()));
                        // System.out.println("(!((Card)(StartMainGame.getTableau_2().getPile().get(StartMainGame.getTableau_2().index_of_card_clicked(e.getX (), e.getY ())))).get_is_face_down()) = " + (!((Card)(StartMainGame.getTableau_2().getPile().get(StartMainGame.getTableau_2().index_of_card_clicked(e.getX (), e.getY ())))).get_is_face_down()));
                    }
                    if (StartMainGame.getTableau_2 ().index_of_card_clicked (e.getX (), e.getY ()) >= 0)
                        if ((StartMainGame.card_buffer_is_empty ()))
                        {
                            int temp_int = StartMainGame.getTableau_2 ().get_pile_size () - StartMainGame.getTableau_2 ().index_of_card_clicked (e.getX (), e.getY ());
                            for (int i = 0 ; i < temp_int ; ++i)
                            {
                                StartMainGame.push_card_buffer (StartMainGame.getTableau_2 ().pop ());
                            }
                            card_buff_last_location = "tableau2";
                            move_flag = true;
                            repaint ();
                        }
                }
            }
            else if (StartMainGame.getTableau_3 ().is_point_inside_full_tableau (e.getX (), e.getY ()))
            {
                if (!StartMainGame.getTableau_3 ().is_empty ())
                {
                    if (StartMainGame.getTableau_3 ().index_of_card_clicked (e.getX (), e.getY ()) >= 0)
                        if ((StartMainGame.card_buffer_is_empty ()))
                        {
                            int temp_int = StartMainGame.getTableau_3 ().get_pile_size () - StartMainGame.getTableau_3 ().index_of_card_clicked (e.getX (), e.getY ());
                            for (int i = 0 ; i < temp_int ; ++i)
                            {
                                StartMainGame.push_card_buffer (StartMainGame.getTableau_3 ().pop ());
                            }
                            card_buff_last_location = "tableau3";
                            move_flag = true;
                            repaint ();
                        }
                }
            }
            else if (StartMainGame.getTableau_4 ().is_point_inside_full_tableau (e.getX (), e.getY ()))
            {
                if (!StartMainGame.getTableau_4 ().is_empty ())
                {
                    if (StartMainGame.getTableau_4 ().index_of_card_clicked (e.getX (), e.getY ()) >= 0)
                        if ((StartMainGame.card_buffer_is_empty ()))
                        {
                            int temp_int = StartMainGame.getTableau_4 ().get_pile_size () - StartMainGame.getTableau_4 ().index_of_card_clicked (e.getX (), e.getY ());
                            for (int i = 0 ; i < temp_int ; ++i)
                            {
                                StartMainGame.push_card_buffer (StartMainGame.getTableau_4 ().pop ());
                            }
                            card_buff_last_location = "tableau4";
                            move_flag = true;
                            repaint ();
                        }
                }
            }
            else if (StartMainGame.getTableau_5 ().is_point_inside_full_tableau (e.getX (), e.getY ()))
            {
                if (!StartMainGame.getTableau_5 ().is_empty ())
                {
                    if (StartMainGame.getTableau_5 ().index_of_card_clicked (e.getX (), e.getY ()) >= 0)
                        if ((StartMainGame.card_buffer_is_empty ()))
                        {
                            int temp_int = StartMainGame.getTableau_5 ().get_pile_size () - StartMainGame.getTableau_5 ().index_of_card_clicked (e.getX (), e.getY ());
                            for (int i = 0 ; i < temp_int ; ++i)
                            {
                                StartMainGame.push_card_buffer (StartMainGame.getTableau_5 ().pop ());
                            }
                            card_buff_last_location = "tableau5";
                            move_flag = true;
                            repaint ();
                        }
                }
            }
            else if (StartMainGame.getTableau_6 ().is_point_inside_full_tableau (e.getX (), e.getY ()))
            {
                if (!StartMainGame.getTableau_6 ().is_empty ())
                {
                    if (StartMainGame.getTableau_6 ().index_of_card_clicked (e.getX (), e.getY ()) >= 0)
                        if ((StartMainGame.card_buffer_is_empty ()))
                        {
                            int temp_int = StartMainGame.getTableau_6 ().get_pile_size () - StartMainGame.getTableau_6 ().index_of_card_clicked (e.getX (), e.getY ());
                            for (int i = 0 ; i < temp_int ; ++i)
                            {
                                StartMainGame.push_card_buffer (StartMainGame.getTableau_6 ().pop ());
                            }
                            card_buff_last_location = "tableau6";
                            move_flag = true;
                            repaint ();
                        }
                }
            }
            else if (StartMainGame.getTableau_7 ().is_point_inside_full_tableau (e.getX (), e.getY ()))
            {
                if (!StartMainGame.getTableau_7 ().is_empty ())
                {
                    if (StartMainGame.getTableau_7 ().index_of_card_clicked (e.getX (), e.getY ()) >= 0)
                        if ((StartMainGame.card_buffer_is_empty ()))
                        {
                            int temp_int = StartMainGame.getTableau_7 ().get_pile_size () - StartMainGame.getTableau_7 ().index_of_card_clicked (e.getX (), e.getY ());
                            for (int i = 0 ; i < temp_int ; ++i)
                            {
                                StartMainGame.push_card_buffer (StartMainGame.getTableau_7 ().pop ());
                            }
                            card_buff_last_location = "tableau7";
                            move_flag = true;
                            repaint ();
                        }
                }
            }
        }
        else
        {
            // checks the size of the temp card buffer the mouse is holding, this checks if there is only 1 card in the mouse buffer
            if (is_card_buffer_single ())
            {
                // checks if the the mouse is inside the top card in the card buffer, this is the only card floating around, or the highest card in terms of dimensions
                if (StartMainGame.get_top_card_buffer ().is_point_inside (e.getX (), e.getY ()))
                {
                    move_flag = true; // this is required in order for the mouseDragged to work
                    StartMainGame.get_top_card_buffer ().setCentreCoords (e.getX (), e.getY ());
                    repaint ();
                }
            }
            // checks if there are multiple cards in the card buffer
            else if (is_card_buffer_not_single ())
            {
                if (StartMainGame.get_top_card_buffer ().is_point_inside (e.getX (), e.getY ()))
                {
                    move_flag = true; // this is required in order for the mouseDragged to work
                    int y_accum = 0;
                    for (int i = 0 ; i < StartMainGame.card_buffer.size () ; ++i)
                    {
                        StartMainGame.get_card_buffer_element (StartMainGame.getCard_buffer ().size () - i - 1).setCentreCoords (e.getX (), e.getY () + y_accum);
                        y_accum += (int) (StartMainGame.get_top_card_buffer ().getHeight () / 4); // this assumes that the cards in the card_buffer are the same size
                    }
                    repaint ();
                }
            }
        }
    }


    // mouseReleased
    public void mouseReleased (MouseEvent e)
    {
        if ((!StartMainGame.card_buffer_is_empty ()))
        {
            if (is_card_buffer_single ())
            {
                if (StartMainGame.get_top_card_buffer ().is_point_inside (e.getX (), e.getY ()))
                {
                    move_flag = false; // this is required in order for the mouseDragged to work
                    StartMainGame.get_top_card_buffer ().setCentreCoords (e.getX (), e.getY ());
                    if (StartMainGame.get_foundation_pile_1 ().is_point_inside (e.getX (), e.getY ()) && card_buff_last_location != "found1")
                    {
                        if (debug_mode)
                            System.out.println ("entered (StartMainGame.get_foundation_pile_1 ().is_point_inside (e.getX (), e.getY ()) == true) conditional");
                        // checks to see if the card in the temp card buffer can be added to the foundation pile 1
                        if (StartMainGame.get_foundation_pile_1 ().check_push (StartMainGame.get_top_card_buffer ()))
                        {
                            if (debug_mode)
                                System.out.println ("entered (StartMainGame.get_foundation_pile_1 ().check_push(StartMainGame.get_top_card_buffer ())) conditional");
                            StartMainGame.get_foundation_pile_1 ().push (StartMainGame.pop_card_buffer (-1)); // updates the foundation pile with the card buffer
                            // checks to see where did the card come from and hence give points based on this
                            if (card_buff_last_location == "dpile")
                            {
                                add_points (10);
                                field_score.setText (Double.toString (points));
                            }
                            update_moves_left ();
                            field_moves_left.setText (Integer.toString (moves_left));
                        }
                        // if it cannot go into the foundation pile, it returns to where it comes from
                        else
                        {
                            return_cards_to_last_pile ();
                        }
                    }
                    else if (StartMainGame.get_foundation_pile_2 ().is_point_inside (e.getX (), e.getY ()) && card_buff_last_location != "found2")
                    {
                        if (StartMainGame.get_foundation_pile_2 ().check_push (StartMainGame.get_top_card_buffer ()))
                        {
                            StartMainGame.get_foundation_pile_2 ().push (StartMainGame.pop_card_buffer (-1));
                            if (card_buff_last_location == "dpile")
                            {
                                add_points (10);
                                field_score.setText (Double.toString (points));
                            }
                            update_moves_left ();
                            field_moves_left.setText (Integer.toString (moves_left));
                        }
                        else
                        {
                            return_cards_to_last_pile ();
                        }
                    }
                    else if (StartMainGame.get_foundation_pile_3 ().is_point_inside (e.getX (), e.getY ()) && card_buff_last_location != "found3")
                    {
                        if (StartMainGame.get_foundation_pile_3 ().check_push (StartMainGame.get_top_card_buffer ()))
                        {
                            StartMainGame.get_foundation_pile_3 ().push (StartMainGame.pop_card_buffer (-1));
                            if (card_buff_last_location == "dpile")
                            {
                                add_points (10);
                                field_score.setText (Double.toString (points));
                            }
                            update_moves_left ();
                            field_moves_left.setText (Integer.toString (moves_left));
                        }
                        else
                        {
                            return_cards_to_last_pile ();
                        }
                    }
                    else if (StartMainGame.get_foundation_pile_4 ().is_point_inside (e.getX (), e.getY ()) && card_buff_last_location != "found4")
                    {
                        if (StartMainGame.get_foundation_pile_4 ().check_push (StartMainGame.get_top_card_buffer ()))
                        {
                            StartMainGame.get_foundation_pile_4 ().push (StartMainGame.pop_card_buffer (-1));
                            if (card_buff_last_location == "dpile")
                            {
                                add_points (10);
                                field_score.setText (Double.toString (points));
                            }
                            update_moves_left ();
                            field_moves_left.setText (Integer.toString (moves_left));
                        }
                        else
                        {
                            return_cards_to_last_pile ();
                        }
                    }
                    else if (StartMainGame.getTableau_1 ().is_point_inside (e.getX (), e.getY ()) && card_buff_last_location != "tableau1")
                    {
                        if (StartMainGame.getTableau_1 ().check_push (StartMainGame.get_top_card_buffer ()))
                        {
                            StartMainGame.getTableau_1 ().push (StartMainGame.pop_card_buffer (-1));
                            // checks and distributes points
                            if (card_buff_last_location == "dpile")
                            {
                                add_points (5);
                                field_score.setText (Double.toString (points));
                            }
                            else if (card_buff_last_location == "found1" || card_buff_last_location == "found2" || card_buff_last_location == "found3" || card_buff_last_location == "found4")
                            {
                                add_points (-15);
                                field_score.setText (Double.toString (points));
                            }
                            update_moves_left ();
                            field_moves_left.setText (Integer.toString (moves_left));
                        }
                        else
                        {
                            return_cards_to_last_pile ();
                        }
                    }
                    else if (StartMainGame.getTableau_2 ().is_point_inside (e.getX (), e.getY ()) && card_buff_last_location != "tableau2")
                    {
                        if (StartMainGame.getTableau_2 ().check_push (StartMainGame.get_top_card_buffer ()))
                        {
                            StartMainGame.getTableau_2 ().push (StartMainGame.pop_card_buffer (-1));
                            if (card_buff_last_location == "dpile")
                            {
                                add_points (5);
                                field_score.setText (Double.toString (points));
                            }
                            else if (card_buff_last_location == "found1" || card_buff_last_location == "found2" || card_buff_last_location == "found3" || card_buff_last_location == "found4")
                            {
                                add_points (-15);
                                field_score.setText (Double.toString (points));
                            }
                            update_moves_left ();
                            field_moves_left.setText (Integer.toString (moves_left));
                        }
                        else
                        {
                            return_cards_to_last_pile ();
                        }
                    }
                    else if (StartMainGame.getTableau_3 ().is_point_inside (e.getX (), e.getY ()) && card_buff_last_location != "tableau3")
                    {
                        if (StartMainGame.getTableau_3 ().check_push (StartMainGame.get_top_card_buffer ()))
                        {
                            StartMainGame.getTableau_3 ().push (StartMainGame.pop_card_buffer (-1));
                            if (card_buff_last_location == "dpile")
                            {
                                add_points (5);
                                field_score.setText (Double.toString (points));
                            }
                            else if (card_buff_last_location == "found1" || card_buff_last_location == "found2" || card_buff_last_location == "found3" || card_buff_last_location == "found4")
                            {
                                add_points (-15);
                                field_score.setText (Double.toString (points));
                            }
                            update_moves_left ();
                            field_moves_left.setText (Integer.toString (moves_left));
                        }
                        else
                        {
                            return_cards_to_last_pile ();
                        }
                    }
                    else if (StartMainGame.getTableau_4 ().is_point_inside (e.getX (), e.getY ()) && card_buff_last_location != "tableau4")
                    {
                        if (StartMainGame.getTableau_4 ().check_push (StartMainGame.get_top_card_buffer ()))
                        {
                            StartMainGame.getTableau_4 ().push (StartMainGame.pop_card_buffer (-1));
                            if (card_buff_last_location == "dpile")
                            {
                                add_points (5);
                                field_score.setText (Double.toString (points));
                            }
                            else if (card_buff_last_location == "found1" || card_buff_last_location == "found2" || card_buff_last_location == "found3" || card_buff_last_location == "found4")
                            {
                                add_points (-15);
                                field_score.setText (Double.toString (points));
                            }
                            update_moves_left ();
                            field_moves_left.setText (Integer.toString (moves_left));
                        }
                        else
                        {
                            return_cards_to_last_pile ();
                        }
                    }
                    else if (StartMainGame.getTableau_5 ().is_point_inside (e.getX (), e.getY ()) && card_buff_last_location != "tableau5")
                    {
                        if (StartMainGame.getTableau_5 ().check_push (StartMainGame.get_top_card_buffer ()))
                        {
                            StartMainGame.getTableau_5 ().push (StartMainGame.pop_card_buffer (-1));
                            if (card_buff_last_location == "dpile")
                            {
                                add_points (5);
                                field_score.setText (Double.toString (points));
                            }
                            else if (card_buff_last_location == "found1" || card_buff_last_location == "found2" || card_buff_last_location == "found3" || card_buff_last_location == "found4")
                            {
                                add_points (-15);
                                field_score.setText (Double.toString (points));
                            }
                            update_moves_left ();
                            field_moves_left.setText (Integer.toString (moves_left));
                        }
                        else
                        {
                            return_cards_to_last_pile ();
                        }
                    }
                    else if (StartMainGame.getTableau_6 ().is_point_inside (e.getX (), e.getY ()) && card_buff_last_location != "tableau6")
                    {
                        if (StartMainGame.getTableau_6 ().check_push (StartMainGame.get_top_card_buffer ()))
                        {
                            StartMainGame.getTableau_6 ().push (StartMainGame.pop_card_buffer (-1));
                            if (card_buff_last_location == "dpile")
                            {
                                add_points (5);
                                field_score.setText (Double.toString (points));
                            }
                            else if (card_buff_last_location == "found1" || card_buff_last_location == "found2" || card_buff_last_location == "found3" || card_buff_last_location == "found4")
                            {
                                add_points (-15);
                                field_score.setText (Double.toString (points));
                            }
                            update_moves_left ();
                            field_moves_left.setText (Integer.toString (moves_left));
                        }
                        else
                        {
                            return_cards_to_last_pile ();
                        }
                    }
                    else if (StartMainGame.getTableau_7 ().is_point_inside (e.getX (), e.getY ()) && card_buff_last_location != "tableau7")
                    {
                        if (StartMainGame.getTableau_7 ().check_push (StartMainGame.get_top_card_buffer ()))
                        {
                            StartMainGame.getTableau_7 ().push (StartMainGame.pop_card_buffer (-1));
                            if (debug_mode)
                                System.out.println ("card_buff_last_location = " + card_buff_last_location);
                            if (card_buff_last_location == "dpile")
                            {
                                add_points (5);
                                if (debug_mode)
                                    System.out.println ("Points = " + points);
                                field_score.setText (Double.toString (points));
                            }
                            else if (card_buff_last_location == "found1" || card_buff_last_location == "found2" || card_buff_last_location == "found3" || card_buff_last_location == "found4")
                            {
                                add_points (-15);
                                field_score.setText (Double.toString (points));
                            }
                            update_moves_left ();
                            field_moves_left.setText (Integer.toString (moves_left));
                        }
                        else
                        {
                            return_cards_to_last_pile ();
                        }
                    }
                    else // returns cards back to last pile if released in other positions
                    {
                        return_cards_to_last_pile ();
                    }

                    StartMainGame.flush_card_buffer ();
                    repaint ();
                }
            }
            // you need different cases for when the card buffer is not single since the pushing of the cards are done differently
            else if (is_card_buffer_not_single ())
            {
                if (StartMainGame.get_top_card_buffer ().is_point_inside (e.getX (), e.getY ()))
                {
                    move_flag = false; // this is required in order for the mouseDragged to work
                    StartMainGame.get_top_card_buffer ().setCentreCoords (e.getX (), e.getY ());
                    if (StartMainGame.getTableau_1 ().is_point_inside (e.getX (), e.getY ()) && card_buff_last_location != "tableau1")
                    {
                        if (StartMainGame.getTableau_1 ().check_push (StartMainGame.get_top_card_buffer ()))
                        {
                            int temp_int = StartMainGame.getCard_buffer ().size ();
                            for (int i = 0 ; i < temp_int ; ++i)
                                StartMainGame.getTableau_1 ().push (StartMainGame.pop_card_buffer (-1));
                            check_flip_if_tableau_top_facedown ("tableau1");
                            update_moves_left ();
                            field_moves_left.setText (Integer.toString (moves_left));
                        }
                        else
                        {
                            return_cards_to_last_pile ();
                        }
                    }
                    else if (StartMainGame.getTableau_2 ().is_point_inside (e.getX (), e.getY ()) && card_buff_last_location != "tableau2")
                    {
                        if (StartMainGame.getTableau_2 ().check_push (StartMainGame.get_top_card_buffer ()))
                        {
                            int temp_int = StartMainGame.getCard_buffer ().size ();
                            for (int i = 0 ; i < temp_int ; ++i)
                                StartMainGame.getTableau_2 ().push (StartMainGame.pop_card_buffer (-1));
                            check_flip_if_tableau_top_facedown ("tableau2");
                            update_moves_left ();
                            field_moves_left.setText (Integer.toString (moves_left));
                        }
                        else
                        {
                            return_cards_to_last_pile ();
                        }
                    }
                    else if (StartMainGame.getTableau_3 ().is_point_inside (e.getX (), e.getY ()) && card_buff_last_location != "tableau3")
                    {
                        if (StartMainGame.getTableau_3 ().check_push (StartMainGame.get_top_card_buffer ()))
                        {
                            int temp_int = StartMainGame.getCard_buffer ().size ();
                            for (int i = 0 ; i < temp_int ; ++i)
                                StartMainGame.getTableau_3 ().push (StartMainGame.pop_card_buffer (-1));
                            check_flip_if_tableau_top_facedown ("tableau3");
                            update_moves_left ();
                            field_moves_left.setText (Integer.toString (moves_left));
                        }
                        else
                        {
                            return_cards_to_last_pile ();
                        }
                    }
                    else if (StartMainGame.getTableau_4 ().is_point_inside (e.getX (), e.getY ()) && card_buff_last_location != "tableau4")
                    {
                        if (StartMainGame.getTableau_4 ().check_push (StartMainGame.get_top_card_buffer ()))
                        {
                            int temp_int = StartMainGame.getCard_buffer ().size ();
                            for (int i = 0 ; i < temp_int ; ++i)
                                StartMainGame.getTableau_4 ().push (StartMainGame.pop_card_buffer (-1));
                            check_flip_if_tableau_top_facedown ("tableau4");
                            update_moves_left ();
                            field_moves_left.setText (Integer.toString (moves_left));
                        }
                        else
                        {
                            return_cards_to_last_pile ();
                        }
                    }
                    else if (StartMainGame.getTableau_5 ().is_point_inside (e.getX (), e.getY ()) && card_buff_last_location != "tableau5")
                    {
                        if (StartMainGame.getTableau_5 ().check_push (StartMainGame.get_top_card_buffer ()))
                        {
                            int temp_int = StartMainGame.getCard_buffer ().size ();
                            for (int i = 0 ; i < temp_int ; ++i)
                                StartMainGame.getTableau_5 ().push (StartMainGame.pop_card_buffer (-1));
                            check_flip_if_tableau_top_facedown ("tableau5");
                            update_moves_left ();
                            field_moves_left.setText (Integer.toString (moves_left));
                        }
                        else
                        {
                            return_cards_to_last_pile ();
                        }
                    }
                    else if (StartMainGame.getTableau_6 ().is_point_inside (e.getX (), e.getY ()) && card_buff_last_location != "tableau6")
                    {
                        if (StartMainGame.getTableau_6 ().check_push (StartMainGame.get_top_card_buffer ()))
                        {
                            int temp_int = StartMainGame.getCard_buffer ().size ();
                            for (int i = 0 ; i < temp_int ; ++i)
                                StartMainGame.getTableau_6 ().push (StartMainGame.pop_card_buffer (-1));
                            check_flip_if_tableau_top_facedown ("tableau6");
                            update_moves_left ();
                            field_moves_left.setText (Integer.toString (moves_left));
                        }
                        else
                        {
                            return_cards_to_last_pile ();
                        }
                    }
                    else if (StartMainGame.getTableau_7 ().is_point_inside (e.getX (), e.getY ()) && card_buff_last_location != "tableau7")
                    {
                        if (StartMainGame.getTableau_7 ().check_push (StartMainGame.get_top_card_buffer ()))
                        {
                            int temp_int = StartMainGame.getCard_buffer ().size ();
                            for (int i = 0 ; i < temp_int ; ++i)
                                StartMainGame.getTableau_7 ().push (StartMainGame.pop_card_buffer (-1));
                            check_flip_if_tableau_top_facedown ("tableau7");
                            update_moves_left ();
                            field_moves_left.setText (Integer.toString (moves_left));
                        }
                        else
                        {
                            return_cards_to_last_pile ();
                        }
                    }
                    else if (StartMainGame.get_foundation_pile_1 ().is_point_inside (e.getX (), e.getY ()) && card_buff_last_location != "found1")
                    {
                        if (StartMainGame.getCard_buffer ().size () == 1)
                        {
                            if (StartMainGame.get_foundation_pile_1 ().check_push (StartMainGame.get_top_card_buffer ()))
                            {
                                StartMainGame.get_foundation_pile_1 ().push (StartMainGame.pop_card_buffer (-1));
                                check_flip_if_tableau_top_facedown ("found1");
                                if (card_buff_last_location == "tableau1" || card_buff_last_location == "tableau2" || card_buff_last_location == "tableau3" || card_buff_last_location == "tableau4" || card_buff_last_location == "tableau5" || card_buff_last_location == "tableau6" || card_buff_last_location == "tableau7")
                                {
                                    add_points (10);
                                    field_score.setText (Double.toString (points));
                                }
                                update_moves_left ();
                                field_moves_left.setText (Integer.toString (moves_left));
                            }
                            else
                            {
                                return_cards_to_last_pile ();
                            }
                        }
                        else
                        {
                            return_cards_to_last_pile ();
                        }
                    }
                    else if (StartMainGame.get_foundation_pile_2 ().is_point_inside (e.getX (), e.getY ()) && card_buff_last_location != "found2")
                    {
                        if (StartMainGame.getCard_buffer ().size () == 1)
                        {
                            if (StartMainGame.get_foundation_pile_2 ().check_push (StartMainGame.get_top_card_buffer ()))
                            {
                                StartMainGame.get_foundation_pile_2 ().push (StartMainGame.pop_card_buffer (-1));
                                check_flip_if_tableau_top_facedown ("found2");
                                if (card_buff_last_location == "tableau1" || card_buff_last_location == "tableau2" || card_buff_last_location == "tableau3" || card_buff_last_location == "tableau4" || card_buff_last_location == "tableau5" || card_buff_last_location == "tableau6" || card_buff_last_location == "tableau7")
                                {
                                    add_points (10);
                                    field_score.setText (Double.toString (points));
                                }
                                update_moves_left ();
                                field_moves_left.setText (Integer.toString (moves_left));
                            }
                            else
                            {
                                return_cards_to_last_pile ();
                            }
                        }
                        else
                        {
                            return_cards_to_last_pile ();
                        }
                    }
                    else if (StartMainGame.get_foundation_pile_3 ().is_point_inside (e.getX (), e.getY ()) && card_buff_last_location != "found3")
                    {
                        if (StartMainGame.getCard_buffer ().size () == 1)
                        {
                            if (StartMainGame.get_foundation_pile_3 ().check_push (StartMainGame.get_top_card_buffer ()))
                            {
                                StartMainGame.get_foundation_pile_3 ().push (StartMainGame.pop_card_buffer (-1));
                                check_flip_if_tableau_top_facedown ("found3");
                                if (card_buff_last_location == "tableau1" || card_buff_last_location == "tableau2" || card_buff_last_location == "tableau3" || card_buff_last_location == "tableau4" || card_buff_last_location == "tableau5" || card_buff_last_location == "tableau6" || card_buff_last_location == "tableau7")
                                {
                                    add_points (10);
                                    field_score.setText (Double.toString (points));
                                }
                                update_moves_left ();
                                field_moves_left.setText (Integer.toString (moves_left));
                            }
                            else
                            {
                                return_cards_to_last_pile ();
                            }
                        }
                        else
                        {
                            return_cards_to_last_pile ();
                        }
                    }
                    else if (StartMainGame.get_foundation_pile_4 ().is_point_inside (e.getX (), e.getY ()) && card_buff_last_location != "found4")
                    {
                        if (StartMainGame.getCard_buffer ().size () == 1)
                        {
                            if (StartMainGame.get_foundation_pile_4 ().check_push (StartMainGame.get_top_card_buffer ()))
                            {
                                StartMainGame.get_foundation_pile_4 ().push (StartMainGame.pop_card_buffer (-1));
                                check_flip_if_tableau_top_facedown ("found4");
                                if (card_buff_last_location == "tableau1" || card_buff_last_location == "tableau2" || card_buff_last_location == "tableau3" || card_buff_last_location == "tableau4" || card_buff_last_location == "tableau5" || card_buff_last_location == "tableau6" || card_buff_last_location == "tableau7")
                                {
                                    add_points (10);
                                    field_score.setText (Double.toString (points));
                                }
                                update_moves_left ();
                                field_moves_left.setText (Integer.toString (moves_left));
                            }
                            else
                            {
                                return_cards_to_last_pile ();
                            }
                        }
                        else
                        {
                            return_cards_to_last_pile ();
                        }
                    }
                    else // returns cards back to last pile if released in other positions
                    {
                        return_cards_to_last_pile ();
                    }

                    // StartMainGame.flush_card_buffer();
                    repaint ();
                }
            }
        }
    }


    // returns cards back to the previous pile
    public void return_cards_to_last_pile ()
    {
        // checks which pile the card is from and sends it back
        if (card_buff_last_location == "dpile")
        {
            StartMainGame.getD_pile ().push (StartMainGame.pop_card_buffer (-1));
            if (debug_mode)
                System.out.println ("StartMainGame.card_buffer_is_empty() = " + StartMainGame.card_buffer_is_empty ());
        }
        else if (card_buff_last_location == "found1")
        {
            StartMainGame.get_foundation_pile_1 ().push (StartMainGame.pop_card_buffer (-1));
        }
        else if (card_buff_last_location == "found2")
        {
            StartMainGame.get_foundation_pile_2 ().push (StartMainGame.pop_card_buffer (-1));
        }
        else if (card_buff_last_location == "found3")
        {
            StartMainGame.get_foundation_pile_4 ().push (StartMainGame.pop_card_buffer (-1));
        }
        else if (card_buff_last_location == "found4")
        {
            StartMainGame.get_foundation_pile_4 ().push (StartMainGame.pop_card_buffer (-1));
        }
        else if (card_buff_last_location == "tableau1")
        {
            while (!StartMainGame.card_buffer_is_empty ())
            {
                StartMainGame.getTableau_1 ().push (StartMainGame.pop_card_buffer (-1));
            }
        }
        else if (card_buff_last_location == "tableau2")
        {
            while (!StartMainGame.card_buffer_is_empty ())
            {
                StartMainGame.getTableau_2 ().push (StartMainGame.pop_card_buffer (-1));
            }
        }
        else if (card_buff_last_location == "tableau3")
        {
            while (!StartMainGame.card_buffer_is_empty ())
            {
                StartMainGame.getTableau_3 ().push (StartMainGame.pop_card_buffer (-1));
            }
        }
        else if (card_buff_last_location == "tableau4")
        {
            while (!StartMainGame.card_buffer_is_empty ())
            {
                StartMainGame.getTableau_4 ().push (StartMainGame.pop_card_buffer (-1));
            }
        }
        else if (card_buff_last_location == "tableau5")
        {
            while (!StartMainGame.card_buffer_is_empty ())
            {
                StartMainGame.getTableau_5 ().push (StartMainGame.pop_card_buffer (-1));
            }
        }
        else if (card_buff_last_location == "tableau6")
        {
            while (!StartMainGame.card_buffer_is_empty ())
            {
                StartMainGame.getTableau_6 ().push (StartMainGame.pop_card_buffer (-1));
            }
        }
        else if (card_buff_last_location == "tableau7")
        {
            while (!StartMainGame.card_buffer_is_empty ())
            {
                StartMainGame.getTableau_7 ().push (StartMainGame.pop_card_buffer (-1));
            }
        }
    }


    public void mouseDragged (MouseEvent e)
    {
        if (is_card_buffer_single ())
        {
            // while the move_flag is active, the card obj(s) are ok to move and hence need to be updated
            if (move_flag)
            {
                StartMainGame.get_top_card_buffer ().setCentreCoords (e.getX (), e.getY ());
                paint_card_buffer_code = 1;
                repaint ();
                paint_card_buffer_code = 0;
            }
        }
        else if (is_card_buffer_not_single ())
        {
            if (move_flag)
            {
                int y_accum = 0;
                for (int i = 0 ; i < StartMainGame.card_buffer.size () ; ++i)
                {
                    StartMainGame.get_card_buffer_element (StartMainGame.getCard_buffer ().size () - i - 1).setCentreCoords (e.getX (), e.getY () + y_accum);
                    y_accum += (int) (StartMainGame.get_top_card_buffer ().getHeight () / 4); // this assumes that the cards in the card_buffer are the same size
                }
                paint_card_buffer_code = 2;
                repaint ();
                paint_card_buffer_code = 0;
            }
        }
    }


    public void mouseMoved (MouseEvent e)
    {
        /* mouse_x = e.getX ();
        mouse_y = e.getY ();
        if ((!StartMainGame.card_buffer_is_empty()))
        {
                if (card_buff_last_location == "dpile")
                {
                    if ((StartMainGame.get_top_card_buffer().is_point_inside(e.getX (), e.getY ())))
                    {
                        StartMainGame.get_top_card_buffer().setCentreCoords(e.getX (), e.getY ());
                        repaint();
                    }
                }
        } */
    }


    public static int get_screen_x ()
    {
        return screen_x;
    }


    public static int get_screen_y ()
    {
        return screen_y;
    }


    public static boolean isDebugMode ()
    {
        return debug_mode;
    }


    public static int getMoves_left ()
    {
        return moves_left;
    }


    public static int getDifficulty ()
    {
        return difficulty;
    }
}
