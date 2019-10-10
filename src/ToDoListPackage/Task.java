package ToDoListPackage;

import java.util.Calendar;
import java.util.Date;

public class Task {

    // constructor : ask user to enter option
    public Task() {

    }

    //
    private String projectName;
    private String taskDescription;
    private Date createdAt;
    private Date dueDate;
    private boolean taskCompleted;
    private int taskID;


    // 1Getter
    public String getProjectName() {
        return projectName;
    }

    // 1Setter
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    // 2Getter
    public String gettaskDescription() {
        return taskDescription;
    }

    // 2Setter
    public void settaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    // 3Getter
    public Date getcreatedAt() {
        return createdAt;
    }

    // 3Setter
    public void setcreatedAt() {
        this.createdAt = Calendar.getInstance().getTime();
    }

    // 4Getter
    public Date getdueDate() {
        return dueDate;
    }

    // 4Setter
    public void setdueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    // 5Getter
    public String gettaskCompleted() {
        return taskCompleted? "Completed":"Pending";
    }

    // 5Setter
    public void settaskCompleted(boolean taskCompleted) {
        this.taskCompleted = taskCompleted;
    }

    // 6Setter
    public void setTaskID(int taskID ) {
        this.taskID = taskID;
    }

    // 6Getter
    public int getTaskID() {
        return taskID;

    }


}
