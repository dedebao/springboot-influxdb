package com.example.influxdb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.influxdb.model.StreamInfo;
import com.example.influxdb.service.StreamInfoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootInfluxdbApplicationTests {
	
	@Autowired
	StreamInfoService streamInfoService;

	@Test
	public void contextLoads() {
		
		StreamInfo stinfo=new StreamInfo();
		stinfo.setStreamName("sdtv0215");
		stinfo.setBitrate(123456);
		stinfo.setFramerate(1475);
		stinfo.setAudiorate(7534);
		stinfo.setVideorate(5635);
		streamInfoService.save(stinfo);
		
		StreamInfo streamInfo=streamInfoService.getLatest();		
		System.out.println(streamInfo.getStreamName()+","+streamInfo.getBitrate());
	
	}

}
