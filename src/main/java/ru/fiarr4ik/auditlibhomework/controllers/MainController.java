package ru.fiarr4ik.auditlibhomework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fiarr4ik.springbootstarterauditlib.annotations.AuditLog;
import ru.fiarr4ik.springbootstarterauditlib.enums.LogLevel;

    @Controller
    public class MainController {

        @RequestMapping("/")
        @AuditLog(logLevel = LogLevel.INFO)
        public String mainView(Model model) {
            model.addAttribute("name", "Denis");
            return "main";
        }
    }
