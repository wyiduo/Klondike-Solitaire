import java.awt.*;

public class Card extends ShapeClass
{
    private char card_suit = 'S', face_value = 'A', card_size = 'S';
    private boolean is_face_down = false;
    int face_value_reference = 1;

    public void set_face_value_reference (int int_arg)
    {
        face_value_reference = int_arg;
    }


    public int get_face_value_reference ()
    {
        return face_value_reference;
    }


    public void set_suit (char char_arg)
    {
        card_suit = char_arg;
    }


    public char get_suit ()
    {
        return card_suit;
    }


    public void set_face_value (char char_arg)
    {
        face_value = char_arg;

        switch (char_arg)
        {
            case 'A':
                face_value_reference = 1;
                break;
            case '2':
                face_value_reference = 2;
                break;
            case '3':
                face_value_reference = 3;
                break;
            case '4':
                face_value_reference = 4;
                break;
            case '5':
                face_value_reference = 5;
                break;
            case '6':
                face_value_reference = 6;
                break;
            case '7':
                face_value_reference = 7;
                break;
            case '8':
                face_value_reference = 8;
                break;
            case '9':
                face_value_reference = 9;
                break;
            case 'T':
                face_value_reference = 10;
                break;
            case 'J':
                face_value_reference = 11;
                break;
            case 'Q':
                face_value_reference = 12;
                break;
            case 'K':
                face_value_reference = 13;
                break;
        }
    }


    public char get_face_value ()
    {
        return face_value;
    }


    public void set_size (char char_arg)
    {
        card_size = char_arg;

        if (char_arg == 'S')
        {
            this.setHeight (60);
            this.setWidth (42);
        }
        else if (char_arg == 'M')
        {
            this.setHeight (80);
            this.setWidth (56);
        }
        else if (char_arg == 'L')
        {
            this.setHeight (100);
            this.setWidth (70);
        }
        else if (char_arg == 'X')
        {
            this.setHeight (120);
            this.setWidth (84);
        }
    }


    public char get_size ()
    {
        return card_size;
    }


    public void set_is_face_down (boolean bool_arg)
    {
        is_face_down = bool_arg;
    }


    public boolean get_is_face_down ()
    {
        return is_face_down;
    }


    public void draw (Graphics g)
    {
        g.setColor (iColour);

        if (!is_face_down)
        {
            isFilled = false;

            // draws the card box
            g.setColor (Color.lightGray);
            g.fillRect (this.start_x, this.start_y, this.iWidth, this.iHeight);
            g.setColor (Color.black);
            g.drawRect (this.start_x, this.start_y, this.iWidth, this.iHeight);

            // draws the card suit
            if (card_suit == 'C')
            {
                Club club_obj = new Club ((int) getWidth () / 4, (int) getHeight () / 4, iCentreX, iCentreY, Color.black);

                club_obj.draw (g);
            }
            else if (card_suit == 'D')
            {
                Diamond diamond_obj = new Diamond ((int) getWidth () / 4, (int) getHeight () / 4, iCentreX, iCentreY, Color.red);

                diamond_obj.draw (g);
            }
            else if (card_suit == 'H')
            {
                Heart heart_obj = new Heart ((int) getWidth () / 4, (int) getHeight () / 4, iCentreX, iCentreY, Color.red);

                heart_obj.draw (g);
            }
            else if (card_suit == 'S')
            {
                Spade spade_obj = new Spade ((int) getWidth () / 4, (int) getHeight () / 4, iCentreX, iCentreY, Color.black);

                spade_obj.draw (g);
            }

            Font font_1 = new Font ("Serif", Font.BOLD, 30);

            g.setFont (font_1);
            // determines the colour to use based on suit
            switch (card_suit)
            {
                case 'D':
                    g.setColor (Color.red);
                    break;
                case 'C':
                    g.setColor (Color.black);
                    break;
                case 'H':
                    g.setColor (Color.red);
                    break;
                case 'S':
                    g.setColor (Color.black);
                    break;
            }

            final int FACE_VALUE_SPACING_HEIGHT = 25, FACE_VALUE_SPACING_WIDTH = 5;
            if (face_value != 'T')
            {
                String temp_str = String.valueOf (face_value);
                g.drawString (temp_str, iCentreX - (int) (iWidth / 2) + FACE_VALUE_SPACING_WIDTH, iCentreY - (int) (iHeight / 2) + FACE_VALUE_SPACING_HEIGHT);
            }
            else
            {
                g.drawString ("10", iCentreX - (int) (iWidth / 2) + FACE_VALUE_SPACING_WIDTH, iCentreY - (int) (iHeight / 2) + FACE_VALUE_SPACING_HEIGHT);
            }
        }
        else
        {
            isFilled = true;

            // draws the card box
            g.fillRect (this.start_x, this.start_y, this.iWidth, this.iHeight);
            g.setColor (Color.black);
            g.drawRect (this.start_x, this.start_y, this.iWidth, this.iHeight);
        }
    }
}
