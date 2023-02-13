package ru.youlola.spring.project3.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.youlola.spring.project3.models.Measurement;
import ru.youlola.spring.project3.repositories.MeasurementsRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;
    private final SensorsService sensorsService;

    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorsService sensorsService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsService = sensorsService;
    }
    public List<Measurement> findAll(){
        return measurementsRepository.findAll();
    }

    public void enrichMeasurements(Measurement measurement) {
        measurement.setSensor(sensorsService.findByName(measurement.getSensor().getName()).get());
        measurement.setCreatedAt(LocalDateTime.now());
    }
    @Transactional
    public void addMeasurement(Measurement measurement){
        enrichMeasurements(measurement);
        measurementsRepository.save(measurement);
    }

    public long findRainy(){
        long foundRainyDays = measurementsRepository.countByRainingIsTrue();
        return foundRainyDays;
    }
}

