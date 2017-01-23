package com.zju.meta;

/**
 * 细胞状态
 */
public enum CellState {
    DEAD(0),
    LIVE(1);
    private int value;

    CellState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
