import java.awt.*;

public class Spade extends Suit
{
    public Spade ()
    {
    }


    public Spade (int iNewWidth, int iNewHeight, int iNewCentreX, int iNewCentreY, Color cNewColor)
    {
        iWidth = iNewWidth;
        iHeight = iNewHeight;
        iCentreX = iNewCentreX;
        iCentreY = iNewCentreY;
        iColour = cNewColor;
    }


    public void draw (Graphics g)
    {
        int iPointsX[] = new int [5];
        int iPointsY[] = new int [5];

        iPointsX [0] = iCentreX - iWidth / 2;
        iPointsY [0] = iCentreY;
        iPointsX [1] = iCentreX + iWidth / 2;
        iPointsY [1] = iCentreY;
        iPointsX [2] = iCentreX;
        iPointsY [2] = iCentreY - iHeight / 2;
        iPointsX [3] = iCentreX - iWidth / 2;
        iPointsY [3] = iCentreY - iHeight / 4;
        iPointsX [4] = iCentreX;
        iPointsY [4] = iCentreY - iHeight / 4;

        int triPointsX[] = new int [3];
        int triPointsY[] = new int [3];

        triPointsX [0] = iCentreX;
        triPointsY [0] = iCentreY;
        triPointsX [1] = iCentreX - iWidth / 8;
        triPointsY [1] = iCentreY + iHeight / 2;
        triPointsX [2] = iCentreX + iWidth / 8;
        triPointsY [2] = iCentreY + iHeight / 2;

        g.setColor (iColour);

        isFilled = true;

        g.fillArc (iPointsX [3], iPointsY [3], iWidth / 2, iHeight / 2, 180, 180);
        g.fillArc (iPointsX [4], iPointsY [4], iWidth / 2, iHeight / 2, 180, 180);
        g.fillPolygon (iPointsX, iPointsY, 3);
        g.fillPolygon (triPointsX, triPointsY, 3);
    }
}
