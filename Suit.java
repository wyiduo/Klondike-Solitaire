public abstract class Suit extends ShapeClass
{
    public void setWidth (int iNewWidth)
    {
        super.setWidth (iNewWidth);
        super.setHeight ((int) Math.rint (iNewWidth / (0.8))); // 4/5 = 0.8
    }


    public void setHeight (int iNewHeight)
    {
        super.setHeight (iNewHeight);
        super.setWidth ((int) Math.rint (iNewHeight * 4 / 5));
    }


    public int getWidth ()
    {
        try
        {
            if (iHeight * 4 / 5 == iWidth)
            {
                return iWidth;
            }
        }
        catch (ArithmeticException e)
        {
            System.out.println ("ArithmeticException thrown : " + e);
        }
        return -1;
    }


    public int getHeight ()
    {
        try
        {
            if (iHeight * 4 / 5 == iWidth)
            {
                return iHeight;
            }
        }
        catch (ArithmeticException e)
        {
            System.out.println ("ArithmeticException thrown : " + e);
        }
        return -1;
    }
}
