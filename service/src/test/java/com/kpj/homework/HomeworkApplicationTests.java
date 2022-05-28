package com.kpj.homework;

import com.kpj.homework.messaging.MessageSender;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class HomeworkApplicationTests {

    @MockBean
    private MessageSender messageSender;

}
