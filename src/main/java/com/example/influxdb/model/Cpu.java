package com.example.influxdb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.beans.Transient;
import java.time.Instant;

@Measurement(name = "cpu")
public class Cpu {

	@JsonIgnore
	@Column(name = "time")
	private String timeString;

	@Column(name = "value")
	private Double value;

	public Cpu() {
	}

	public Cpu(Double value) {
		this.value = value;
	}

	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String time) {
		this.timeString = time;
	}

	public Long getTime() {
		return Instant.parse(timeString).getEpochSecond();
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
