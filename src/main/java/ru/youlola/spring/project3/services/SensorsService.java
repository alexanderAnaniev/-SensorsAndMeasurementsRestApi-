package ru.youlola.spring.project3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.youlola.spring.project3.models.Sensor;
import ru.youlola.spring.project3.repositories.SensorsRepository;

import java.util.Optional;

@Service
@Transactional
public class SensorsService {

    private final SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    public Optional<Sensor> findByName(String name){
        return sensorsRepository.findByName(name);
    }

    @Transactional
    public void register(Sensor sensor) {
        sensorsRepository.save(sensor);
    }
}
