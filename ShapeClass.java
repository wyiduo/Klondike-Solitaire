import java.awt.*;

public abstract class ShapeClass
{
    protected int iHeight = 100, iWidth = 100, iCentreX = 100, iCentreY = 100, start_x = 100, start_y = 100;
    protected Color iColour = Color.red;
    protected boolean isFilled = true;

    public int getStart_x ()
    {
        return start_x;
    }


    public void setStart_x (int start_x)
    {
        this.start_x = start_x;
        this.iCentreX = (int) (iWidth / 2) + start_x;
    }


    public int getStart_y ()
    {
        return start_y;
    }


    public void setStart_y (int start_y)
    {
        this.start_y = start_y;
        this.iCentreY = (int) (iHeight / 2) + start_y;
    }


    public void setWidth (int iNewWidth)
    {
        iWidth = iNewWidth;
    }


    public void setHeight (int iNewHeight)
    {
        iHeight = iNewHeight;
    }


    public void setCentreCoords (int iNewCentreX, int iNewCentreY)
    {
        iCentreX = iNewCentreX;
        iCentreY = iNewCentreY;

        this.start_x = iCentreX - (int) (iWidth / 2);
        this.start_y = iCentreY - (int) (iHeight / 2);
    }


    public void setCentreX (int iNewCentreX)
    {
        iCentreX = iNewCentreX;

        this.start_x = iCentreX - (int) (iWidth / 2);
    }


    public void setCentreY (int iNewCentreY)
    {
        iCentreY = iNewCentreY;

        this.start_y = iCentreY - (int) (iHeight / 2);
    }


    public void setColour (Color cNewColor)
    {

        iColour = cNewColor;
    }


    public int getWidth ()
    {
        return iWidth;
    }


    public int getHeight ()
    {
        return iHeight;
    }


    public int getCentreX ()
    {
        return iCentreX;
    }


    public int getCentreY ()
    {
        return iCentreY;
    }


    public Color getColour ()
    {
        return iColour;
    }


    public void setIsFilled (boolean newfilled)
    {
        isFilled = newfilled;
    }


    public boolean getIsFilled ()
    {
        return isFilled;
    }


    public abstract void draw (Graphics g);


    public void erase (Graphics g)
    {
        Color iOldColour = getColour ();
        setColour (Color.white);
        draw (g);
        setColour (iOldColour);
    }


    public void delay (int iDelayTime)
    {
        long lFinalTime = System.currentTimeMillis () + iDelayTime;

        for (;;)
        {
            if (System.currentTimeMillis () > lFinalTime)
            {
                break;
            }
        }
    }


    public boolean is_point_inside (int x, int y)
    {
        if (x > start_x && x < (start_x + iWidth))
        {
            if (y > start_y && y < (start_y + iHeight))
            {
                return true;
            }
        }
        return false;
    }
}
