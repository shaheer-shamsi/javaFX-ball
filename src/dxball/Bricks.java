
package dxball;

import java.util.ArrayList;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bricks extends Shapes{
    int i, j, width=50, height=15, rows=8, columns=13, gap=3;
    Bricks(int x, int y)
    {
        super(x, y);
    }
    ArrayList<Rectangle> getBricks()
    {
        ArrayList<Rectangle> bricks = new ArrayList<Rectangle>();
        for(i=0; i<rows; i++)
        {
            for(int j=0; j<columns; j++)
            {
                Rectangle r = new Rectangle(x+j*(width+gap), y+i*(height+gap), width, height);
                if(i%2==0)
                    r.setFill(Color.ALICEBLUE);
                else
                    r.setFill(Color.ANTIQUEWHITE);
                InnerShadow e = new InnerShadow();
                e.setWidth(15);
                e.setHeight(15);
                e.setColor(Color.BLACK);
                r.setEffect(e);
                //r.setArcHeight(5);
                //r.setArcWidth(5);
                bricks.add(r);
            }
        }
        return bricks;
    }    
}
