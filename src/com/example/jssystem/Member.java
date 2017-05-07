package com.example.jssystem;

public class Member {
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	private int ID = -1;
	private String name;
	private String sex;
	private int    age;
	private String beginDate;
	private String endDate;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = "";
		result += "ID:" + this.ID + ",";
		result += "����:" + this.name + ",";
		result += "�Ա�:" + this.sex + ",";
		result += "����:" + this.age + ",";
		result += "�쿨ʱ��:" + this.beginDate + ",";
		result += "����ʱ��:" + this.endDate ;
		return result;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
