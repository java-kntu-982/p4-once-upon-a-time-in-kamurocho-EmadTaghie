package ir.ac.kntu;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public abstract class FriendlySoldier extends Soldier{
    private double FOV;
    private Circle friendMarker;
    private ArrayList<EnemySoldier> enemySoldiers;
    private EnemySoldier target;
    private ArrayList<FriendlySoldier> friendlySoldiers;
    private Circle marker;

    private final int fixFOV = 60;

    public FriendlySoldier(int damage, double health, int attackRange, Color color, ArrayList<FriendlySoldier> friendlySoldiers,
                           String name, Point2D pos, int FOV, ArrayList<EnemySoldier> enemySoldiers, int dps, int id, Block block){
        super(damage, health, attackRange, 4, color, name, pos, dps, id, block);
        this.FOV = FOV * fixFOV;
        this.enemySoldiers = enemySoldiers;
        this.friendlySoldiers = friendlySoldiers;
        marker = new Circle(12, Color.TRANSPARENT);
        marker.setStroke(Color.BLUE);
        marker.setTranslateX(pos.getX());
        marker.setTranslateY(pos.getY());
        marker.setVisible(false);
        if(!enemySoldiers.isEmpty()) {
            target = enemySoldiers.get(0);
        }
        friendMarker = new Circle(shape.getRadius()/2, Color.WHITE);
        friendMarker.setTranslateX(pos.getX());
        friendMarker.setTranslateY(pos.getY());
        root.getChildren().addAll(friendMarker, marker);
    }

    public void setTarget(EnemySoldier target) {
        this.target = target;
    }

    @Override
    public void attack() {
        if(actionInPeriod() && !isDead()) {
            target.gotHitFromEnemy(this);
        }
    }

    @Override
    public boolean move() {
        if(!isDead()) {
            findClosestEnemy();
            if (getDistance(target.getShape(), shape) - target.getShape().getRadius() < FOV) {
                if (getDistance(target.getShape(), shape) - target.getShape().getRadius() <= attackRange) {
                    return true;
                } else {
//                    collisionWithBlock();
                    if(!collisionWithSoldier((ArrayList<Soldier>) enemySoldiers.clone()) &&
                        !collisionWithSoldier((ArrayList<Soldier>) friendlySoldiers.clone())) {
                        moveSoldier(nextPoint(getAngle()));
                    }
                    resetPeriodCounter();
                }
            }
        }
        return false;
    }

    private void moveSoldier(double x, double y){
        changePos(x, y);
    }

    public void changePos(double x, double y) {
        super.setPos(x, y);
        friendMarker.setTranslateX(x);
        friendMarker.setTranslateY(y);
        marker.setTranslateX(x);
        marker.setTranslateY(y);
    }

    private void moveSoldier(Point2D pos){
        moveSoldier(pos.getX(), pos.getY());
    }

    private void findClosestEnemy(){
        if(!enemySoldiers.isEmpty()) {
            for (EnemySoldier i : enemySoldiers) {
                if((getDistance(i.getShape(), shape) < getDistance(target.getShape(), shape) && !i.isDead()) || target.isDead()){
                    target = i;
                }
                setAngle(getAngle(new Point2D(target.getShape().getTranslateX(), target.getShape().getTranslateY())));
            }
        }
    }

    public void setMarkerVisible(boolean visible) {
        marker.setVisible(visible);
    }

    public void setEnemySoldiers(ArrayList<EnemySoldier> enemySoldiers) {
        this.enemySoldiers = enemySoldiers;
    }

    public void setFriendlySoldiers(ArrayList<FriendlySoldier> friendlySoldiers) {
        this.friendlySoldiers = friendlySoldiers;
    }

    private void getBackToFirstPos(){

    }

    public void gotHit(double damage){
//        decreaseHealth(damage);
    }
}
