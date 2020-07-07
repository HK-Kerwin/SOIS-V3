package com.tedu.sois.stu.controller;

import com.tedu.sois.common.pojo.stu.ScoreTable;
import com.tedu.sois.common.vo.JsonResult;
import com.tedu.sois.common.vo.PageObject;
import com.tedu.sois.stu.service.ScoreTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("score/")
public class ScoreTableController {

    @Autowired
    private ScoreTableService service;

    @PostMapping("doSaveScoreInfo")
    public JsonResult doSaveObject(ScoreTable entity){
        service.saveScoreInfo(entity);
        return new JsonResult("保存成功!");
    }


    @PostMapping("doModifiedScore")
    public JsonResult doModifiedScore(@RequestBody List<ScoreTable> list){
        service.modifiedScore(list);
        return new JsonResult("修改成功");
    }

    @PostMapping("findScoreInfo")
    public JsonResult findScoreInfo(@RequestParam("stuDirection") String[] stuDirection,
                                    @RequestParam("stuName") String stuName,
                                    @RequestParam("stuClass") String stuClass,
                                    @RequestParam("classRoomNum") String classRoomNum,
                                    @RequestParam("pageCurrent")Integer pageCurrent){
        PageObject<ScoreTable> pageObjects = service.findPageScore(stuDirection,stuName,stuClass,classRoomNum,pageCurrent);
        return new JsonResult(pageObjects);
    }

    @GetMapping("findStuDirection")
    public JsonResult findStuDirection(){
        return new JsonResult(service.findStuDirection());
    }
}
