package com.level.bean;

import java.util.List;

public class New4 {

	 List<News1> feeds;

	 
	public New4(List<News1> feeds) {
		super();
		this.feeds = feeds;
	}

	public List<News1> getFeeds() {
		return feeds;
	}

	public void setFeeds(List<News1> feeds) {
		this.feeds = feeds;
	}

	@Override
	public String toString() {
		return "New4 [feeds=" + feeds + "]";
	}
	

}
