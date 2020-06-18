package ir.ac.kntu;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class TableMaker {
    private Group root;
    private List<FriendlySoldier> friendlySoldiers;
    private List<FriendlySoldier> chosenSols;
    private double width;
    private double height;
    private ObservableList<String> soldiersName;
    private ObservableList<String> chosenSolName;
    private Circle selectedSol;
    private Menu menu;

    public TableMaker(Group root, List<FriendlySoldier> soldiers, double width, double height, Menu menu){
        setRoot(root);
        setWidth(width);
        setHeight(height);
        this.menu = menu;
        friendlySoldiers = createFriendlySol();
        chosenSols = new ArrayList<>();
        if (soldiers.isEmpty()) {
            chosenSols.add(friendlySoldiers.get(0));
        }
        else {
            chosenSols = soldiers;
        }
        this.selectedSol = new Circle(chosenSols.get(0).shape.getRadius() * 3, chosenSols.get(0).getColor());
        soldiersName = FXCollections.observableArrayList();
        chosenSolName = FXCollections.observableArrayList();
        for(FriendlySoldier friendlySoldier: friendlySoldiers){
            soldiersName.add(friendlySoldier.getName());
        }

        for(FriendlySoldier friendlySoldier: chosenSols){
            chosenSolName.add(friendlySoldier.getName());
        }
        makeRoot();
    }

    private void setRoot(Group root){
        this.root = root;
    }

    private void setWidth(double width){
        this.width = width;
    }

    private void setHeight(double height){
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    private void makeRoot(){
        final String[] addItem = {soldiersName.get(0)};
        final String[] rmItem = {soldiersName.get(0)};
        Label selectedSolName = new Label();
//        Label selectedSolName = new Label();
//        Label selectedSolName = new Label();
        ListView selectView = new ListView(soldiersName);
        Button addButton = makeButton("add", 50, 50 + getListHeight() + 10);
        Button rmButton = makeButton("remove", 250, 50 + getListHeight() + 10);
        Button saveExit = makeButton("Save & Exit", 150, 50 + getListHeight() + 10);

        selectedSolName.setTranslateX(600);
        selectedSolName.setTranslateY(50);
        selectedSolName.setText(soldiersName.get(0));

        selectedSol.setTranslateX(635);
        selectedSol.setTranslateY(100);

        selectView.setMaxWidth(200);
        selectView.setTranslateX(50);
        selectView.setTranslateY(50);
        selectView.setPrefHeight(getListHeight());
        selectView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                addItem[0] = (String) newValue;
                selectedSolName.setText((String) newValue);
                setSelectedSol(findSol(addItem[0], friendlySoldiers).getColor());
            }
        });

        ListView selectedView = new ListView(chosenSolName);
        selectedView.setMaxWidth(200);
        selectedView.setTranslateX(50 + 200);
        selectedView.setTranslateY(50);
        selectedView.setPrefHeight(getListHeight());
        selectedView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(!chosenSols.isEmpty()) {
                    rmItem[0] = (String) newValue;
                    selectedSolName.setText((String) newValue);
                    setSelectedSol(findSol(rmItem[0], friendlySoldiers).getColor());
                }
            }
        });

        addButton.setOnMouseClicked(event -> {
            if(chosenSols.size() < 10 && !chosenSolName.contains(addItem[0])) {
                chosenSolName.add(addItem[0]);
                chosenSols.add(findSol(addItem[0], friendlySoldiers));
            }
        });

        rmButton.setOnMouseClicked(event -> {
            if(chosenSols.size() > 1){
                chosenSols.remove(findSol(rmItem[0], chosenSols));
                chosenSolName.remove(rmItem[0]);
            }
        });

        saveExit.setOnMouseClicked(event -> {
            menu.getBackToMenu();
            selectedSolName.setText("");
        });

        root.getChildren().addAll(selectView, selectedView, selectedSolName, addButton, rmButton, selectedSol, saveExit);
    }

    private List<FriendlySoldier> createFriendlySol(){
        List<FriendlySoldier>  friendlySoldiers= new ArrayList<>();
        friendlySoldiers.add(new GoroMajima(new Point2D(0,0), new ArrayList<>(), new ArrayList<>(), 0, 0, new Block()));
        friendlySoldiers.add(new DaigoDojima(new Point2D(0,0), new ArrayList<>(), new ArrayList<>(), 0, 0, new Block()));
        friendlySoldiers.add(new KaoruSayama(new Point2D(0,0), new ArrayList<>(), new ArrayList<>(), 0, 0, new Block()));
        friendlySoldiers.add(new TaigaSaejima(new Point2D(0,0), new ArrayList<>(), new ArrayList<>(), 0, 0, new Block()));
        friendlySoldiers.add(new SoheiDojima(new Point2D(0,0), new ArrayList<>(), new ArrayList<>(), 0, 0, new Block()));
        friendlySoldiers.add(new ShintaroKazama(new Point2D(0,0), new ArrayList<>(), new ArrayList<>(), 0, 0, new Block()));
        friendlySoldiers.add(new RyujiGoda(new Point2D(0,0), new ArrayList<>(), new ArrayList<>(), 0, 0, new Block()));
        friendlySoldiers.add(new OsamuKashiwagi(new Point2D(0,0), new ArrayList<>(), new ArrayList<>(), 0, 0, new Block()));
        friendlySoldiers.add(new MakotoDate(new Point2D(0,0), new ArrayList<>(), new ArrayList<>(), 0, 0, new Block()));
        friendlySoldiers.add(new FutoshiShimano(new Point2D(0,0), new ArrayList<>(), new ArrayList<>(), 0, 0, new Block()));
        friendlySoldiers.add(new RyoTakashima(new Point2D(0,0), new ArrayList<>(), new ArrayList<>(), 0, 0, new Block()));
        friendlySoldiers.add(new JiroKawara(new Point2D(0,0), new ArrayList<>(), new ArrayList<>(), 0, 0, new Block()));
        return friendlySoldiers;
    }

    private double getListHeight(){
        return soldiersName.size() * 23.5;
    }

    private Button makeButton(String text, double x, double y){
        Button button = new Button(text);
        button.setTranslateX(x);
        button.setTranslateY(y);
        return button;
    }

    private FriendlySoldier findSol(String name, List<FriendlySoldier> soldiers){
        for(FriendlySoldier soldier: soldiers){
            if(soldier.getName().equals(name)){
                return soldier;
            }
        }
        return null;
    }

    private void setSelectedSol(Color color){
        this.selectedSol.setFill(color);
    }


}
