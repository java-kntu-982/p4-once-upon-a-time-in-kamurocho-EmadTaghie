package ir.ac.kntu;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Group;

import java.util.*;

public class Play {
    private final int dps = 20;
    private boolean start;
    private Group root;
    private ArrayList<FriendlySoldier> friendlySoldiers;
    private int markerCounter;
    private Block blocks;
    private double width;
    private double height;
    private LvL1 lvl1;

    public Play(Group root, ArrayList<FriendlySoldier> soldiers, double width, double height){
        this.root = root;
        friendlySoldiers = soldiers;
        this.start = false;
        markerCounter = 0;
        blocks = new Block();
        this.width = width;
        this.height = height;
        lvl1 = new LvL1(blocks, friendlySoldiers, dps, getWidth(), getHeight());
        start();
    }

    public void start() {
        root.getChildren().setAll(lvl1.getRoot());
        lvl1.getFriendlySoldiers().get(markerCounter).setMarkerVisible(true);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (start) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (Soldier enemySoldier : lvl1.getEnemySoldiers()) {
                                if (enemySoldier.move()) {
                                    enemySoldier.attack();
                                }
                            }
                        }
                    });

                    for (Soldier friendlySoldier : lvl1.getFriendlySoldiers()) {
                        if (friendlySoldier.move()) {
                            friendlySoldier.attack();
                        }
                    }
                    if(lvl1.getStatus() == 1){
                        start(false);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                lvl1.nextWave();
                            }
                        });
                    }
                    else if(lvl1.getStatus() == -1){
                        start(false);
                    }
                    else if(lvl1.getStatus() == 2){
                        start(false);
                    }
                }
            }
        }, 0, 5);
    }
    public void start(boolean start) {
        this.start = start;
        friendlySoldiers.get(markerCounter).setMarkerVisible(!start);
    }


    public boolean getStart() {
        return start;
    }

    public void setMouseTarget(double x, double y) {
        if(root.getScene() != null) {
            if (x > root.getScene().getWidth() / 2 && !checkCollOnMouseClick(x, y)) {
                friendlySoldiers.get(markerCounter).changePos(x, y);
            }
        }
    }

    public void changeSoldierMarker(boolean dir){
        friendlySoldiers.get(markerCounter).setMarkerVisible(false);
        if(dir){
            markerCounter = (++markerCounter)%friendlySoldiers.size();
        }
        else {
            markerCounter = (--markerCounter + friendlySoldiers.size())%friendlySoldiers.size();
        }
        friendlySoldiers.get(markerCounter).setMarkerVisible(true);
    }

    private boolean checkCollOnMouseClick(double x, double y){
        for(Soldier i: friendlySoldiers){
            if(i.getDistance(i.getPos(), new Point2D(x, y)) <
                    friendlySoldiers.get(markerCounter).getShape().getRadius() + i.getShape().getRadius()){
                return true;
            }
        }
        return false;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
