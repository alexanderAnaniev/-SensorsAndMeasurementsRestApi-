package ru.youlola.spring.project3.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.youlola.spring.project3.models.Sensor;
import ru.youlola.spring.project3.services.SensorsService;

@Component
public class SensorsValidator implements Validator {

        private final SensorsService sensorsService;

        @Autowired
    public SensorsValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
        public boolean supports(Class<?> clazz) {
            return Sensor.class.equals(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            Sensor sensor = (Sensor) target;

            if (sensorsService.findByName(sensor.getName()).isPresent())
            errors.rejectValue("name", "","Сенсор с таким Именем уже существует");
        }
    }

