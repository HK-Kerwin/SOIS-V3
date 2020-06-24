package com.tedu.sois.training.controllerpage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("training/")
public class TrainingPageController {

    @RequestMapping("doGetTrainingDockingPage")
    public String doGetTrainingDockingPage(){
        return "training/docking_list";
    }
}
