package com.zhi.domain;

public class News {
	private int id;
	private String title;
	private long timeLength;

	public News() {
	}

	public News(int id, String title, long timeLength) {
		super();
		this.id = id;
		this.title = title;
		this.timeLength = timeLength;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getTimeLength() {
		return timeLength;
	}

	public void setTimeLength(long timeLength) {
		this.timeLength = timeLength;
	}
}