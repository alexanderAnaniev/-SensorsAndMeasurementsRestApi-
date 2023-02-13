package ru.youlola.spring.project3.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.youlola.spring.project3.dto.MeasurementsDTO;
import ru.youlola.spring.project3.dto.MeasurementsResponse;
import ru.youlola.spring.project3.models.Measurement;
import ru.youlola.spring.project3.services.MeasurementsService;
import ru.youlola.spring.project3.util.MeasurementErrorResponse;
import ru.youlola.spring.project3.util.MeasurementException;
import ru.youlola.spring.project3.util.MeasurementValidator;

import java.util.List;
import java.util.stream.Collectors;

import static ru.youlola.spring.project3.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;

    public MeasurementsController(MeasurementsService measurementsService, ModelMapper modelMapper, MeasurementValidator measurementValidator) {
        this.measurementsService = measurementsService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasurementsDTO measurementsDTO, BindingResult bindingResult) throws JsonProcessingException {
        Measurement measurementToAdd = convertToMeasurements(measurementsDTO);
        measurementValidator.validate(measurementToAdd,bindingResult);
        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        measurementsService.addMeasurement(measurementToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e){
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping()
    public MeasurementsResponse getMeasurements(){
        return new MeasurementsResponse(measurementsService.findAll().stream().map(this::convertToMeasurementsDTO)
                .collect(Collectors.toList()));
    }
    private Measurement convertToMeasurements(MeasurementsDTO measurementsDTO){
        return  modelMapper.map(measurementsDTO, Measurement.class);
    }
    private MeasurementsDTO convertToMeasurementsDTO(Measurement measurement){
        return modelMapper.map(measurement, MeasurementsDTO.class);
    }

    @GetMapping("/rainyDays")
    public long getRainy(){
        return measurementsService.findRainy();
    }
}
