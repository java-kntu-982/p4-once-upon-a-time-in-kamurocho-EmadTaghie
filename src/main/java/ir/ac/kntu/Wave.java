package ir.ac.kntu;
import javafx.geometry.Point2D;
import java.util.ArrayList;

public class Wave {
    private Block block;
    private int dsp;
    private ArrayList<FriendlySoldier> friendlySoldiers;
    private Structure[] structures;
    private double width;
    private double height;

    private final String[] enemiesNames = {"redEnemy", "yellowEnemy", "grayEnemy", "bossLVL1", "bossLVL2"};
    private final int solRadius = 10;

    public Wave(Structure[] structures, ArrayList<FriendlySoldier> friendlySoldiers, int dps, Block block, double width, double height){
        setStructures(structures);
        setFriendlySoldiers(friendlySoldiers);
        setDsp(dps);
        setBlock(block);
        setWidth(width);
        setHeight(height);
    }

    private void setBlock(Block block) {
        this.block = block;
    }

    private void setDsp(int dsp) {
        this.dsp = dsp;
    }

    private void setFriendlySoldiers(ArrayList<FriendlySoldier> friendlySoldiers) {
        this.friendlySoldiers = friendlySoldiers;
    }

    private void setStructures(Structure[] structures) {
        this.structures = structures;
    }

    public Block getBlock() {
        return block;
    }

    public int getDsp() {
        return dsp;
    }

    public ArrayList<FriendlySoldier> getFriendlySoldiers() {
        return friendlySoldiers;
    }

    public Structure[] getStructures() {
        return structures;
    }

    public String[] getEnemiesNames() {
        return enemiesNames;
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

    public ArrayList<EnemySoldier> createWave(int redNum, int yellowNum, int grayNum, int boss1Num, int boss2Num){
        int id = 0;
        int totalNum = redNum + yellowNum + grayNum + boss1Num + boss2Num;
        int rows = sign(redNum) + sign(yellowNum) + sign(grayNum)  +sign(boss1Num) + sign(boss2Num);
        int cols = getCols(grayNum, rows);
        ArrayList<EnemySoldier> enemySoldiers = new ArrayList<>();
        double x = 0, y = 0;
        for(int i= 0; i < grayNum; i++){
            enemySoldiers.add(new GrayEnemy(getStructures(), new Point2D(width/4 + x, solRadius + y),
                    getFriendlySoldiers(), enemySoldiers, getDsp(), id, getBlock()));
            y += 2 * solRadius;
            if((i + 1)%(grayNum/cols) == 0){
                x -= 2 * solRadius;
                y -= (int)(2 * solRadius * grayNum/cols);
            }
        }

        x = 0;
        y = ((double)(sign(grayNum) + sign(redNum) - 1) * getHeight())/rows + solRadius;
        cols = getCols(redNum, rows);
        for(int i= 0; i < redNum; i++){
            enemySoldiers.add(new RedEnemy(getStructures(), new Point2D(width/4 + x, y),
                    getFriendlySoldiers(), enemySoldiers, getDsp(), id, getBlock()));
            y += 2 * solRadius;
            if((i + 1)%(redNum/cols) == 0){
                x -= 2 * solRadius;
                y -= (int)(2 * solRadius * redNum/cols);
            }
        }

        x = 0;
        y = ((rows - 1) * getHeight())/rows + solRadius;
        cols = getCols(yellowNum, rows);
        for(int i= 0; i < yellowNum; i++){
            enemySoldiers.add(new YellowEnemy(getStructures(), new Point2D(width/4 + x, y),
                    getFriendlySoldiers(), enemySoldiers, getDsp(), id, getBlock()));
            y += 2 * solRadius;
            if((i + 1)%(yellowNum/cols) == 0){
                x -= 2 * solRadius;
                y -= (int)(2 * solRadius * yellowNum/cols);
            }
        }

        if (sign(boss1Num) == 1){
            enemySoldiers.add(new BossLVL1(getStructures(), new Point2D(width/5, height/2),
                    getFriendlySoldiers(), enemySoldiers, getDsp(), id, getBlock()));
        }

        if (sign(boss2Num) == 1){
            enemySoldiers.add(new BossLvL2(getStructures(), new Point2D(width/5, height/2),
                    getFriendlySoldiers(), enemySoldiers, getDsp(), id, getBlock()));
        }

        return enemySoldiers;
    }

    private int getCols(int num, int rows){
        int cols = 1;
        while(solRadius * 2 * num/cols > height/rows){
            cols+=1;
        }
        return cols;
    }

    private int sign(int num){
        return num > 0 ? 1 : 0;
    }
}
