package ir.ac.kntu;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private Scene scene;
    private Group[] root;
    private ArrayList<Label> labels;
    private ArrayList<EventHandler> events;
    private ArrayList<FriendlySoldier> friendlySoldiers;
    private Line underLine;

    private final Font font = new Font(20);
    private final int stepSize = 40;
    private final int x = 50;
    private final int y = 80;
    private final int lineSize = 200;
    private final Color startUpColor = Color.rgb(40, 40, 63);
    private final Color playColor = Color.WHITE;

    public Menu(){
        root = new Group[3];
        for(int i = 0; i < root.length; i++){
            root[i] = new Group();
        }
        friendlySoldiers = new ArrayList<>();
        friendlySoldiers.add(new GoroMajima(new Point2D(0,0), new ArrayList<>(), new ArrayList<>(), 0, 0, new Block()));
        root[0] = new Group();
        scene = new Scene(root[0], 800, 600, false, SceneAntialiasing.BALANCED);
        scene.setFill(startUpColor);
        labels = new ArrayList<>();
        underLine = new Line(x, 0, x + lineSize, 0);
        underLine.setStroke(Color.rgb(0,255, 255));
        underLine.setVisible(false);
        root[0].getChildren().add(underLine);
        events = new ArrayList<>();
    }

    private void startUp(){
        addLabel("Play");
        addLabel("Make table");
        events();
        root[0].getChildren().addAll(labels);
    }

    public Scene start(){
        startUp();
        return scene;
    }

    private void addLabel(String text){
        labels.add(new Label(text));
        int pointer = labels.size() - 1;
        labels.get(pointer).setFont(font);
        labels.get(pointer).setTranslateX(x);
        labels.get(pointer).setTranslateY(y + pointer * stepSize);
        labels.get(pointer).setTextFill(Color.WHITE);
    }

    private void setUnderLine(Label label){
        underLine.setTranslateY(label.getTranslateY() + label.getHeight());
        underLine.setVisible(true);
    }

    private void events(){
        for(Label label: labels){
            label.setOnMouseEntered(event -> setUnderLine(label));
            scene.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ESCAPE)){
                    getBackToMenu();
                }
            });
            label.setOnMouseClicked(event -> {
                switch (label.getText()){
                    case "Play":
                        scene.setRoot(root[1]);
                        scene.setFill(playColor);
                        Play play = new Play(root[1], friendlySoldiers, scene.getWidth(), scene.getHeight());

                        scene.setOnMouseClicked(event1 -> {
                            if(!play.getStart()) {
                                play.setMouseTarget(event1.getX(), event1.getY());
                            }
                        });

                        scene.setOnKeyPressed(event1 -> {
                            switch (event1.getCode()){
                                case SPACE:
                                    play.start(true);
                                    break;
                                case Q:
                                    play.changeSoldierMarker(true);
                                    break;
                                case E:
                                    play.changeSoldierMarker(false);
                                    break;
                            }
                        });
                        break;
                    case "Make table":
                        scene.setRoot(root[2]);
                        scene.setFill(playColor);
                        TableMaker tableMaker = new TableMaker(root[2], friendlySoldiers, scene.getWidth(), scene.getHeight(), this);
                        break;
                    default:
                        break;
                }
            });
            label.setOnMouseExited(event -> removeUnderLine());
        }
    }

    private void removeUnderLine(){
        underLine.setVisible(false);
    }

    public void getBackToMenu(){
        this.scene.setRoot(root[0]);
        this.scene.setFill(startUpColor);
    }
}
