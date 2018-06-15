import java.awt.*;
import java.util.*;  // Vector class is in the 'util'  package

public class Deck
{
    protected Vector deck = new Vector (0, 1); // instantiate vector object with a capacity of 0 and to grow the capacity by 1 everytime the vector is full
    char size = 'S';
    int width = 50, height = 50, deck_x = 50, deck_y = 50;
    Color deck_colour = Color.black;

    // Initializes a standard deck
    public void init_std_deck ()
    {
        final char STD_CARD_SIZE = this.size;
        final boolean STD_IS_CARD_FACEDOWN = true;
        final Color STD_CARD_COLOUR = this.deck_colour;
        final int STD_START_X = this.deck_x, STD_START_Y = this.deck_y;

        // 52 cards = 13 face values * 4 suits
        for (int i = 0 ; i < 13 ; ++i)
        {
            for (int j = 0 ; j < 4 ; ++j)
            {
                if (i == 0)
                {
                    if (j == 0)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('A');
                        card_obj.set_suit ('D');

                        addCard (card_obj, 0);
                    }
                    else if (j == 1)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('A');
                        card_obj.set_suit ('C');

                        addCard (card_obj, 0);
                    }
                    else if (j == 2)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('A');
                        card_obj.set_suit ('H');

                        addCard (card_obj, 0);
                    }
                    else if (j == 3)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('A');
                        card_obj.set_suit ('S');

                        addCard (card_obj, 0);
                    }
                }
                else if (i == 1)
                {
                    if (j == 0)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('2');
                        card_obj.set_suit ('D');

                        addCard (card_obj, 0);
                    }
                    else if (j == 1)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('2');
                        card_obj.set_suit ('C');

                        addCard (card_obj, 0);
                    }
                    else if (j == 2)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('2');
                        card_obj.set_suit ('H');

                        addCard (card_obj, 0);
                    }
                    else if (j == 3)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('2');
                        card_obj.set_suit ('S');

                        addCard (card_obj, 0);
                    }
                }
                else if (i == 2)
                {
                    if (j == 0)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('3');
                        card_obj.set_suit ('D');

                        addCard (card_obj, 0);
                    }
                    else if (j == 1)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('3');
                        card_obj.set_suit ('C');

                        addCard (card_obj, 0);
                    }
                    else if (j == 2)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('3');
                        card_obj.set_suit ('H');

                        addCard (card_obj, 0);
                    }
                    else if (j == 3)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('3');
                        card_obj.set_suit ('S');

                        addCard (card_obj, 0);
                    }
                }
                else if (i == 3)
                {
                    if (j == 0)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('4');
                        card_obj.set_suit ('D');

                        addCard (card_obj, 0);
                    }
                    else if (j == 1)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('4');
                        card_obj.set_suit ('C');

                        addCard (card_obj, 0);
                    }
                    else if (j == 2)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('4');
                        card_obj.set_suit ('H');

                        addCard (card_obj, 0);
                    }
                    else if (j == 3)
                    {
                        Card card_obj = new Card ();
                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('4');
                        card_obj.set_suit ('S');

                        addCard (card_obj, 0);
                    }
                }
                else if (i == 4)
                {
                    if (j == 0)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('5');
                        card_obj.set_suit ('D');

                        addCard (card_obj, 0);
                    }
                    else if (j == 1)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('5');
                        card_obj.set_suit ('C');

                        addCard (card_obj, 0);
                    }
                    else if (j == 2)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('5');
                        card_obj.set_suit ('H');

                        addCard (card_obj, 0);
                    }
                    else if (j == 3)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('5');
                        card_obj.set_suit ('S');

                        addCard (card_obj, 0);
                    }
                }
                else if (i == 5)
                {
                    if (j == 0)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('6');
                        card_obj.set_suit ('D');

                        addCard (card_obj, 0);
                    }
                    else if (j == 1)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('6');
                        card_obj.set_suit ('C');

                        addCard (card_obj, 0);
                    }
                    else if (j == 2)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('6');
                        card_obj.set_suit ('H');

                        addCard (card_obj, 0);
                    }
                    else if (j == 3)
                    {
                        Card card_obj = new Card ();
                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('6');
                        card_obj.set_suit ('S');

                        addCard (card_obj, 0);
                    }
                }
                else if (i == 6)
                {
                    if (j == 0)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('7');
                        card_obj.set_suit ('D');

                        addCard (card_obj, 0);
                    }
                    else if (j == 1)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('7');
                        card_obj.set_suit ('C');

                        addCard (card_obj, 0);
                    }
                    else if (j == 2)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('7');
                        card_obj.set_suit ('H');

                        addCard (card_obj, 0);
                    }
                    else if (j == 3)
                    {
                        Card card_obj = new Card ();
                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('7');
                        card_obj.set_suit ('S');

                        addCard (card_obj, 0);
                    }
                }
                else if (i == 7)
                {
                    if (j == 0)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('8');
                        card_obj.set_suit ('D');

                        addCard (card_obj, 0);
                    }
                    else if (j == 1)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('8');
                        card_obj.set_suit ('C');

                        addCard (card_obj, 0);
                    }
                    else if (j == 2)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('8');
                        card_obj.set_suit ('H');

                        addCard (card_obj, 0);
                    }
                    else if (j == 3)
                    {
                        Card card_obj = new Card ();
                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('8');
                        card_obj.set_suit ('S');

                        addCard (card_obj, 0);
                    }
                }
                else if (i == 8)
                {
                    if (j == 0)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('9');
                        card_obj.set_suit ('D');

                        addCard (card_obj, 0);
                    }
                    else if (j == 1)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('9');
                        card_obj.set_suit ('C');

                        addCard (card_obj, 0);
                    }
                    else if (j == 2)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('9');
                        card_obj.set_suit ('H');

                        addCard (card_obj, 0);
                    }
                    else if (j == 3)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('9');
                        card_obj.set_suit ('S');

                        addCard (card_obj, 0);
                    }
                }
                else if (i == 9)
                {
                    if (j == 0)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('T');
                        card_obj.set_suit ('D');

                        addCard (card_obj, 0);
                    }
                    else if (j == 1)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('T');
                        card_obj.set_suit ('C');

                        addCard (card_obj, 0);
                    }
                    else if (j == 2)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('T');
                        card_obj.set_suit ('H');

                        addCard (card_obj, 0);
                    }
                    else if (j == 3)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('T');
                        card_obj.set_suit ('S');

                        addCard (card_obj, 0);
                    }
                }
                else if (i == 10)
                {
                    if (j == 0)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('J');
                        card_obj.set_suit ('D');

                        addCard (card_obj, 0);
                    }
                    else if (j == 1)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('J');
                        card_obj.set_suit ('C');

                        addCard (card_obj, 0);
                    }
                    else if (j == 2)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('J');
                        card_obj.set_suit ('H');

                        addCard (card_obj, 0);
                    }
                    else if (j == 3)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('J');
                        card_obj.set_suit ('S');

                        addCard (card_obj, 0);
                    }
                }
                else if (i == 11)
                {
                    if (j == 0)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('Q');
                        card_obj.set_suit ('D');

                        addCard (card_obj, 0);
                    }
                    else if (j == 1)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('Q');
                        card_obj.set_suit ('C');

                        addCard (card_obj, 0);
                    }
                    else if (j == 2)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('Q');
                        card_obj.set_suit ('H');

                        addCard (card_obj, 0);
                    }
                    else if (j == 3)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('Q');
                        card_obj.set_suit ('S');

                        addCard (card_obj, 0);
                    }
                }
                else if (i == 12)
                {
                    if (j == 0)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('K');
                        card_obj.set_suit ('D');

                        addCard (card_obj, 0);
                    }
                    else if (j == 1)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('K');
                        card_obj.set_suit ('C');

                        addCard (card_obj, 0);
                    }
                    else if (j == 2)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('K');
                        card_obj.set_suit ('H');

                        addCard (card_obj, 0);
                    }
                    else if (j == 3)
                    {
                        Card card_obj = new Card ();

                        card_obj.set_size (STD_CARD_SIZE);
                        card_obj.set_is_face_down (STD_IS_CARD_FACEDOWN);
                        card_obj.setColour (STD_CARD_COLOUR);
                        card_obj.setStart_x (STD_START_X);
                        card_obj.setStart_y (STD_START_Y);

                        card_obj.set_face_value ('K');
                        card_obj.set_suit ('S');

                        addCard (card_obj, 0);
                    }
                }
            }
        }
    }


    // add cards to the deck pile
    public void addCard (Card cardToAdd, int Pos)
    {
        cardToAdd.setStart_x (deck_x);
        cardToAdd.setStart_y (deck_y);
        cardToAdd.set_is_face_down (true);

        if (Pos == 0 || deck.size () == 0)
        {
            deck.addElement (cardToAdd);
        }
        else if (Pos > deck.size ())
        {
            deck.insertElementAt (cardToAdd, deck.size ());
        }
        else
        {
            deck.insertElementAt (cardToAdd, Pos);
        }
    }


    // removes a card at a certain position
    public Card dealCard (int Pos)
    {
        if (Pos == -1)
        {
            return (Card) deck.remove (deck.size () - 1);
        }
        else
        {
            return (Card) deck.remove (Pos);   // must type cast element
        }
    }


    // shuffles the deck
    public void shuffle ()
    {
        Collections.shuffle (deck);
    }


    public void draw (Graphics g)
    {
        if (deck.isEmpty ())
        {
            g.setColor (Color.black);
            g.drawRect (deck_x, deck_y, width, height);
            g.drawOval (deck_x + (int) ((width - (height / 3)) / 2), deck_y + (int) ((height - (height / 3)) / 2), (int) (height / 3), (int) (height / 3));
        }
        else
        {
            g.setColor (deck_colour);
            // g.fillRect (deck_x, deck_y, width, height); // default for facedown cards
            Card temp_card = (Card) deck.get (deck.size () - 1);
            temp_card.draw (g);
        }
    }


    public void erase (Graphics g)
    {
        g.setColor (Color.white);
        g.fillRect (deck_x, deck_y, width, height);
    }


    public char get_size ()
    {
        return size;
    }


    public void set_size (char foundation_size)
    {
        this.size = foundation_size;

        if (foundation_size == 'S')
        {
            height = 60;
            width = 42;
        }
        else if (foundation_size == 'M')
        {
            height = 80;
            width = 56;
        }
        else if (foundation_size == 'L')
        {
            height = 100;
            width = 70;
        }
        else if (foundation_size == 'X')
        {
            height = 120;
            width = 84;
        }
    }


    public int get_deck_x ()
    {
        return deck_x;
    }


    public void set_deck_x (int deck_x)
    {
        this.deck_x = deck_x;
    }


    public int get_deck_y ()
    {
        return deck_y;
    }


    public void set_deck_y (int deck_y)
    {
        this.deck_y = deck_y;
    }


    public Color get_deck_colour ()
    {
        return deck_colour;
    }


    public void set_deck_colour (Color deck_colour)
    {
        this.deck_colour = deck_colour;
    }


    public boolean is_point_inside (int x, int y)
    {
        // checks if point is between the start_x of deck and the start_x + width
        if (x > deck_x && x < (deck_x + width))
        {
            if (y > deck_y && y < (deck_y + height))
            {
                return true;
            }
        }
        return false;
    }


    public boolean is_empty ()
    {
        return deck.size () == 0;
    }
} // Deck class
