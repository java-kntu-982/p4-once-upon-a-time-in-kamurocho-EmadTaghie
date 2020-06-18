package ir.ac.kntu;

import javafx.scene.Group;

import java.util.ArrayList;

public class LvL1 {
    private Structure[] structures;
    private Block block;
    private ArrayList<FriendlySoldier> friendlySoldiers;
    private ArrayList<EnemySoldier> enemySoldiers;
    private ArrayList<EnemySoldier>[] waves;
    private int dps;
    private double width;
    private double height;
    private Wave wave;
    private int waveCounter;
    private Group root;
    private Group enemyRoot;
    private Group[] enemyWaves;

    public  LvL1(Block block, ArrayList<FriendlySoldier> friendlySoldiers, int dps, double width, double height){
        setWidth(width);
        setHeight(height);
        setStructures(new Structure[]{new Container(), new Truck()});
        getStructures()[0].setPos(650, 50);
        getStructures()[1].setPos(650, getHeight() - getStructures()[1].getShape().getHeight() - 50);
        setBlock(block);
        setDps(dps);
        setFriendlySoldiers(friendlySoldiers);
        root = new Group();
        enemyRoot = new Group();
        enemyWaves = new Group[6];
        for(int i = 0; i < enemyWaves.length; i++){
            enemyWaves[i] = new Group();
        }
        waveCounter = 0;
        wave = new Wave(getStructures(),getFriendlySoldiers(), getDps(), getBlock(), getWidth(), getHeight());
        waves = new ArrayList[6];
        for(int i = 0; i < waves.length; i++){
            waves[i] = new ArrayList<>();
        }
        waves[0] = wave.createWave(20, 0, 20, 0, 0);
        waves[1] = wave.createWave(30, 0, 20, 0, 0);
        waves[2] = wave.createWave(20, 0, 30, 0, 0);
        waves[3] = wave.createWave(15, 10, 15, 0, 0);
        waves[4] = wave.createWave(15, 15, 15, 0, 0);
        waves[5] = wave.createWave(0, 0, 0, 1, 0);
        block.creatBlocks(getWidth(), getHeight());
        makeRoot(waveCounter);
    }

    public Structure[] getStructures() {
        return structures;
    }

    public void setStructures(Structure[] structures) {
        this.structures = structures;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public ArrayList<FriendlySoldier> getFriendlySoldiers() {
        return friendlySoldiers;
    }

    public void setFriendlySoldiers(ArrayList<FriendlySoldier> friendlySoldiers) {
        this.friendlySoldiers = friendlySoldiers;
    }

    public ArrayList<EnemySoldier> getEnemySoldiers() {
        return enemySoldiers;
    }

    public void setEnemySoldiers(ArrayList<EnemySoldier> enemySoldiers) {
        this.enemySoldiers = enemySoldiers;
    }

    public int getDps() {
        return dps;
    }

    public void setDps(int dps) {
        this.dps = dps;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    private void makeRoot(int waveCounter){
        setEnemySoldiers(waves[waveCounter]);
        configFriendlySoldiers();
        getRoot().getChildren().setAll(getStructures()[0].getRoot(), getStructures()[1].getRoot(), getBlock().getRoot());
        for(int j = 0; j < enemyWaves.length; j++) {
            for (Soldier i : waves[j]) {
                enemyWaves[j].getChildren().add(i.getRoot());
            }
        }
        enemyRoot.getChildren().setAll(enemyWaves[waveCounter]);
        getRoot().getChildren().add(enemyRoot);

        for(Soldier i: getFriendlySoldiers()){
            getRoot().getChildren().add(i.getRoot());
        }
    }
    public Group getRoot() {
        return root;
    }

    private void configFriendlySoldiers(){
        double spacing = getHeight()/(getFriendlySoldiers().size() + 1);
        for(int i = 0; i < getFriendlySoldiers().size(); i++){
            friendlySoldiers.get(i).changePos(getWidth() - getWidth()/4, (1 + i) * spacing);
            friendlySoldiers.get(i).setEnemySoldiers(getEnemySoldiers());
            friendlySoldiers.get(i).setFriendlySoldiers(getFriendlySoldiers());
            friendlySoldiers.get(i).setBlock(getBlock());
            friendlySoldiers.get(i).setDps(getDps());
            friendlySoldiers.get(i).setId(i);
            friendlySoldiers.get(i).setTarget(getEnemySoldiers().get(0));
        }
    }

    public int getStatus(){
        if(deadEnemies() == getEnemySoldiers().size()){
            if(waveCounter < 6){
                return 1;
            }
            else return 2;
        }

        if(getStructures()[0].getDurability() <= 0 || getStructures()[1].getDurability() <= 0){
            return -1;
        }

        return 0;
    }

    public void nextWave(){
        waveCounter++;
        setEnemySoldiers(waves[waveCounter]);
        enemyRoot.getChildren().setAll(enemyWaves[waveCounter]);
        configFriendlySoldiers();
    }

    private int deadEnemies(){
        int counter = 0;
        for(Soldier i: getEnemySoldiers()){
            if(i.getHealth() <= 0){
                counter++;
            }
        }
        return counter;
    }

//    private void makeFriendlyGroup(){
//        for(Soldier i: getFriendlySoldiers()){
//            getFriendly().getChildren().add(i.getRoot());
//        }
//    }
//
//    public Group getFriendly() {
//        return friendly;
//    }
//
//    private void makeEnemyGroup(){
//        for(Soldier i: getEnemySoldiers()){
//            getEnemy().getChildren().add(i.getRoot());
//        }
//    }
//
//    public Group getEnemy() {
//        return enemy;
//    }
//
//    private void removeEnemyGroup(){
//        getEnemy().getChildren().re
//    }
}
