package ir.ac.kntu;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class OsamuKashiwagi extends FriendlySoldier{
    public OsamuKashiwagi(Point2D pos, ArrayList<FriendlySoldier> friendlySoldiers, ArrayList<EnemySoldier> enemySoldiers, int dps, int id, Block block){
        super(3000, 4000, 1, Color.BLACK, friendlySoldiers,"Osamu Kashiwagi", pos, 2, enemySoldiers, dps, id, block);
    }
}
