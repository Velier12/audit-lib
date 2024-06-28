package ru.fiarr4ik.auditlibhomework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@ExtendWith(SpringExtension.class)
@WebMvcTest(AuditLibHomeworkApplicationControllerTest.TestController.class)
@AutoConfigureMockMvc
@AutoConfigureWebClient
class AuditLibHomeworkApplicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void exampleTest() throws Exception {
        ResultActions resultActions = this.mockMvc
                .perform(get("/"));
        resultActions
                .andDo(print());
    }

    @RestController
    public static class TestController {
        @GetMapping("/")
        public String test(Model model) {
            model.addAttribute("name", "Denis");
            return "main";
        }
    }

}