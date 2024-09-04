package com.zziririt.board.model.vo;

public class Category { // Category 클래스 영역 시작
	
	// 선언부
	private String categoryName;	//	CATEGORY_NAME	VARCHAR2(20 CHAR)
	private int status; 			//	STATUS	NUMBER
									// 카테고리 존재or삭제 여부
	
	// 생성자부
	// 기본생성자
	public Category() { }
	
	// (모든) 매개변수 생성자
	public Category(String categoryName, int status) {
		super();
		this.categoryName = categoryName;
		this.status = status;
	}
	
	// 메서드부
	// getter / setter 메서드들
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	// toString 메서드
	@Override
	public String toString() {
		return "Category [categoryName=" + categoryName + ", status=" + status + "]";
	}
} // Category 클래스 영역 끝