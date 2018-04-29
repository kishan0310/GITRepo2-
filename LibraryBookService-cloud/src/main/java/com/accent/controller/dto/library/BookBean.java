package com.accent.controller.dto.library;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;


@XmlRootElement(name="Books")
public class BookBean extends ResourceSupport{

	private Integer bookId;
	private String bookName;
	private String bookAuthor;
	private String bookPublisher;
	private String bookISBN;

	public BookBean(){
	}

	public BookBean(int bookId, String bookName, String bookAuthor, String bookPublisher, String bookISBN) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookPublisher = bookPublisher;
		this.bookISBN = bookISBN;
	}
	public Integer getBookId() {
		return bookId;
	}
	@XmlElement
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	@XmlElement(nillable=false)
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	@XmlElement
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	@XmlElement
	public String getBookPublisher() {
		return bookPublisher;
	}
	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}
	@XmlElement
	public String getBookISBN() {
		return bookISBN;
	}
	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}
}
