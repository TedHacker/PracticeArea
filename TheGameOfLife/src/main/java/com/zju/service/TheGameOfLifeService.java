package com.zju.service;

import com.zju.meta.CellState;
import com.zju.meta.CellularArray;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TheGameOfLifeService {
    private int[] direct = {-1, 0, 1};

    private int countLiveNeighbor(CellularArray now, int x, int y) {
        int count = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (CellState.LIVE.getValue() == now.getCell(x + this.direct[i], y + this.direct[j])) {
                    ++count;
                }
            }
        }
        if (CellState.LIVE.getValue() == now.getCell(x, y)) {
            --count;
        }
        return count;
    }

    public CellularArray generate(CellularArray now) {
        if (null == now) {
            return null;
        }
        int liveCount;
        CellularArray next = new CellularArray(now.getRow(), now.getCol());
        for (int i = 0; i < next.getRow(); ++i) {
            for (int j = 0; j < next.getCol(); ++j) {
                liveCount = this.countLiveNeighbor(now, i, j);
                if (CellState.LIVE.getValue() == now.getCell(i, j) && (liveCount < 2 || liveCount > 3)) { //人口过少,人口过多
                    next.setCell(i, j, CellState.DEAD.getValue());
                } else if (CellState.LIVE.getValue() == now.getCell(i, j) && (2 <= liveCount && liveCount <= 3)) { //正常
                    next.setCell(i, j, CellState.LIVE.getValue());
                } else if (CellState.DEAD.getValue() == now.getCell(i, j) && (3 == liveCount)) { //繁殖
                    next.setCell(i, j, CellState.LIVE.getValue());
                }
            }
        }
        return next;
    }

    public CellularArray randInit(CellularArray cellularArray) {
        if (null == cellularArray) return null;
        Random r = new Random();
        int value;
        for (int i = 0; i < cellularArray.getRow(); ++i) {
            for (int j = 0; j < cellularArray.getCol(); ++j) {
                value = r.nextInt(2);
                cellularArray.setCell(i, j, value);
            }
        }
        return cellularArray;
    }

    public CellularArray emptyInit(CellularArray cellularArray) {
        if (null == cellularArray) return null;
        for (int i = 0; i < cellularArray.getRow(); ++i) {
            for (int j = 0; j < cellularArray.getCol(); ++j) {
                cellularArray.setCell(i, j, CellState.DEAD.getValue());
            }
        }
        return cellularArray;
    }
}
