package todo.app;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**.
 * ModelクラスとViewクラスのかけ渡しを担うクラス
 */
public class Controller {
  /**. Modelクラスのインスタンスへの参照*/
  private final Model MODEL;

  /**. Viewクラスのインスタンスへの参照*/
  private final View VIEW;


  /**.
   * コンストラクタ
   *
   * @param model Modelクラスのインスタンスへの参照
   * @param view Viewクラスのインスタンスへの参照
   */
  @SuppressWarnings("EI_EXPOSE_REP2")
  public Controller(Model model, View view) {
    this.MODEL = model;
    this.VIEW = view;
  }

  /**.
   *メモの保存と削除ボタン処理を指示するメソッド
   */
  public void setupTodoCreateListeners() {

    //todo保存ボタン
    VIEW.setupSaveButtonListener(e -> {
      String content = VIEW.getTextInput();

      if (content.isEmpty()) {
        JOptionPane.showMessageDialog(null, "内容が記載されていません。", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }

      MODEL.saveTodo(content);
      JOptionPane.showMessageDialog(null, "todoが保存されました。", "information",
          JOptionPane.INFORMATION_MESSAGE);
      VIEW.setTextInputContent("");
      makeTodoListPanel();
    });

    //テキスト削除ボタン
    VIEW.setupTextClearButtonListener(e -> VIEW.setTextInputContent(""));

  }

  /**.
   * todoの進行を「未実施」から「実行中」に変更するメソッド
   *
   * @param progressButton progressButtonのインスタンスへの参照
   */
  public void setupProgressListener(JButton progressButton) {

    progressButton.addActionListener(e -> {
      String fileName = e.getActionCommand();
      MODEL.changeProgress(fileName, Progress.IN_PROGRESS);
      makeTodoListPanel();
    });
  }

  /**.
   * todoの進行を「実行中」から「完了」に変更するメソッド
   *
   * @param completedButton completedButtonのインスタンスへの参照
   */
  public void setupCompletedListener(JButton completedButton) {

    completedButton.addActionListener(e -> {
      String fileName = e.getActionCommand();
      MODEL.changeProgress(fileName, Progress.COMPLETED);
      makeTodoListPanel();
    });
  }

  /**.
   * todoを削除するメソッド
   *
   * @param clearTodoButton clearTodoButtonのインスタンスへの参照
   */
  public void setupClearTodoListener(JButton clearTodoButton) {

    clearTodoButton.addActionListener(e -> {
      String fileName = e.getActionCommand();
      MODEL.clearTodoItem(fileName);
      makeTodoListPanel();
      JOptionPane.showMessageDialog(null, "todoを削除しました。", "Information",
          JOptionPane.INFORMATION_MESSAGE);
    });
  }

  /**.
   * 履歴表示の再構成を指示するメソッド
   */
  public void makeTodoListPanel() {
    List<TodoItem> pendingTodoList = MODEL.getSpecifiedProgressgTodoList(Progress.PENDING);
    List<TodoItem> inProgressTodoList = MODEL.getSpecifiedProgressgTodoList(Progress.IN_PROGRESS);
    List<TodoItem> completedTodoList = MODEL.getSpecifiedProgressgTodoList(Progress.COMPLETED);
    VIEW.updateTodoListPanel(pendingTodoList, inProgressTodoList, completedTodoList, this);
  }
}
