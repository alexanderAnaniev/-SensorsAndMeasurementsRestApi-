package ru.youlola.spring.project3.services;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.youlola.spring.project3.repositories.MeasurementsRepository;

@SpringBootTest
public class MeasurementsServiceTest {
@Autowired
    private MeasurementsService measurementsService;
@Autowired
private MeasurementsRepository measurementsRepository;

    @Test
    void findAll() {
        measurementsService.findAll();

    }

    @Test
    void findRainy() {
        measurementsRepository.countByRainingIsTrue();
    }
}