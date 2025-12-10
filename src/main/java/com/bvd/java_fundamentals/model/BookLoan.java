package com.bvd.java_fundamentals.model;
import java.time.LocalDate;
import java.util.Date;

public class BookLoan {

    private String loanID;
    private String memberID;
    private LocalDate loanDate;
    private String bookTitle;
    private String genre;
    private String author;
    private Integer daysLoaned;


    public BookLoan(String loanID, String memberID, LocalDate loanDate, String bookTitle, String genre, String author, Integer daysLoaned){
        this.loanID = loanID;
        this.memberID = memberID;
        this.loanDate = loanDate;
        this.bookTitle = bookTitle;
        this.genre = genre;
        this.author = author;
        this.daysLoaned = daysLoaned;
    }

    public String getLoanID(){
        return loanID;
    }

    public String getMemberID(){
        return memberID;
    }

    public LocalDate getLoanDate(){
        return loanDate;
    }

    public String getBookTitle(){
        return bookTitle;
    }

    public String getGenre(){
        return genre;
    }

    public String getAuthor(){
        return author;
    }

    public Integer getDaysLoaned(){
        return daysLoaned;
    }

}
