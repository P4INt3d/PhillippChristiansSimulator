package de.nachname;

public class Territorium {

    private static int rows;
    private static int cols;
    private static int vDirection; //1 = Osten, 2 = Süden, 3 = Westen, 4 = Norden
    private static int xPosition;
    private static int yPosition;
    private static int totalAmount;
    private int[][] grid;



    public Territorium(int h, int w){
        rows = h;
        cols = w;
        setGrid(rows, cols);
        xPosition = 0;
        yPosition = 0;
        vDirection = 1;
    }

    public void takeP(){
        if(grid[xPosition][yPosition] > 0) {
            grid[xPosition][yPosition]--;
            totalAmount++;
        }
        //todo: excpetion
    }

    public void dropP(){
        if(grid[xPosition][yPosition] < 9 && totalAmount > 0) {
            grid[xPosition][yPosition]++;
            totalAmount--;
        }
        //todo: hier auch
    }



    public void setGrid(int h, int w) {
        this.grid = new int[h][w];
    }

    public int[][] getGrid() {
        return grid;
    }

    public int getP(int row, int col){
        return grid[row][col];
    }

    public void setP(int row, int col, int amount){
        grid[row][col] = amount;
    }

    public int getvDirection() {
        return vDirection;
    }

    public void setvDirection(int vDirection) {
        vDirection = vDirection;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        yPosition = yPosition;
    }

    public void setTotalAmount(int amount){
        totalAmount = amount;
    }

    public int getTotalAmount(){
        return totalAmount;
    }
}
