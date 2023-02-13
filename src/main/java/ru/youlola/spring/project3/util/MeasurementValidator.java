package ru.youlola.spring.project3.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.youlola.spring.project3.models.Measurement;
import ru.youlola.spring.project3.services.MeasurementsService;
import ru.youlola.spring.project3.services.SensorsService;


@Component
public class MeasurementValidator implements Validator {

    private final MeasurementsService measurementsService;
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementValidator(MeasurementsService measurementsService, SensorsService sensorsService) {
        this.measurementsService = measurementsService;
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;

        if (sensorsService.findByName(measurement.getSensor().getName()).isEmpty())
            errors.rejectValue("sensor", "","Сенсор с таким Именем не зарегистрирован");
    }
}
