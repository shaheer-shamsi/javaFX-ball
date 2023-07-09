package dxball;


import static dxball.GameScene.dx;
import static dxball.GameScene.dy;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class CalculateGen<T> {

    void checkBrickCollision(Circle ball, ArrayList<Rectangle> bricks, Group group) {
        for (Rectangle r : bricks) {
            if (ball.getBoundsInParent().intersects(r.getBoundsInParent())) {

                int i = bricks.indexOf(r);
                bricks.remove(i);
                group.getChildren().remove(r);
                break;
            }
        }
    }

    void checkSceneCollision(Circle ball, Scene scene) {
        double xMin = ball.getBoundsInParent().getMinX();
        double yMin = ball.getBoundsInParent().getMinY();
        double xMax = ball.getBoundsInParent().getMaxX();
        double yMax = ball.getBoundsInParent().getMaxY();
        if(xMin < 0) dx = 1;
        if(xMax > scene.getWidth()) dx = -1;
        if(yMin < 0) dy = 1;
        if(yMax > scene.getHeight()) dy = -1;
        
    }

    void checkPaddleCollision(Circle ball, T paddle) {

            dy = -1;
            double xMin = ball.getBoundsInParent().getMinX();
            double xMax = ball.getBoundsInParent().getMaxX();  
            double pxMin;
            
        }
        
}