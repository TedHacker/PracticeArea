package com.zju.service;

import com.zju.meta.CellState;
import com.zju.meta.CellularArray;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TheGameOfLifeService {
    //方向数组
    private int[] direct = {-1, 0, 1};

    /**
     * 给定阵列和坐标,计算坐标点的邻居存活数量
     * @param now 细胞阵列
     * @param x 横坐标
     * @param y 纵坐标
     * @return
     */
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

    /**
     * 给定细胞阵列,生成下一代的细胞阵列
     * @param now 细胞阵列
     * @return
     */
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

    /**
     * 给定细胞阵列,产生随机结果
     * @param cellularArray 细胞阵列
     * @return
     */
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

    /**
     * 给定细胞阵列,产生初始化结果
     * @param cellularArray
     * @return
     */
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
