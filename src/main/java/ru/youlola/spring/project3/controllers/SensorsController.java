package ru.youlola.spring.project3.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.youlola.spring.project3.dto.SensorDTO;
import ru.youlola.spring.project3.models.Sensor;
import ru.youlola.spring.project3.services.SensorsService;
import ru.youlola.spring.project3.util.MeasurementException;
import ru.youlola.spring.project3.util.MeasurementErrorResponse;
import ru.youlola.spring.project3.util.SensorsValidator;

import static ru.youlola.spring.project3.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/sensors")
public class SensorsController {

    private final SensorsValidator sensorsValidator;
    private final SensorsService sensorsService;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorsController(SensorsValidator sensorsValidator,SensorsService sensorsService, ModelMapper modelMapper) {
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
        this.sensorsValidator = sensorsValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO,
                                             BindingResult bindingResult){
        Sensor sensorToAdd = convertToSensors(sensorDTO);
        sensorsValidator.validate(sensorToAdd,bindingResult);

        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

    sensorsService.register(sensorToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e){
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensors(SensorDTO sensorDTO){
        return  modelMapper.map(sensorDTO, Sensor.class);
    }

    private SensorDTO convertToSensorsDTO(Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }
}
