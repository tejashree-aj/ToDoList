package ToDoListPackage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Validation {

    public Date parseDate(String dueDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            Date parsedDueDate = sdf.parse(dueDate);
            if (Calendar.getInstance().getTime().after(parsedDueDate)) {
                System.out.println("Task due date should be future date please reenter future date ");
                return null;
            }
            return parsedDueDate;
        } catch (ParseException e) {
            System.out.println("Invalid date, please enter date in dd/MM/yyyy format ");
            return null;
        }
    }

    public String validateProjectName(String projName) {
        if (projName.trim().length() == 0) {
            System.out.println("Project name can not be empty please reenter");
            return null;
        }
        if (projName.length() > 15) {
            System.out.println("Project name length can not be greater than 15 characters ");
            return null;
        }
        return projName;
    }

    public String validateTaskDescription(String description){
        if (description.length() > 20) {
            System.out.println("Description length can not be greater than 20 characters ");
            return null;
        }
        return description;
    }
}
