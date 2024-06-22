package ru.fiarr4ik.auditlibhomework;

import org.springframework.stereotype.Service;
import ru.fiarr4ik.springbootstarterauditlib.LogExecutionTime;

    @Service
    public class ExampleService {
        @LogExecutionTime
        public void serve() throws InterruptedException {
            Thread.sleep(2000);
        }
    }
