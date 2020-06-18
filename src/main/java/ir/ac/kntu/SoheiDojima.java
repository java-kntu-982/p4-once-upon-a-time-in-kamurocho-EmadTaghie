package ir.ac.kntu;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class SoheiDojima extends FriendlySoldier{
    public SoheiDojima(Point2D pos, ArrayList<FriendlySoldier> friendlySoldiers, ArrayList<EnemySoldier> enemySoldiers, int dps, int id, Block block){
        super(3000, 3000, 1, Color.ORANGE, friendlySoldiers,"Sohei Dojima", pos, 1, enemySoldiers, dps, id, block);
    }
}
