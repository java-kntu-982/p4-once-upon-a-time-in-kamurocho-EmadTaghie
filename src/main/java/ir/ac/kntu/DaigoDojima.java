package ir.ac.kntu;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class DaigoDojima extends FriendlySoldier{
    public DaigoDojima(Point2D pos, ArrayList<FriendlySoldier> friendlySoldiers, ArrayList<EnemySoldier> enemySoldiers, int dps, int id, Block block){
        super(4000, 4000, 2,Color.BLUE, friendlySoldiers,"Daigo Dojima", pos, 1, enemySoldiers, dps, id, block);
    }
}
