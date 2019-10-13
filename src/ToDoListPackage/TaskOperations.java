package ToDoListPackage;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;


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

        removeTaskFromList(taskId);

        System.out.println("Task deleted successfully!");

        viewtasks();
    }

    private void removeTaskFromList(int taskId) {
        taskList.stream().filter(x -> x.getTaskID() == taskId).findFirst().ifPresent(x -> {
            taskList.remove(x);
        });

    }
    //method to edit task
    public void editTask() {

        viewtasks();
        System.out.println("Enter task ID to edit task: ");
        Scanner taskScanner = new Scanner(System.in);

        Task tasktobeEdited = null;
        while (tasktobeEdited == null) {
            int selectedTaskId;
            selectedTaskId = taskScanner.nextInt();
            tasktobeEdited = taskList.stream().filter(x -> x.getTaskID() == selectedTaskId).findFirst().orElse(null);
            if (tasktobeEdited == null)
                System.out.println("Please enter valid Task ID to edit task: ");
        }

        System.out.println("Please select property to be edited: ");

        System.out.println("1 - Change Project Name");
        System.out.println("2 - Change Task Description");
        System.out.println("3 - Change Task Due Date");
        System.out.println("4 - Set Task Status as Completed");
        int taskPropertyOption = taskScanner.nextInt();
        taskScanner = new Scanner(System.in);
        switch (taskPropertyOption) {

            case 1:
                System.out.println("Please enter project name: ");
                String correctProjectName = null;
                while (correctProjectName == null) {
                    correctProjectName = taskScanner.nextLine();
                    correctProjectName = validation.validateProjectName(correctProjectName);
                }
                tasktobeEdited.setProjectName(correctProjectName);
                break;
            case 2:
                System.out.println("Please enter task description: ");
                String correctTaskDescription = null;
                while (correctTaskDescription == null) {
                    correctTaskDescription = taskScanner.nextLine();
                    correctTaskDescription = validation.validateTaskDescription(correctTaskDescription);
                }
                tasktobeEdited.settaskDescription(correctTaskDescription);
                break;

            case 3:
                System.out.println("Please enter due date: ");
                Date correctDueDate = null;
                while (correctDueDate == null) {
                    String dueDate = taskScanner.nextLine();
                    correctDueDate = validation.parseDate(dueDate);
                }
                tasktobeEdited.setdueDate(correctDueDate);
                break;

            case 4:
                System.out.println("Please enter if task completed Y/N: ");
                String taskCompleted = taskScanner.nextLine();
                if(taskCompleted.equalsIgnoreCase( "Y")){
                    tasktobeEdited.settaskCompleted(true);
                }
                else if (taskCompleted.equalsIgnoreCase ("N")){
                    tasktobeEdited.settaskCompleted(false);
                }
                break;
        }

        removeTaskFromList(tasktobeEdited.getTaskID());
        taskList.add(tasktobeEdited);

        System.out.println("Task updated successfully!");

        viewtasks();
    }

    //method to save & quit

   /* public void createFile ()
    {
        try {
            FileOutputStream fos = new FileOutputStream("output");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(taskList); // write MenuArray to ObjectOutputStream
            oos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }*/

}
