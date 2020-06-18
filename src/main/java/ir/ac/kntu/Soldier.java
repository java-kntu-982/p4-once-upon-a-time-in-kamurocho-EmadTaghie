package ir.ac.kntu;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Soldier{
    Group root;
    int damage;
    double health;
    int attackRange;
    double speed;
    Color color;
    Circle shape;
    String name;
    Point2D target;
    private int periodCounter;
    private int dps;
    private double angle;
    private boolean isAttacking;
    private double bufferSpeed;
    private int id;
    private Block block;

    private final ProgressIndicator healthInd;
    private final double maxHealth;
    private final int baseAttackRange = 20;
    private final int baseSpeed = 1;

    public Soldier(int damage, double health, int attackRange, int speed, Color color, String name, Point2D pos, int dsp, int id, Block block){
        this.damage = damage;
        this.health = health;
        this.attackRange = attackRange * baseAttackRange;
        this.speed = speed * baseSpeed;
        this.color = color;
        shape = new Circle(10, this.color);
        this.name = name;
        this.dps = dsp;
        this.angle = 0;
        this.id = id;
        this.bufferSpeed = this.speed;
        this.block = block;
        maxHealth = health;
        healthInd = new ProgressBar(getHealth()/maxHealth);
        healthInd.setPrefSize(50, 10);
        setPos(pos.getX(), pos.getY());
        target = new Point2D(shape.getTranslateX(), shape.getTranslateY());
        root = new Group();
        root.getChildren().addAll(shape, healthInd);
        periodCounter = dsp;
    }

    public int getDamage() {
        return damage;
    }

    public double getHealth() {
        return health > 0 ? health : 0;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public Circle getShape() {
        return shape;
    }

    protected double getSpeed(){
        return speed/5;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    protected void setIsAttacking(boolean isAttacking){
        this.isAttacking = isAttacking;
    }

    public void setPos(double x, double y) {
        shape.setTranslateX(x);
        shape.setTranslateY(y);
        healthInd.setTranslateX(x - healthInd.getPrefWidth()/2);
        healthInd.setTranslateY(y - 2 * shape.getRadius());
    }

    public void setPos(Point2D pos) {
        setPos(pos.getX(), pos.getY());
    }

    public void setDps(int dps) {
        this.dps = dps;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTarget(Point2D target) {
        this.target = target;
    }

    public Point2D getTarget() {
        return target;
    }

    public double getDistance(Point2D p1, Point2D p2){
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    public double getDistance(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public double getDistance(Circle c1, Circle c2){
        return getDistance(c1.getTranslateX(), c1.getTranslateY(), c2.getTranslateX(), c2.getTranslateY());
    }

    protected Group getRoot() {
        return root;
    }

    protected Point2D getPos(){
        return new Point2D(shape.getTranslateX(), shape.getTranslateY());
    }

    public double
    getAngle(Point2D target){
        return getAngle(target, new Point2D(shape.getTranslateX(), shape.getTranslateY()));
    }

    public double getAngle(Point2D target1, Point2D target2){
        if(Math.abs(target1.getX() - target2.getX()) <= 2 && target1.getY() != target2.getY()){
            return target1.getY() > target2.getY() ? Math.toRadians(90) : Math.toRadians(-90);
        }
        return (target1.getX() - target2.getX() < 0 ? Math.toRadians(180) : 0 +
                Math.atan((target1.getY() - target2.getY())/(target1.getX() - target2.getX())));
    }

    protected Point2D nextPoint(double angle){
        if(isAttacking()){
            return getPos();
        }
        return new Point2D(getPos().getX() + getSpeed() * Math.cos(angle),
                getPos().getY() + getSpeed() * Math.sin(angle));
    }

    public double getAngle() {
        return angle;
    }

    protected void setAngle(double angle) {
        this.angle = angle;
    }

    public ProgressIndicator getHealthInd() {
        return healthInd;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setHealth(double health){
        this.health = health;
    }

    public boolean isDead(){
        if (health <= 0){
            return true;
        }
        return false;
    }

    public void decreaseHealth(double damage){
        setHealth(getHealth() - damage/20);
        getHealthInd().setProgress(getHealth()/getMaxHealth());
        if(isDead()){
            setSpeed(0);
        }
    }

    protected boolean actionInPeriod(){
        if(periodCounter++ >= dps){
            setPeriodCounterToZero();
            return true;
        }
        return false;
    }

    private void setPeriodCounterToZero(){
        periodCounter = 0;
    }
    protected void resetPeriodCounter(){
        periodCounter = dps;
    }

    private void setSpeed(double speed) {
        this.speed = speed;
    }

    protected boolean checkSoldierColl(Soldier target, double angle){
        if(getDistance(nextPoint(angle), target.nextPoint(target.getAngle())) <
                (shape.getRadius() + target.getShape().getRadius())){
            if(getDistance(nextPoint(angle), target.nextPoint(target.getAngle())) >= getDistance(getPos(), target.getPos()) && !target.isAttacking()){
                return false;
            }
            return true;
        }
        return false;
    }

    protected double[] resolvedCollAngle(Soldier target){
        double angle = Math.toRadians(90);
        if(getDistance(getPos(), target.getPos()) < shape.getRadius() + target.getShape().getRadius() - 6){
            if(getDistance(target.getPos(), target.getTarget()) < getDistance(getPos(), getTarget())) {
                return new double[]{getAngle(target.getPos()) + Math.toRadians(180), getAngle(target.getPos()) + Math.toRadians(180)};
            }
        }
        return new double[]{getAngle(target.getPos()) + angle, getAngle(target.getPos()) + angle};
    }

    public int getId() {
        return id;
    }

    private ArrayList<Soldier> findNearest(ArrayList<Soldier> soldiers){
        ArrayList<Soldier> confSol = new ArrayList<>();
        for (Soldier i: soldiers){
            if(getDistance(nextPoint(getAngle()), i.nextPoint(i.getAngle())) < i.getShape().getRadius() + shape.getRadius() + 1 && !this.equals(i)){
                confSol.add(i);
            }
        }
        return confSol;
    }

    protected double resolveBlockColl(int blockIndex){
        if(getPos().getY() - block.getBlocks().get(blockIndex).getY() <
                block.getBlocks().get(blockIndex).getY() + block.getBlocks().get(blockIndex).getHeight() - getPos().getY()){
            return Math.toRadians(-90);
        }
        return Math.toRadians(90);
    }

    protected boolean collisionWithSoldier(ArrayList<Soldier> soldiers){
        ArrayList<Soldier> nearestSols = new ArrayList<>(findNearest(soldiers));
//        if(nearestSols.isEmpty()){
//            return false;
//        }
        int counterWhile = 0;
        int counterFor = 0;
        double resolvedAngle = getAngle();
        int blockIndex = block.isInBoundary(this, resolvedAngle);
        if(blockIndex != -1){
            resolvedAngle = resolveBlockColl(blockIndex);
            setAngle(resolvedAngle);
        }
        while(counterWhile < nearestSols.size() + 3) {
            for (Soldier i : nearestSols) {
                if(((checkSoldierColl(i, resolvedAngle) && getDistance(i.getPos(), i.getTarget()) < getDistance(getPos(), getTarget()))) || block.isInBoundary(this, resolvedAngle) != -1){
                    resolvedAngle = getDistance(nextPoint(resolvedCollAngle(i)[0]), target) <
                            getDistance(nextPoint(resolvedCollAngle(i)[1]), target) ? resolvedCollAngle(i)[0] : resolvedCollAngle(i)[1];
                    if(i.isAttacking() || i.isDead()){
                        counterFor++;
                    }
                    blockIndex = block.isInBoundary(this, resolvedAngle);
                    if(blockIndex != -1 && i.getPos().getY() > getPos().getY()){
                        resolvedAngle = resolveBlockColl(blockIndex);
                    }
                }
                else{
                    counterFor++;
                }
            }
            if(counterFor == nearestSols.size()){
                setAngle(resolvedAngle);
                return false;
            }
            counterWhile++;
        }
        if(!nearestSols.isEmpty()) {
            return true;
        }
        return false;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public abstract void attack();
    public abstract boolean move();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Soldier soldier = (Soldier) o;
        return damage == soldier.damage &&
                Double.compare(soldier.health, health) == 0 &&
                attackRange == soldier.attackRange &&
                Double.compare(soldier.speed, speed) == 0 &&
                dps == soldier.dps &&
                Double.compare(soldier.angle, angle) == 0 &&
                isAttacking == soldier.isAttacking &&
                id == soldier.id &&
                Double.compare(soldier.maxHealth, maxHealth) == 0 &&
                root.equals(soldier.root) &&
                color.equals(soldier.color) &&
                shape.equals(soldier.shape) &&
                name.equals(soldier.name) &&
                target.equals(soldier.target) &&
                healthInd.equals(soldier.healthInd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root, damage, health, attackRange, speed, color, shape, name, target, dps, angle, isAttacking, id, healthInd, maxHealth);
    }
}

