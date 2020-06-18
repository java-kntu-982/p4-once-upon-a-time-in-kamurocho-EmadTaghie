package ir.ac.kntu;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class BossLvL2 extends EnemySoldier{
    public BossLvL2(Structure[] structures, Point2D pos, ArrayList<FriendlySoldier> enemySoldiers, ArrayList<EnemySoldier> friendSoldiers, int dps, int id, Block block){
        super(12000, 20000, 1, 2, Color.BLUE, "Boss level2", enemySoldiers, structures, pos, friendSoldiers, dps, id, block);
    }
}
