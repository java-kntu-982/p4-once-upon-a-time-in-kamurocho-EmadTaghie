package ir.ac.kntu;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class TaigaSaejima extends FriendlySoldier{
    public TaigaSaejima(Point2D pos, ArrayList<FriendlySoldier> friendlySoldiers, ArrayList<EnemySoldier> enemySoldiers, int dps, int id, Block block){
        super(5000, 7000, 1, Color.RED, friendlySoldiers,"Taiga Saejima", pos, 1, enemySoldiers, dps, id, block);
    }
}
