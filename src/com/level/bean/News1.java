package com.level.bean;

public class News1 {
	
	 News2 data;
	 

	
	public News2 getData() {
		return data;
	}



	public void setData(News2 data) {
		this.data = data;
	}



	@Override
	public String toString() {
		return "News1 [data=" + data + "]";
	}



	public class News2 {
		 String subject;
		 String summary;
		 String cover;
		 
		public News2(String subject, String summary, String cover) {
			super();
			this.subject = subject;
			this.summary = summary;
			this.cover = cover;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getSummary() {
			return summary;
		}
		public void setSummary(String summary) {
			this.summary = summary;
		}
		public String getCover() {
			return cover;
		}
		public void setCover(String cover) {
			this.cover = cover;
		}
		@Override
		public String toString() {
			return "News2 [subject=" + subject + ", summary=" + summary
					+ ", cover=" + cover + "]";
		}
	}

}
