package ir.ac.kntu;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class FutoshiShimano extends FriendlySoldier{
    public FutoshiShimano(Point2D pos, ArrayList<FriendlySoldier> friendlySoldiers, ArrayList<EnemySoldier> enemySoldiers, int dps, int id, Block block){
        super(4000, 4200, 1, Color.FIREBRICK, friendlySoldiers,"Futoshi Shimano", pos, 2, enemySoldiers, dps, id, block);
    }
}
