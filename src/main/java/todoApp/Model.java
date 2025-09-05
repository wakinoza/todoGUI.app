package todoApp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**.
 * TodoItemインスタンスを管理するクラス
 */
public class Model {
  private List <TodoItem> todoItemList = new ArrayList<>();

  /**. テキストエリアの文字列データから、TodoItemインスタンスを生成するメソッド
   *
   * @param content テキストエリアの文字列データ
   */
  public void saveTodo(String content) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    LocalDateTime now = LocalDateTime.now();
    TodoItem todo = new TodoItem(formatter.format(now), content, Progress.PENDING);
    todoItemList.add(todo);
  }

  /**.
   * TodoItemインスタンスの進捗フィールドを変更するメソッド
   *
   * @param fileName 変更するtodoのファイル名
   * @param newProgress 新しい進捗列挙子
   */
  public void changeProgress(String fileName, Progress newProgress) {
    for (TodoItem todo: todoItemList) {
      if (todo.getFileName().equals(fileName)) {
        todo.setProgress(newProgress);
        break;
      }
    }
  }

  /**.
   * 指定されたtodoItemインスタンスを削除するメソッド
   *
   * @param fileName 削除したいファイル名
   */
  public void clearTodoItem(String fileName) {
    for (TodoItem todo: todoItemList) {
      if (todo.getFileName().equals(fileName)) {
        todoItemList.remove(todo);
        break;
      }
    }
  }

  /**.
   * 指定した進捗のTodoItemを抽出するメソッド
   *
   * @param SpecifiedProgress 抽出したい進捗列挙子
   * @return 指定した進捗のTodoItemのList
   */
  public List<TodoItem> getSpecifiedProgressgTodoList(Progress SpecifiedProgress) {
    List <TodoItem> answers = new ArrayList<>();
    for (TodoItem todo: todoItemList) {
      if (todo.getProgress() == SpecifiedProgress) {
        answers.add(todo);
      }
    }
    return answers;
  }
}
