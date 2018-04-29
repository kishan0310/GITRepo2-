package com.accent.controller.dto.library;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Book-List")
public class BookBeanList {

	private List<BookBean> bookList;

	public List<BookBean> getBookList() {
		return bookList;
	}
	
	@XmlElement(name="Book")
	public void setBookList(List<BookBean> bookList) {
		this.bookList = bookList;
	}
}
