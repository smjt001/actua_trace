package com.actuatrace.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.EndpointsSupplier;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.actuatrace.entities.ActuatorRecord;
import com.actuatrace.repositories.ActuatorRecordRepository;

@RestController
@Profile("actuators-page")
public class ActuatorsController {

    @Autowired
    private EndpointsSupplier<ExposableEndpoint<?>> endpointsSupplier;

    @Autowired
    private ActuatorRecordRepository recordRepository;

    @GetMapping("/actuator")
    public ModelAndView listActuators() {
        List<Map<String, String>> endpoints = endpointsSupplier.getEndpoints()
                .stream()
                .map(endpoint -> {
                    String id = endpoint.getEndpointId().toString();
                    String url = "/" + id;
                    // Collection<?> mediaTypes = endpoint.getOperations();
                    // String mediaTypesStr = mediaTypes.stream().collect(Collectors.joining(", "));
                    // return Map.of("id", id, "url", url, "mediaTypes", mediaTypesStr);
                    return Map.of("id", id, "url", url);
                })
                .collect(Collectors.toList());

        ModelAndView modelAndView = new ModelAndView("actuator");
        modelAndView.addObject("endpoints", endpoints);
        modelAndView.addObject("actuatorRecord", new ActuatorRecord()); // Added for form binding
        System.out.println("Results Called...");
        return modelAndView;
    }

    @PostMapping("/save-record")
    public String saveRecord(@RequestBody ActuatorRecord record) {
        record.setTimestamp(LocalDateTime.now());
        recordRepository.save(record);
        System.out.println("Recode Save Called...");
        return "Record saved successfully!";
    }
}
