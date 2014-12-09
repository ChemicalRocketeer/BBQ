package aptosAppClasses;

public class Time {
	private Long millis;
	
	public Time(){
	millis = System.currentTimeMillis();
	}
	private int convertTimeHours(){
		int hours = (int) (millis/100000000)/3600;
		return hours;
	}
	private int convertTimeMinutes(){
		int minutes = (int) ((millis/1000)/60) % 60;
		return minutes;
	}
	private int convertTimeSeconds(){
		int seconds = (int) (millis/1000);
		return seconds;
	}
	public String getTime(){
	return convertTimeHours() + ":"+ convertTimeMinutes();
	}
}
