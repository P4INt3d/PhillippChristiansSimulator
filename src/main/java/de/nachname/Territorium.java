package de.nachname;

public class Territorium {

    private static int rows;
    private static int cols;
    private int vDirection; //1 = Osten, 2 = Süden, 3 = Westen, 4 = Norden
    private int xPosition;
    private int yPosition;
    private int totalAmount;
    private int[][] grid; // -1 bedeutet Maus, 0-9 ist die Anzahl der Körner



    public Territorium(int h, int w){
        rows = h;
        cols = w;
        setGrid(rows, cols);
        xPosition = 0;
        yPosition = 0;
        vDirection = 1;
    }


    public void setGrid(int h, int w) {
        this.grid = new int[h][w];
        for (int i = 0; i < h; i++){
            for(int j= 0; j < w; j++)
                grid[i][j] = 0;
        }

    }

    public int[][] getGrid() {
        return grid;
    }

    public static int getRows() {
        return rows;
    }

    public static int getCols() {
        return cols;
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

    public int getTotalAmount(){
        return totalAmount;
    }

    public void setTotalAmount(int amount){
        totalAmount = amount;
    }

    public void setMouse(int row, int col){
        grid[row][col] = -1;
    }

    public boolean hasMouse(int row, int col){
        return grid[row][col] == -1;
    }

}
