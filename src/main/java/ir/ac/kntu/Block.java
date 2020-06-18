package ir.ac.kntu;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Block {
    private List<Rectangle> blocks;
    private Group root;

    private final double blockHeight = 60;
    private final double blockWidth = 10;

    public Block(){
        blocks = new ArrayList<>();
        root = new Group();
    }

    public Group creatBlocks(double windowsWidth, double windowsHeight){
        double spacing = (windowsHeight - 4 * blockHeight)/3;
        for(int i = 0; i < 4; i++){
            blocks.add(new Rectangle(windowsWidth/2, (blockHeight + spacing)*i , blockWidth, blockHeight));
            blocks.get(i).setFill(Color.RED);
            blocks.get(i).setStroke(Color.BLACK);
        }
        root.getChildren().addAll(blocks);
        return root;
    }

    public List<Rectangle> getBlocks() {
        return new ArrayList<>(blocks);
    }

    public Group getRoot() {
        return root;
    }

    public int isInBoundary(Soldier soldier, double angle){
        for(Rectangle i: blocks){
            if(soldier.getDistance(soldier.nextPoint(angle), findClosestPointToSol(i, soldier)) < soldier.getShape().getRadius()){
                return blocks.indexOf(i);
            }
        }
        return -1;
    }

    public Point2D findClosestPointToSol(Rectangle block, Soldier soldier){
        Point2D closest = new Point2D(block.getX(), block.getY());
        for(int j = 0; j < 2; j++) {
            for (double i = 0; i <= block.getHeight(); i += 0.5) {
                if(soldier.getDistance(soldier.getPos(), new Point2D(block.getX() + j * block.getWidth(), block.getY() + i))
                        < soldier.getDistance(soldier.getPos(), closest)){
                    closest = new Point2D(block.getX() + j * block.getWidth(), block.getY() + i);
                }
            }
            for (double i = 0; i <= block.getWidth(); i += 0.5) {
                if(soldier.getDistance(soldier.getPos(), new Point2D(block.getX() + i, block.getY() + j * block.getHeight()))
                        < soldier.getDistance(soldier.getPos(), closest)){
                    closest = new Point2D(block.getX() + i, block.getY() + j * block.getHeight());
                }
            }
        }
        return closest;
    }
}
