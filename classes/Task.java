package application.classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class Task implements Serializable
{

    private static final long serialVersionUID = 1L;
    public String getTaskName()
    {
        return taskName;
    }

    String taskName = null;

    public String getMessage()
    {
        return message;
    }

    String message = null;

    public Task(String taskName, String message)
    {
        this.taskName = taskName;
        this.message = message;
    }

    private  void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException
    {
        this.taskName = (String) ois.readObject();
        this.message = (String) ois.readObject();
    }

    // méthode writeObject, utilisée lors de la sérialization
    private  void writeObject(ObjectOutputStream oos) throws IOException
    {
        oos.writeObject(taskName);
        oos.writeObject(message);
    }

}
