package ir.ac.kntu;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class JiroKawara extends FriendlySoldier{
    public JiroKawara(Point2D pos, ArrayList<FriendlySoldier> friendlySoldiers, ArrayList<EnemySoldier> enemySoldiers, int dps, int id, Block block){
        super(3200, 5500, 3, Color.GRAY, friendlySoldiers,"Jiro Kawara", pos, 3, enemySoldiers, dps, id, block);
    }
}
