package com.zziririt.board.model.vo;

public class Like { // Like 클래스 영역 시작
	
	// 선언부
	private int likeUserNo;  //	LIKE_USER_NO	NUMBER
							 // = userNo
	private int likeBoardNo; //	LIKE_BOARD_NO	NUMBER
							 // = boardNo

	// 생성자부
	// 기본생성자
	public Like() { }

	// (모든) 매개변수 생성자
	public Like(int likeUserNo, int likeBoardNo) {
		super();
		this.likeUserNo = likeUserNo;
		this.likeBoardNo = likeBoardNo;
	}
	
	// 메서드부
	// getter / setter 메서드들
	public int getLikeUserNo() {
		return likeUserNo;
	}

	public void setLikeUserNo(int likeUserNo) {
		this.likeUserNo = likeUserNo;
	}

	public int getLikeBoardNo() {
		return likeBoardNo;
	}

	public void setLikeBoardNo(int likeBoardNo) {
		this.likeBoardNo = likeBoardNo;
	}

	// toString 메서드
	@Override
	public String toString() {
		return "Like [likeUserNo=" + likeUserNo + ", likeBoardNo=" + likeBoardNo + "]";
	}
} // Like 클래스 영역 끝