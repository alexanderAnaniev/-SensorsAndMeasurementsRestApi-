package ru.youlola.spring.project3.services;

import com.github.javafaker.Name;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.youlola.spring.project3.models.Sensor;
import ru.youlola.spring.project3.repositories.SensorsRepository;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SensorsServiceTest {
    @Autowired
    SensorsRepository sensorsRepository;
    @MockBean
    Name name;
    @MockBean
    Sensor sensor;

    @Test
    void findByName() {
        sensorsRepository.findByName(String.valueOf(name));
    }
}

