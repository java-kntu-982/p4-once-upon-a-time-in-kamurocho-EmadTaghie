package ir.ac.kntu;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class GoroMajima extends FriendlySoldier{
    public GoroMajima(Point2D pos, ArrayList<FriendlySoldier> friendlySoldiers, ArrayList<EnemySoldier> enemySoldiers, int dps, int id, Block block){
        super(4500, 4000, 4, Color.PURPLE, friendlySoldiers,"Goro Majima", pos, 3, enemySoldiers, dps, id, block);
    }
}
