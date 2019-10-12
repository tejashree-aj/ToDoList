package ToDoListPackage;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class TaskOperations {

    private static ArrayList<Task> taskList;
    private Validation validation;


    public TaskOperations() {
        taskList = new ArrayList<Task>();
        validation = new Validation();
    }

    //method to add task
    public void addTask() {

        Task task = new Task();

        //To add ID to tasks

        task.setTaskID(taskList.size() + 1);

        Scanner taskScanner = new Scanner(System.in);

        //To add project name
        System.out.println("Enter project name : ");
        String correctProjectName = null;
        while (correctProjectName == null) {
            correctProjectName = taskScanner.nextLine();
            correctProjectName = validation.validateProjectName(correctProjectName);
        }
        task.setProjectName(correctProjectName);

        //To add task description
        System.out.println("Enter task description : ");
        String correctTaskDescription = null;
        while (correctTaskDescription == null) {
            correctTaskDescription = taskScanner.nextLine();
            correctTaskDescription = validation.validateTaskDescription(correctTaskDescription);
        }
        task.settaskDescription(correctTaskDescription);

        //To add due date
        System.out.println("Enter task due date (dd/MM/yyyy): ");
        //declare Date variable null
        Date correctDueDate = null;
        //while loop - date variable is null
        while (correctDueDate == null) {
            String dueDate = taskScanner.nextLine();
            correctDueDate = validation.parseDate(dueDate);
        }
        task.setdueDate(correctDueDate);

        task.setcreatedAt();

        //add task completed
        task.settaskCompleted(false);

        taskList.add(task);

        System.out.println("Task has been added!");


    }

    //method to view tasks
    public void viewtasks() {
        System.out.println("---------------------------------------------------------------------------------------------------------------------");

        System.out.printf("%7s %17s %21s %25s %12s %30s", "TASK ID", "Project Name", "Description", "Due Date", "Status", "Task Created on");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        for (Task task : taskList) {

            System.out.format(
                    "%7s %17s %21s %25s %12s %30s",
                    Integer.toString(task.getTaskID()), task.getProjectName(), task.gettaskDescription(), task.getdueDate().toInstant(),
                    task.gettaskCompleted()
                    , task.getcreatedAt().toInstant());

            System.out.println();
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
    }

    //method to delete task
    public void deleteTask() {

        viewtasks();
        System.out.println("Please enter Task Id to delete: ");

        Scanner taskScanner = new Scanner(System.in);
        int taskId;
        taskId = taskScanner.nextInt();

        taskList.stream().filter(x -> x.getTaskID() == taskId).findFirst().ifPresent(x -> {
            taskList.remove(x);
        });


        System.out.println("Task deleted successfully!");

        viewtasks();
    }

    //public void remove ()

}
