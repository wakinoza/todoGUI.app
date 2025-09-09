package todo.app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**.
 * JSONファイルの読み書きを行うクラス
 */
public class DAO {

  /**.
   * コンストラクタ
   *
   * @param model Modelインスタンスへの参照
   */
  public DAO(Model model) {
    model.setTodoItemList(readJSONFile());
  }

  /**.
   * JsonファイルからTodoItemインスタンスを読み込むメソッド
   *
   * @return TodoItemインススタンスのリスト
   */
  public List<TodoItem> readJSONFile() {
    ObjectMapper mapper = new ObjectMapper();
    List<TodoItem> newTodoItemList = new ArrayList<>();

    try {
      File file = new File(Main.getSaveDir(), "todos.json");
      if (file.exists()) {
        newTodoItemList = mapper.readValue(file, new TypeReference<List<TodoItem>>() {});
        System.out.println("JSONファイルからデータを読み込みました。");
      }
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("JSONファイルの読み込み中にエラーが発生しました。");
    }
    return newTodoItemList;
  }

  /**.
   *  TodoItemインスタンスのリストをJSONファイルに保存するメソッド
   *
   * @param todoItemList TodoItemインスタンスのList
   */
  public void writeJSONFile(List<TodoItem> todoItemList) {
    ObjectMapper mapper = new ObjectMapper();

    try {
      mapper.writeValue(new File(Main.getSaveDir(), "todos.json"), todoItemList);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
