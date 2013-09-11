package com.hww.model;

public class BookType {
	private String typeID;
	private String typeName;
	private int days;
	private String fakuan;
	
	public String getFakuan() {
		return fakuan;
	}
	public void setFakuan(String fakuan) {
		this.fakuan = fakuan;
	}
	public String getTypeID() {
		return typeID;
	}
	public void setTypeID(String typeID) {
		this.typeID = typeID;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}

}
