package com.example.influxdb.config;

import org.influxdb.dto.Point;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.influxdb.InfluxDBConnectionFactory;
import org.springframework.data.influxdb.InfluxDBProperties;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.data.influxdb.converter.PointConverter;

@Configuration
@EnableConfigurationProperties(InfluxDBProperties.class)
public class InfluxDBConfiguration {
	@Bean
	public InfluxDBConnectionFactory connectionFactory(final InfluxDBProperties properties)
	{
		return new InfluxDBConnectionFactory(properties);
	}

	@Bean
	public InfluxDBTemplate<Point> influxDBTemplate(final InfluxDBConnectionFactory connectionFactory)
	{
		/*
		 * You can use your own 'PointCollectionConverter' implementation, e.g. in case
		 * you want to use your own custom measurement object.
		 */
		return new InfluxDBTemplate<>(connectionFactory, new PointConverter());
	}
}
