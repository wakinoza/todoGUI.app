package todoApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DAO {

  public DAO() {

  }

  public void writeJSONFile(List<TodoItem> todoItemList) {
    ObjectMapper mapper = new ObjectMapper();

    try {
      mapper.writeValue(new File(Main.getSaveDir() + "todos.json"), todoItemList);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
