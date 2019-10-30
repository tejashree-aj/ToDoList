package ToDoListPackage;

import java.io.File;
import java.util.Scanner;

/***************************************************************
 * This class manages the options given to user
 *author : tejashree j
 ***************************************************************/

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        //Default welcome msg
        System.out.println(" # Welcome to ToDoly # ");
        ShowOptions();
    }

    //call a method for options to be shown
    private static void ShowOptions() {
        //create instance of class TaskOperations
        TaskOperations taskOperation = new TaskOperations("ToDoList.txt");
        int opChoice = 10;
        taskOperation.viewTaskList();
        do {

            Scanner in = new Scanner(System.in);

            System.out.println("1 - Add new tasks");
            System.out.println("2 - Edit");
            System.out.println("3 - Delete");
            System.out.println("4 - View all Tasklist");
            System.out.println("5 - Filter");
            System.out.println("6 - Save & Quit");

            opChoice = in.nextInt();

            //switch statement to select given options
            switch (opChoice) {

                case 1:
                    taskOperation.addTask();
                    break;

                case 2:
                     taskOperation.editTask();
                    break;

                case 3:
                    taskOperation.deleteTask();
                    break;

                case 4:
                   taskOperation.viewTaskList();
                    break;

                case 5:
                    taskOperation.filterTasks();
                    break;

                case 6:
                    taskOperation.saveTasks();
                    in.close();
                    break;
            }

        } while (opChoice != 6);


    }
}
