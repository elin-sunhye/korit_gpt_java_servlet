package com.korit.servlet_study.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korit.servlet_study.entity.Author;
import com.korit.servlet_study.entity.Book;
import com.korit.servlet_study.entity.BookCategory;
import com.korit.servlet_study.entity.Publisher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/book")
public class BookRestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Author author = new Author(1, "author");
        Publisher publisher = new Publisher(1, "publisher");
        BookCategory category = new BookCategory(1, "category");

        Book book = Book.builder()
                .bookId(1)
                .bookName("bookname")
                .isbn("12333333")
                .bookImgUrl("bookImgUrl")

                .authorId(author.getAuthorId())
                .publisherId(publisher.getPublisherId())
                .categoryId(category.getCategoryId())

                .author(author)
                .publisher(publisher)
                .bookCategory(category)
                .build();

//        JSON으로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBook = objectMapper.writeValueAsString(book);

//        요청 응답 과정 : 리액트 <-> 톰캣서버 <-> 필터 <-> 서불릿 -> 서비스 -> 레파지토리 -> 디비
//        요청 후 응답 시 Cors 에러 처리: filter 파일로 뺴서 매 호촐 시 중복 되는 코드 캡슐화
//        resp.setHeader("Access-Control-Allow-Origin", "*");
//        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
//        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
//        resp.setHeader("Access-Control-Allow-Credentials", "true");

        resp.setContentType("application/json");
        resp.getWriter().write(jsonBook);
    }
}
