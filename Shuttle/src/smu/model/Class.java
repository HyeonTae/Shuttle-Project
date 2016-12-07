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
