package org.finfourconsumer.project.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // можно заменить на GenerationType.IDENTITY тогда не будет сквозной нумерации
    private int id;

    @Column(name = "doc_date")
    private Date date;

    @Column(name = "origin_docname")
    private String originDocName;

    @Column(name = "doc_number")
    private long number;

    @Column(name = "doc_sum")
    private long sum;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "comment")
    private String comment;

    @Column(name = "direction")
    private String direction;

    @Column(name = "file_data")
    private byte[] body;

    public Document() {
        //
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOriginDocName() {
        return originDocName;
    }

    public void setOriginDocName(String originDocName) {
        this.originDocName = originDocName;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
