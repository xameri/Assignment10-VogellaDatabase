package com.example.xusenweli.cis3334unit10yparticipation;

// Comment class
public class Comment {
    private long id; // Declaring variable id with the type of long
    private String comment; // variable comment with the type of string
    private String rate; // variable comment with the type of string

    // The constructor of the comment class
    public Comment(){
        id = id;
        comment = comment;
        rate = rate;
    }
    // Get id method
    public long getId() {
        return id;
    }

    // Set id method
    public void setId(long id) {
        this.id = id;
    }

    // Get commment method
    public String getComment() {
        return comment;
    }

    // Setting the value of comment to a avariable
    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRate() {
        return rate;
    }

    // Setting the value of rate to a avariable
    public void setRate(String rate) {
        this.rate = rate;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return comment + rate;
    }
}
