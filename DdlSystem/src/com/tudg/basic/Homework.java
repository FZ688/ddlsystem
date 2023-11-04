package com.tudg.basic;

public class Homework {

    private String HomeworkId;
    private String Headline;
    private String context;

    private String deadline;
    private String priority;

    public Homework(String homeworkId ,String headline, String context, String deadline, String priority) {
        this.HomeworkId = homeworkId;
        this.Headline = headline;
        this.context = context;
        this.deadline = deadline;
        this.priority = priority;
    }

    public Homework() {
    }


    public String getHomeworkId() {
        return HomeworkId;
    }

    public String getHeadline() {
        return Headline;
    }

    public String getContext() {
        return context;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getPriority() {
        return priority;
    }


    public void setHomeworkId(String homeworkId) {
        HomeworkId = homeworkId;
    }

    public void setHeadline(String headline) {
        Headline = headline;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
