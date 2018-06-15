import java.awt.*;

public class Heart extends Suit
{
    public Heart ()
    {
    }


    public Heart (int iNewWidth, int iNewHeight, int iNewCentreX, int iNewCentreY, Color cNewColor)
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
        iPointsY [2] = iCentreY + iHeight / 2;
        iPointsX [3] = iCentreX - iWidth / 2;
        iPointsY [3] = iCentreY - iHeight / 4;
        iPointsX [4] = iCentreX;
        iPointsY [4] = iCentreY - iHeight / 4;

        g.setColor (iColour);

        isFilled = true;

        g.fillArc (iPointsX [3], iPointsY [3], iWidth / 2, iHeight / 2, 0, 180);
        g.fillArc (iPointsX [4], iPointsY [4], iWidth / 2, iHeight / 2, 0, 180);
        g.fillPolygon (iPointsX, iPointsY, 3);
    }
}
