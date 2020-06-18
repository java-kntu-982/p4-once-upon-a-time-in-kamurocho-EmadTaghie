package ir.ac.kntu;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class BossLVL1 extends EnemySoldier{
    public BossLVL1(Structure[] structures, Point2D pos, ArrayList<FriendlySoldier> enemySoldiers, ArrayList<EnemySoldier> friendSoldiers, int dps, int id, Block block){
        super(5000, 8000, 1, 2, Color.PURPLE, "Boss level1", enemySoldiers, structures, pos, friendSoldiers, dps, id, block);
    }
}
