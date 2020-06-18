package ir.ac.kntu;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class YellowEnemy extends EnemySoldier{
    public YellowEnemy(Structure[] structures, Point2D pos, ArrayList<FriendlySoldier> enemySoldiers, ArrayList<EnemySoldier> friendSoldiers, int dps, int id, Block block){
        super(1800, 800, 2, 1, Color.YELLOW, "Yellow", enemySoldiers,structures, pos, friendSoldiers, dps, id, block);
    }
}
