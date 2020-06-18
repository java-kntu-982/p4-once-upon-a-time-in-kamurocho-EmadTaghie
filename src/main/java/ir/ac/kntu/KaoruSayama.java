package ir.ac.kntu;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class KaoruSayama extends FriendlySoldier{
    public KaoruSayama(Point2D pos, ArrayList<FriendlySoldier> friendlySoldiers, ArrayList<EnemySoldier> enemySoldiers, int dps, int id, Block block){
        super(4500, 4500, 4, Color.YELLOW, friendlySoldiers,"Kaoru Sayama", pos, 2, enemySoldiers, dps, id, block);
    }
}
