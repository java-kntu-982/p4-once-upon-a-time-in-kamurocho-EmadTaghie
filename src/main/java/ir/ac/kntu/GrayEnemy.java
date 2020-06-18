package ir.ac.kntu;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GrayEnemy extends EnemySoldier{
    public GrayEnemy(Structure[] structures, Point2D pos, ArrayList<FriendlySoldier> enemySoldiers, ArrayList<EnemySoldier> friendSoldiers, int dps, int id, Block block){
        super(800, 1000, 1, 2, Color.GRAY, "Gray", enemySoldiers, structures, pos, friendSoldiers, dps, id, block);
    }
}
