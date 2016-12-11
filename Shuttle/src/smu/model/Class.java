<<<<<<< HEAD
package smu.model;

public class Class {
	private String classId;
	private String classPass;
	private String className;
	private String classDept;
	private String classArea;
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getClassPass() {
		return classPass;
	}
	public void setClassPass(String classPass) {
		this.classPass = classPass;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassDept() {
		return classDept;
	}
	public void setClassDept(String classDept) {
		this.classDept = classDept;
	}
	public String getClassArea() {
		return classArea;
	}
	public void setClassArea(String classArea) {
		this.classArea = classArea;
	}
	public Class(String classId, String classPass, String className, String classDept, String classArea) {
		super();
		this.classId = classId;
		this.classPass = classPass;
		this.className = className;
		this.classDept = classDept;
		this.classArea = classArea;
	}

	
	
}
=======
package smu.shuttle.model;

public class Class {
	private String id;
	private String pass;
	private String name;
	private String dept;
	private String area;
	
	public Class(String id, String pass, String name, String dept, String area) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.dept = dept;
		this.area = area;
	}
	

	@Override
	public String toString() {
		return "Class [id=" + id + ", pass=" + pass + ", name=" + name + ", dept=" + dept + ", area=" + area + "]";
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
>>>>>>> branch 'hong' of https://github.com/HyeonTae/Shuttle-Project
