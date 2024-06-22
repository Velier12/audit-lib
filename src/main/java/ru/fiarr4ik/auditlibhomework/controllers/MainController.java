package ru.fiarr4ik.auditlibhomework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fiarr4ik.springbootstarterauditlib.annotations.LogExecutionTime;

    @Controller
    public class MainController {

        @RequestMapping("/")
        @LogExecutionTime
        public String mainView() {
            return "main";
        }
    }
