package com.dto;

public class BookDTO {

	
	int book_idx;
	String book_name;
	String book_author;
	String book_pub;
	String book_year;
	String book_genre;
	String book_ISBN;
	String book_price;
	char book_rent;
	
	public BookDTO() {}

	public BookDTO(int book_idx, String book_name, String book_author, String book_pub, String book_year,
			String book_genre, String book_ISBN, String book_price, char book_rent) {
		this.book_idx = book_idx;
		this.book_name = book_name;
		this.book_author = book_author;
		this.book_pub = book_pub;
		this.book_year = book_year;
		this.book_genre = book_genre;
		this.book_ISBN = book_ISBN;
		this.book_price = book_price;
		this.book_rent = book_rent;
	}

	public int getBook_idx() {
		return book_idx;
	}

	public void setBook_idx(int book_idx) {
		this.book_idx = book_idx;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getBook_author() {
		return book_author;
	}

	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}

	public String getBook_pub() {
		return book_pub;
	}

	public void setBook_pub(String book_pub) {
		this.book_pub = book_pub;
	}

	public String getBook_year() {
		return book_year;
	}

	public void setBook_year(String book_year) {
		this.book_year = book_year;
	}

	public String getBook_genre() {
		return book_genre;
	}

	public void setBook_genre(String book_genre) {
		this.book_genre = book_genre;
	}

	public String getBook_ISBN() {
		return book_ISBN;
	}

	public void setBook_ISBN(String book_ISBN) {
		this.book_ISBN = book_ISBN;
	}

	public String getBook_price() {
		return book_price;
	}

	public void setBook_price(String book_price) {
		this.book_price = book_price;
	}

	public char getBook_rent() {
		return book_rent;
	}

	public void setBook_rent(char book_rent) {
		this.book_rent = book_rent;
	}

	@Override
	public String toString() {
		return "ManagerDTO [book_idx=" + book_idx + ", book_name=" + book_name + ", book_author=" + book_author
				+ ", book_pub=" + book_pub + ", book_year=" + book_year + ", book_genre=" + book_genre + ", book_ISBN="
				+ book_ISBN + ", book_price=" + book_price + ", book_rent=" + book_rent + "]";
	};
	
	
}
