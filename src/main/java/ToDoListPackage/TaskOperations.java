package ToDoListPackage;

import com.google.gson.Gson;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


//This class manages the complete task operations

public class TaskOperations {

    public static ArrayList<Task> taskList;
    private Validation validation;
    String fileName;

    public TaskOperations(String fileName) {
        this.fileName = fileName;
        retrieveData();
        if (taskList == null)
            taskList = new ArrayList<Task>();

        validation = new Validation();

    }

    //default view method
    public void viewTaskList() {
        Collections.sort(taskList, Comparator.comparingInt(Task::getTaskID));
        viewtasks(taskList);
    }

    //method to add task
    public void addTask() {

        Task task = new Task();

        //on addition of task default task ID generated
        task.setTaskID(taskList.size() + 1);

        //declaration of scanner
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

        addTaskInList(task);

    }

    //add task in list
    public void addTaskInList(Task task) {
        taskList.add(task);

        System.out.println("Task has been added!");
    }

    //method to view tasks list
    public void viewtasks(List<Task> tasks) {
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");

        System.out.printf("%7s %17s %21s %29s %29s %25s", "TASK ID", "Project Name", "Description", "Due Date", "Status", "Task Created on");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");

        for (Task task : tasks) {

            System.out.format(
                    "%7s %17s %21s %29s %29s %25s",
                    Integer.toString(task.getTaskID()), task.getProjectName(), task.gettaskDescription(), task.getdueDate(),
                    task.gettaskCompleted()
                    , task.getcreatedAt().toInstant());

            System.out.println();
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
    }

    //method to delete task
    public void deleteTask() {

        viewtasks(taskList);
        System.out.println("Please enter Task Id to delete: ");

        Scanner taskScanner = new Scanner(System.in);
        int taskId;
        taskId = taskScanner.nextInt();

        removeTaskFromList(taskId);

        System.out.println("Task deleted successfully!");

        viewtasks(taskList);
    }

    public void removeTaskFromList(int taskId) {
        taskList.stream().filter(x -> x.getTaskID() == taskId).findFirst().ifPresent(x -> {
            taskList.remove(x);
        });

    }

    //method to edit task
    public void editTask() {

        viewtasks(taskList);
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
                if (taskCompleted.equalsIgnoreCase("Y")) {
                    tasktobeEdited.settaskCompleted(true);
                } else if (taskCompleted.equalsIgnoreCase("N")) {
                    tasktobeEdited.settaskCompleted(false);
                }
                break;
        }

        removeTaskFromList(tasktobeEdited.getTaskID());
        taskList.add(tasktobeEdited);

        System.out.println("Task updated successfully!");

        viewtasks(taskList);
    }

    //method to save & quit
    public boolean saveTasks() {

        try {
            Gson gson = new Gson();
            String json = gson.toJson(taskList);

            FileWriter fw = new FileWriter(fileName);
            fw.write(json);
            fw.close();
            return true;
        } catch (IOException e) {
            System.out.println("exception " + e.getMessage());
            e.printStackTrace();
            return false;

        }
    }

    //method to filter tasks by due date and project name
    public void filterTasks() {

        System.out.println("Filter option : \n1 - Filter by Due Date \n2 - Filter by Project Name ");
        Scanner taskScanner = new Scanner(System.in);
        int filterTask = taskScanner.nextInt();
        List<Task> filterSet = new ArrayList<Task>();
        switch (filterTask) {

            case 1:
                taskScanner = new Scanner(System.in);
                System.out.println("Please enter Due date (dd/MM/yyyy): ");

                Date correctDueDateToBeFiltered = null;
                //while loop - date variable is null
                while (correctDueDateToBeFiltered == null) {
                    String dueDateToBeFiltered = taskScanner.nextLine();
                    correctDueDateToBeFiltered = validation.parseDate(dueDateToBeFiltered);
                }
                Date finalCorrectDueDateToBeFiltered = correctDueDateToBeFiltered;
                filterSet = taskList.stream().filter(filteredDueDateTask -> filteredDueDateTask.getdueDate()
                        .compareTo(finalCorrectDueDateToBeFiltered) == 0).collect(Collectors.toList());
                break;
            case 2:
                taskScanner = new Scanner(System.in);
                String projectNameToBeFiltered;
                System.out.println("Please enter Project Name : ");

                projectNameToBeFiltered = taskScanner.nextLine();
                filterSet = taskList.stream().filter(filteredTask -> filteredTask.getProjectName().toLowerCase()
                        .startsWith(projectNameToBeFiltered.toLowerCase()))
                        .collect(Collectors.toList());

                break;

        }
        if (filterSet.size() > 0)
            viewtasks(filterSet);
        else
            System.out.println("No matching records found!");
    }


    //method retrieve saved data
    public void retrieveData() {
        File file = new File(fileName);
        if (file.exists()) {
            Gson gson = new Gson();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(fileName));
                Task[] result = gson.fromJson(br, Task[].class);
                if (result != null)
                    taskList = new ArrayList<Task>(Arrays.asList(result));
            } catch (FileNotFoundException e) {
                System.out.println("exception " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("exception " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}