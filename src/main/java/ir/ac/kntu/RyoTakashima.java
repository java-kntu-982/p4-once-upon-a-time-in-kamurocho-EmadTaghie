package ir.ac.kntu;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class RyoTakashima extends FriendlySoldier{
    public RyoTakashima(Point2D pos, ArrayList<FriendlySoldier> friendlySoldiers, ArrayList<EnemySoldier> enemySoldiers, int dps, int id, Block block){
        super(3800, 3600, 1, Color.BLUE, friendlySoldiers,"Ryo Takashima", pos, 1, enemySoldiers, dps, id, block);
    }
}
