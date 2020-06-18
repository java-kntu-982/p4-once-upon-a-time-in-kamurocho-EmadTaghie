package ir.ac.kntu;

import javafx.scene.Group;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Structure {
    String name;
    int durability;
    Rectangle shape;
    Color color;
    Group root;
    ProgressBar health;

    private final double maxHealth;

    public Structure(String name, int durability, Color color){
        this.name = name;
        this.durability = durability;
        maxHealth = durability;
        this.color = color;
        shape = new Rectangle(50, 150, color);
        root = new Group();
        health = new ProgressBar(1);
        health.setRotate(90);
        root.getChildren().addAll(shape, health);
    }

    public void gotHit(int damage){
        setDurability(getDurability() - damage);
        health.setProgress(getDurability()/maxHealth);
    }

    public String getName() {
        return name;
    }

    public int getDurability() {
        return durability > 0 ? durability : 0;
    }

    public Group getRoot() {
        return root;
    }

    public void setPos(double x, double y){
        shape.setTranslateX(x);
        shape.setTranslateY(y);
        health.setTranslateX(x + 10);
        health.setTranslateY(y + shape.getHeight()/2);
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public Rectangle getShape() {
        return shape;
    }
}
