package ToDoListPackage;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


public class Task implements Serializable {

    // constructor : ask user to enter option
    public Task() {

    }


    private String projectName;
    private String taskDescription;
    private Date createdAt;
    private Date dueDate;
    private boolean taskCompleted;
    private int taskID;


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    public String gettaskDescription() {
        return taskDescription;
    }

    public void settaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }


    public Date getcreatedAt() {
        return createdAt;
    }

    public void setcreatedAt() {
        this.createdAt = Calendar.getInstance().getTime();
    }


    public Date getdueDate() {
        return dueDate;
    }

    public void setdueDate(Date dueDate) {
        this.dueDate = dueDate;
    }


    public String gettaskCompleted() {

        if (taskCompleted)
            return "Completed";
        else {
            if (Calendar.getInstance().getTime().before(getdueDate())) {
                return "Pending";
            } else {
                return "Pending - Due date lapsed!";
            }
        }
    }

    public void settaskCompleted(boolean taskCompleted) {
        this.taskCompleted = taskCompleted;
    }


    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getTaskID() {
        return taskID;
    }


}
