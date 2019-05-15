package com.ind.weighing.Indo_weighing.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="application" , ignoreUnknownFields=false)
public class ApplicationProperties {
	private final Indodb indodb =new Indodb();
	

	public Indodb getIndodb() {
		return indodb;
	}


	public static class Indodb {
		private String driverName="";
		private String wsurl = "";
		private String wsurlUsername="";
		private String wsurlPassword="";
		public String getDriverName() {
			return driverName;
		}
		public void setDriverName(String driverName) {
			this.driverName = driverName;
		}
		public String getWsurl() {
			return wsurl;
		}
		public void setWsurl(String wsurl) {
			this.wsurl = wsurl;
		}
		public String getWsurlUsername() {
			return wsurlUsername;
		}
		public void setWsurlUsername(String wsurlUsername) {
			this.wsurlUsername = wsurlUsername;
		}
		public String getWsurlPassword() {
			return wsurlPassword;
		}
		public void setWsurlPassword(String wsurlPasssword) {
			this.wsurlPassword = wsurlPasssword;
		}
		
		
	}
}
