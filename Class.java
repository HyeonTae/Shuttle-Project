package smu.shuttle.model;

public class Clas {
	private String id;
	private String pass;
	private String name;
	private String dept;
	private String area;
	
	public Clas(String id, String pass, String name, String dept, String area) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.dept = dept;
		this.area = area;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}
