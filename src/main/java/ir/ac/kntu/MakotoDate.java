package ir.ac.kntu;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class MakotoDate extends FriendlySoldier{
    public MakotoDate(Point2D pos, ArrayList<FriendlySoldier> friendlySoldiers, ArrayList<EnemySoldier> enemySoldiers, int dps, int id, Block block){
        super(1800, 4500, 3, Color.BROWN, friendlySoldiers,"Makoto Date", pos, 2, enemySoldiers, dps, id, block);
    }
}
