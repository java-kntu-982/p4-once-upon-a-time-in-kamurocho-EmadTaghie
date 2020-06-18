package ir.ac.kntu;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ShintaroKazama extends FriendlySoldier{
    public ShintaroKazama(Point2D pos, ArrayList<FriendlySoldier> friendlySoldiers, ArrayList<EnemySoldier> enemySoldiers, int dps, int id, Block block){
        super(4500, 4500, 1, Color.GREEN, friendlySoldiers,"Shintaro Kazama", pos, 2, enemySoldiers, dps, id, block);
    }
}
