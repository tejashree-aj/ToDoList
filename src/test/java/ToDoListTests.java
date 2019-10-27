import ToDoListPackage.Task;
import ToDoListPackage.TaskOperations;
import ToDoListPackage.Validation;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


//unit tests for taskoperations and validation class
public class ToDoListTests {

    static String testFileName = "ToDoList-test.txt";

    static TaskOperations taskOperations;

    @BeforeAll
    public static void setUp() throws IOException {
        File file = new File(testFileName);
        if (file.exists())
            file.delete();
        else
            file.createNewFile();
        taskOperations = new TaskOperations(testFileName);
    }

    @Test
    public void addTaskTest() {

        Task task = new Task();
        task.setProjectName("Test Project");
        task.settaskDescription("Test Description");

        String dueDate = "30/10/2019";
        Validation validation = new Validation();
        task.setdueDate(validation.parseDate(dueDate));

        task.setcreatedAt();
        task.settaskCompleted(false);

        taskOperations.addTaskInList(task);

        assertEquals(taskOperations.taskList.size(), 1);
    }

    @Test
    public void SaveTaskTest() {

        boolean saveSuccessFlag = taskOperations.saveTasks();
        assertEquals(saveSuccessFlag, true);
    }

    @Test
    public void RetrieveTaskTest() {

        taskOperations.taskList.clear();
        taskOperations.retrieveData();
        assertEquals(taskOperations.taskList.size(), 1);

    }

    @Test
    public void validationDateTests() {

        Validation validation = new Validation();

        //Task due date should be future date please reenter future date
        Date date = validation.parseDate("13/07/2004");
        assertEquals(null, date);

        //Valid Date
        date = validation.parseDate("13/07/2024");
        assertNotEquals(null, date);

        //Invalid date, please enter date in dd/MM/yyyy format
        date = validation.parseDate("31/02/2024");
        assertEquals(null, date);
    }

    @Test
    public void validationProjectNameTests() {

        //Project name can not be empty please reenter
        Validation validation = new Validation();
        String projectName = validation.validateProjectName("");
        assertEquals(null, projectName);

        //Project name length can not be greater than 15 characters
        projectName = validation.validateProjectName("Project name length exceeds the limit of character");
        assertEquals(null, projectName);

        //valid
        projectName = validation.validateProjectName("Valid Project");
        assertNotEquals(null, projectName);
    }

    @AfterAll
    public static void deleteTestFile() throws IOException {
        File file = new File(testFileName);
        if (file.exists())
            file.delete();
    }

}
