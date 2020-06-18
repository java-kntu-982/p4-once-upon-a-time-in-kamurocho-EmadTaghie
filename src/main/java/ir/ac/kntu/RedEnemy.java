package ir.ac.kntu;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class RedEnemy extends EnemySoldier{
    public RedEnemy(Structure[] structures, Point2D pos, ArrayList<FriendlySoldier> enemySoldiers, ArrayList<EnemySoldier> friendSoldiers, int dps, int id, Block block){
        super(500, 1000, 1, 3, Color.RED, "Red", enemySoldiers, structures, pos, friendSoldiers, dps, id, block);
    }
}
