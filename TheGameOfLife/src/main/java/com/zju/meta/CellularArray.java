package com.zju.meta;

/**
 * 细胞阵列
 */
public class CellularArray {
    private int[][] cells;
    private int row;
    private int col;

    public CellularArray() {
    }

    public CellularArray(int row, int col) {
        this.row = row;
        this.col = col;
        this.cells = new int[row][col];
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int[][] getCells() {
        return cells;
    }

    public void setCells(int[][] cells) {
        this.cells = cells;
    }

    public int getCell(int x, int y) {
        if (x < 0 || this.row <= x || y < 0 || this.col <= y) {
            return -1;
        }
        return this.cells[x][y];
    }

    public boolean setCell(int x, int y, int cell) {
        if (x < 0 || this.row <= x || y < 0 || this.col <= y) {
            return false;
        }
        this.cells[x][y] = cell;
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        CellularArray other = (CellularArray) obj;
        if (this.row != other.getRow() || this.col != other.getCol()) return false;
        for (int i = 0; i < this.row; ++i) {
            for (int j = 0; j < this.col; ++j) {
                if (this.cells[i][j] != other.getCell(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
