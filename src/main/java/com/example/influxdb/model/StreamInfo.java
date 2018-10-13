package com.example.influxdb.model;

import java.io.Serializable;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Measurement(name = "streamInfo3")
public class StreamInfo implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "bitrate")
	private Integer bitrate;
	@Column(name = "framerate")
	private Integer framerate;
	@Column(name = "videorate")
	private Integer videorate;
	@Column(name = "audiorate")
	private Integer audiorate;
	@Column(name = "streamName",tag = true)
	private String  streamName;
	public Integer getBitrate() {
		return bitrate;
	}
	public void setBitrate(Integer bitrate) {
		this.bitrate = bitrate;
	}
	public Integer getFramerate() {
		return framerate;
	}
	public void setFramerate(Integer framerate) {
		this.framerate = framerate;
	}
	public Integer getVideorate() {
		return videorate;
	}
	public void setVideorate(Integer videorate) {
		this.videorate = videorate;
	}
	public Integer getAudiorate() {
		return audiorate;
	}
	public void setAudiorate(Integer audiorate) {
		this.audiorate = audiorate;
	}
	public String getStreamName() {
		return streamName;
	}
	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}	
}
