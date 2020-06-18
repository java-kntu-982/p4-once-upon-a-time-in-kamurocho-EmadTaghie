package ir.ac.kntu;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class EnemySoldier extends Soldier{
    private Line[] structureLine;
    ArrayList<EnemySoldier> friendSoldiers;
    private Structure targetStruct;
    private FriendlySoldier enemy;
    private Structure[] structures;
    private ArrayList<FriendlySoldier> enemySoldiers;

    private final int searchStep = 10;
    private final int kDis = 6 * searchStep;

    public EnemySoldier(int damage, double health, int attackRange, int speed, Color color, String name, ArrayList<FriendlySoldier> enemySoldiers,
                        Structure[] structures, Point2D pos, ArrayList<EnemySoldier> friendSoldiers, int dps, int id , Block block) {
        super(damage, health, attackRange, speed, color, name, pos, dps, id, block);
        enemy = null;
        structureLine = new Line[2];
        this.friendSoldiers = friendSoldiers;
        this.structures = structures;
        this.enemySoldiers = enemySoldiers;

        for (int i = 0; i < 2; i++) {
            structureLine[i] = new Line(structures[i].getShape().getTranslateX() - getAttackRange(),
                    structures[i].getShape().getTranslateY(),
                    structures[i].getShape().getTranslateX() - getAttackRange(),
                    structures[i].getShape().getTranslateY() + structures[i].getShape().getHeight());
            structureLine[i].setFill(Color.RED);
        }
        target = new Point2D(structureLine[0].getStartX(), structureLine[0].getStartY());
        targetStruct = structures[0];
        findClosestStructure();
    }

    private void findClosestStructure(){
        target = new Point2D(structureLine[0].getStartX(), structureLine[0].getStartY());
        for(int i = 0; i < structureLine.length; i++){
            for(int j = 0; j < getDistance(structureLine[i].getStartX(), structureLine[i].getStartY(),
                    structureLine[i].getEndX(), structureLine[i].getEndY())/searchStep + 1; j++){
                if(getDistance(structureLine[i].getStartX(), structureLine[i].getStartY() + j * searchStep,
                        shape.getTranslateX(), shape.getTranslateY())
                        < getDistance(target, new Point2D(shape.getTranslateX(), shape.getTranslateY()))){
                    setTarget(new Point2D(structureLine[i].getStartX(), structureLine[i].getStartY() + j * searchStep));
                    targetStruct = structures[i];
                }
            }
        }
        setAngle(getAngle(target));
    }

    public boolean move(){
        if(!isDead()) {
            if (enemy == null) {
                findClosestStructure();
                if (getDistance(target.getX(), target.getY(), shape.getTranslateX(), shape.getTranslateY()) > 2) {
                    setIsAttacking(false);
                    if(!collisionWithSoldier((ArrayList<Soldier>)friendSoldiers.clone()) &&
                            !collisionWithSoldier((ArrayList<Soldier>) enemySoldiers.clone())) {
                        setPos(nextPoint(getAngle()));
                    }
                } else {
                    setIsAttacking(true);
                    return true;
                }
            } else {
                if (getDistance(getTarget(), getPos()) > attackRange) {
                    if (enemy.isDead()){
                        enemy = null;
                        setIsAttacking(false);
                        return false;
                    }
                    setIsAttacking(false);
                    setAngle(getAngle(target));
//                    collisionWithBlock();
                    if(!collisionWithSoldier((ArrayList<Soldier>) friendSoldiers.clone()))
                    {
                        setPos(nextPoint(getAngle()));
                    }
                } else {
                    setIsAttacking(true);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void setPos(Point2D pos) {
        super.setPos(pos);
//        setAngle(getAngle(target));
    }

    @Override
    public void attack() {
        if(actionInPeriod()) {
            if (enemy == null && targetStruct.getDurability() > 0) {
                targetStruct.gotHit(damage);
            }
            if (enemy != null) {
                enemy.gotHit(damage);
                if (enemy.isDead()) {
                    enemy = null;
                    resetPeriodCounter();
                    findClosestStructure();
                }
            }
        }
    }

    public void gotHitFromEnemy(FriendlySoldier soldier){
        decreaseHealth(soldier.damage);
        if(enemy == null  && !soldier.isDead()){
            enemy = soldier;
            target = enemy.getPos();
            setAngle(getAngle(target));
        }
        for(EnemySoldier i: friendSoldiers){
            if(getDistance(shape, i.getShape()) < kDis && !i.equals(this)){
                i.gotHit(soldier);
            }
        }
    }

    public void gotHit(FriendlySoldier soldier){
//        decreaseHealth(soldier.damage);
        if(enemy == null  && !soldier.isDead()){
            enemy = soldier;
            target = enemy.getPos();
            setAngle(getAngle(target));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EnemySoldier that = (EnemySoldier) o;
        return searchStep == that.searchStep &&
                kDis == that.kDis &&
                targetStruct.equals(that.targetStruct) &&
                enemy.equals(that.enemy) &&
                Arrays.equals(structures, that.structures);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), targetStruct, enemy, searchStep, kDis);
        result = 31 * result + Arrays.hashCode(structures);
        return result;
    }
}
