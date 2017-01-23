package com.zju.controller;

import com.zju.meta.CellularArray;
import com.zju.service.TheGameOfLifeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/")
public class TheGameOfLifeController {

    private static final int defaultRow = 15;
    private static final int defaultCol = 15;

    @Resource
    private TheGameOfLifeService service;

    @ResponseBody
    @RequestMapping("/randInit")
    public Object getRandInit(@RequestParam(required = false) Integer row, @RequestParam(required = false) Integer col) {
        if (null == row) row = defaultRow;
        if (null == col) col = defaultCol;
        CellularArray cellularArray = service.randInit(new CellularArray(row, col));
        return cellularArray;
    }

    @ResponseBody
    @RequestMapping("/generate")
    public Object generate(@RequestBody CellularArray now) {
        CellularArray next = service.generate(now);
        return next;
    }

    @ResponseBody
    @RequestMapping("/empty")
    public Object empty(@RequestParam(required = false) Integer row, @RequestParam(required = false) Integer col) {
        if (null == row) row = defaultRow;
        if (null == col) col = defaultCol;
        CellularArray next = service.emptyInit(new CellularArray(row,col));
        return next;
    }
}
