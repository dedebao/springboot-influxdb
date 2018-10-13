package com.example.influxdb.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.example.influxdb.model.Cpu;
import com.example.influxdb.service.*;
@RestController
@RequestMapping(path = "saver")
public class SaverController {

	private CpuService cpuService;

	@Autowired
	public SaverController(final CpuService cpuService) {
		this.cpuService = cpuService;
	}

	@RequestMapping(path = "", method = POST)
	public ResponseEntity<?> writeValue(final @RequestBody Map<String, Object> input) {
		if (!isValidInput(input)) {
			return badRequest().body("'value' is required.");
		}
		Double parsedValue;
		try {
			parsedValue = Double.parseDouble(input.get("value").toString());
		} catch (NumberFormatException e) {
			return badRequest().body("Error parsing '" + input.get("value") + "' to Double");
		}
		cpuService.save(new Cpu(parsedValue));

		return ok("success");
	}

	@RequestMapping(path = "", method = GET)
	public ResponseEntity<?> getLatestValue() {
		return ok(cpuService.getLatest());
	}

	private boolean isValidInput(Map<String, Object> input) {
		return input.containsKey("value") && input.get("value") != null;
	}
}
