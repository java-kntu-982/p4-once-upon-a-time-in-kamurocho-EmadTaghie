package ir.ac.kntu;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class RyujiGoda extends FriendlySoldier{
    public RyujiGoda(Point2D pos, ArrayList<FriendlySoldier> friendlySoldiers, ArrayList<EnemySoldier> enemySoldiers, int dps, int id, Block block){
        super(5000, 5000, 2, Color.AZURE, friendlySoldiers,"Ryuji Goda", pos, 1, enemySoldiers, dps, id, block);
    }
}
