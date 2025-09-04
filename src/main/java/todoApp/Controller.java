package todoApp;

import java.io.IOException;
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
    VIEW.getSaveButton().addActionListener(e -> {
      String content = VIEW.getTextInput().getText();

      if (content.isEmpty()) {
        JOptionPane.showMessageDialog(null, "内容が記載されていません。", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      }

      try {
        MODEL.saveTodo(content);
        JOptionPane.showMessageDialog(null, "todoが保存されました。", "information",
            JOptionPane.INFORMATION_MESSAGE);
        VIEW.setTextInputContent("");
        makeTodoListPanel();
      } catch (IOException ex) {
        JOptionPane.showMessageDialog(null, "todoの保存に失敗しました。", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    });

    //テキスト削除ボタン
    VIEW.getTextClearButton().addActionListener(e -> VIEW.setTextInputContent(""));

  }

  /**.
   * todoの進行を「未実施」から「実行中」に変更するメソッド
   *
   * @param progressButton progressButtonのインスタンスへの参照
   */
  public void setupProgressListener(JButton progressButton) {

    progressButton.addActionListener(e -> {
      String fileName = e.getActionCommand();

      try {
// todo:       タグを"in_progress"に変更するロジック
        makeTodoListPanel();
      } catch (IOException ex) {
        JOptionPane.showMessageDialog(null, "処理に失敗しました。", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
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

      try {
//   todo:  タグを"completed"に変更するロジック
        makeTodoListPanel();
      } catch (IOException ex) {
        JOptionPane.showMessageDialog(null, "処理に失敗しました。", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    });
  }

  /**.
   * 完了したtodoを削除するメソッド
   *
   * @param clearTodoButton clearTodoButtonのインスタンスへの参照
   */
  public void setupClearTodoListener(JButton clearTodoButton) {

    clearTodoButton.addActionListener(e -> {
      String fileName = e.getActionCommand();
      try {
        MODEL.clearTodoFile(fileName);
        makeTodoListPanel();
        JOptionPane.showMessageDialog(null, "todoを削除しました。", "Information",
            JOptionPane.INFORMATION_MESSAGE);
      } catch (IOException ex) {
        JOptionPane.showMessageDialog(null, "todoの削除に失敗しました。", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    });
  }

  /**.
   * 履歴表示の再構成を指示するメソッド
   */
  public void makeTodoListPanel() {
    List<TodoItem> pendingTodoList = MODEL.getPendingTodoList();
    List<TodoItem> in_progressTodoList = MODEL.getIn_progressTodoList();
    List<TodoItem> completedTodoList = MODEL.getCompletedTodoList();
    VIEW.updateTodoListPanel(pendingTodoList, in_progressTodoList, completedTodoList, this);
  }
}
