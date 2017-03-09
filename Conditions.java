package trening;

public class Conditions {
	
	private Exercise exercise;
	private int temperature;
	private String weather;
	
	public Conditions(Exercise exercise, int temp, String weather) {
		this.exercise = exercise;
		this.temperature = temp;
		this.weather = weather;
	}

}
