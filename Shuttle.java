package smu.shuttle.model;

public class Shuttle {
	private String id;
	private String dep; //출발지
	private String dest; //도착지
	private int hour;
	private int min;
	
	public Shuttle(String id, String dep, String dest, int hour, int min) {
		super();
		this.id = id;
		this.dep = dep;
		this.dest = dest;
		this.hour = hour;
		this.min = min;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}
}
