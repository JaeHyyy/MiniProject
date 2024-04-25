package com.dto;

import java.util.List;

public class RentalDTO {

	int rental_idx;
	int member_idx;
	int book_idx;
	String rental_period; //날짜는 연산 필요없으면 스트링
	String rental_return;
	int rental_latefee;
	
	
	public RentalDTO() {

	}


	public RentalDTO(int rental_idx, int member_idx, int book_idx, String rental_period,
			String rental_return, int rental_latefee) {
		super();
		this.rental_idx = rental_idx;
		this.member_idx = member_idx;
		this.book_idx = book_idx;
		this.rental_period = rental_period;
		this.rental_return = rental_return;
		this.rental_latefee = rental_latefee;
	}


	public int getRental_idx() {
		return rental_idx;
	}


	public void setRental_idx(int rental_idx) {
		this.rental_idx = rental_idx;
	}


	public int getMember_idx() {
		return member_idx;
	}


	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}


	public int getBook_idx() {
		return book_idx;
	}


	public void setBook_idx(int book_idx) {
		this.book_idx = book_idx;
	}

	public String getRental_period() {
		return rental_period;
	}


	public void setRental_period(String rental_period) {
		this.rental_period = rental_period;
	}


	public String getRental_return() {
		return rental_return;
	}


	public void setRental_return(String rental_return) {
		this.rental_return = rental_return;
	}


	public int getRental_latefee() {
		return rental_latefee;
	}


	public void setRental_latefee(int rental_latefee) {
		this.rental_latefee = rental_latefee;
	}

	
}
