

public class Player {
	
	public static final String USERNAME="username";
	public static final String REGIONCODE="regioncode";
	public static final String POINTS="points";
	
	private String userName;	
	private String regionCode;
	private int points;
	private String ip;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
}