package com.example.influxdb.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

import com.example.influxdb.model.StreamInfo;

@Service
public class StreamInfoService {
	private static final String SELECT_LATEST = "select * from streamInfo3 limit 1";

	@Autowired
	private InfluxDBTemplate<Point> influxDBTemplate;
	private InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();

	public void save(StreamInfo streamInfo) {
		Point p = Point.measurement("streamInfo3")
		               .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
		               .addField("bitrate", streamInfo.getBitrate())
		               .addField("videorate", streamInfo.getVideorate())
		               .addField("audiorate", streamInfo.getAudiorate())
		               .addField("framerate", streamInfo.getFramerate())
		               .tag("streamName", streamInfo.getStreamName())
		               .build();
		influxDBTemplate.write(p);
	}

	public StreamInfo getLatest() {
		Query query = new Query(SELECT_LATEST, influxDBTemplate.getDatabase());
		QueryResult result = influxDBTemplate.query(query);

		List<StreamInfo> StreamInfoList = resultMapper.toPOJO(result, StreamInfo.class);
		return StreamInfoList.isEmpty() ? null : StreamInfoList.get(0);
	}
}
