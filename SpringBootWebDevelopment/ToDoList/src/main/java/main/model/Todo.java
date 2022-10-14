package main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String executor;
    private String content;
    private Date startDate;
    private Date endDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStartDate() {
        if (this.startDate == null) {
            return "";
        }
        return dateToString(this.startDate);
    }

    public void setStartDate(String startDate) {
        this.startDate = stringToDate(startDate);
    }

    public String getEndDate() {
        if (this.endDate == null) {
            return "";
        }
        return dateToString(this.endDate);
    }

    public void setEndDate(String endDate) {
        this.endDate = stringToDate(endDate);
    }

    private static String dateToString(Date date) {
        return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }

    private static Date stringToDate(String strDate) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(strDate);
        } catch (ParseException e) {
            return null;
        }
    }
}
