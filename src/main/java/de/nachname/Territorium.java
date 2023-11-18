package de.nachname;

public class Territorium {

    private int rows;
    private int cols;
    private int vDirection; //1 = Süden, 2 = Osten, 3 = Norden, 4 = Westen
    private int xPosition;
    private int yPosition;
    private int totalAmount;
    private int[][] grid; // -1 bedeutet Maus, 0-9 ist die Anzahl der Körner
    private Elefant actor;



    public Territorium(){
        rows = 8;
        cols = 12;
        setGrid(rows, cols);
        xPosition = 0;
        yPosition = 0;
        vDirection = 2;
        actor = new Elefant(this);

    }
    public Territorium(int h, int w){
        rows = h;
        cols = w;
        setGrid(rows, cols);
        xPosition = 0;
        yPosition = 0;
        vDirection = 2;
        actor = new Elefant(this);
    }


    public void setGrid(int h, int w) {
        this.grid = new int[h][w];
        for (int i = 0; i < h; i++){
            for(int j= 0; j < w; j++)
                grid[i][j] = 0;
        }

    }

    public int[][] getGrid() {
        int[][] tempGrid = grid;
        tempGrid[getxPosition()][getyPosition()] = -2;
        return tempGrid;

    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setCols(int cols) {
        this.cols = cols;
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
        this.vDirection = vDirection;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getTotalAmount(){
        return totalAmount;
    }

    public void setTotalAmount(int amount){
        totalAmount = amount;
    }

    public void setMouse(int row, int col){
        if(!hasMouse(row, col)){
            grid[row][col] = -1;
        } else {
            grid[row][col] = 0;
        }

    }

    public boolean hasMouse(int row, int col){
        return grid[row][col] == -1;
    }


    public void clear(int r, int c){
        grid[r][c]=0;
    }

    public void reset(){
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = 0;
            }
        }
    }

    public Elefant getActor() {
        return actor;
    }
}
