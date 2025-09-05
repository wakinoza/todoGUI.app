package todoApp;

import java.awt.Color;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**.
 * UIを司るクラス
 */
public class View {
  /**. todoを一覧表示するパネル */
  private final JPanel TODO_LIST_PANEL;

  /**. todo新規作成のテキスト入力欄 */
  private final JTextField TEXT_INPUT;

  /**. todoの新規作成パネル*/
  private final JPanel TODO_CREATE_PANEL;

  /**. todo保存ボタン*/
  private final JButton SAVE_BUTTON;

  /**. todo削除ボタン*/
  private final JButton TEXT_CLEAR_BUTTON;

  /**.
   * コンストラクタ
   */
  public View() {
    TODO_CREATE_PANEL = new JPanel();
    TODO_CREATE_PANEL.setLayout(new BoxLayout(TODO_CREATE_PANEL, BoxLayout.Y_AXIS));

    JLabel descriptionLabel = new JLabel("「やりたいこと」を以下に記入して、「todo保存」ボタンを押してください");

    TEXT_INPUT= new JTextField();
    TEXT_INPUT.setSize(50, 20);

    SAVE_BUTTON = new JButton("todoを保存");
    SAVE_BUTTON.setBackground(Color.GREEN);
    TEXT_CLEAR_BUTTON = new JButton("削除");
    TEXT_CLEAR_BUTTON.setForeground(Color.RED);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

    buttonPanel.add(SAVE_BUTTON);
    buttonPanel.add(TEXT_CLEAR_BUTTON);

    TODO_CREATE_PANEL.add(descriptionLabel);
    TODO_CREATE_PANEL.add(TEXT_INPUT);
    TODO_CREATE_PANEL.add(buttonPanel);

    TODO_LIST_PANEL = new JPanel();
  }

  /**. getterメソッド */
  @SuppressWarnings("EI_EXPOSE_REP")
  public JTextField getTextInput() {
    return this.TEXT_INPUT;
  }

  @SuppressWarnings("EI_EXPOSE_REP")
  public JPanel getTodoCreatePanel() {
    return this.TODO_CREATE_PANEL;
  }

  @SuppressWarnings("EI_EXPOSE_REP")
  public JButton getSaveButton() {
    return this.SAVE_BUTTON;
  }

  @SuppressWarnings("EI_EXPOSE_REP")
  public JButton getTextClearButton() {
    return this.TEXT_CLEAR_BUTTON;
  }

  @SuppressWarnings("EI_EXPOSE_REP")
  public JPanel getTodoListPanel() {
    return this.TODO_LIST_PANEL;
  }

  /**.
   * メモ欄に引数で指定されたテキストを表示するメソッド
   *
   * @param content 新しく表示したいテキスト
   */
  public void setTextInputContent(String content) {
    TEXT_INPUT.setText(content);
  }


  public void updateTodoListPanel(List<TodoItem> pendingTodoList,
                                  List<TodoItem> in_progressTodoList,
                                  List<TodoItem> completedTodoList, Controller controller) {

    TODO_LIST_PANEL.removeAll();

    JPanel todoListContainerPanel = new JPanel();
    todoListContainerPanel.setLayout(new BoxLayout(todoListContainerPanel, BoxLayout.X_AXIS));

    //未実施のtodoリストを表示するJPanelを作成
    JPanel pendingListPanel = new JPanel();
    pendingListPanel.setLayout(new BoxLayout(pendingListPanel, BoxLayout.Y_AXIS));

    if (!pendingTodoList.isEmpty()) {
      JLabel PendingDescriptionLabel = new JLabel("未実施");
      pendingListPanel.add(PendingDescriptionLabel);
    }

    for(TodoItem todo: pendingTodoList){

      JPanel todoPanel = new JPanel();
      todoPanel.setLayout(new BoxLayout(todoPanel, BoxLayout.X_AXIS));

      JLabel todoNameLabel = new JLabel(todo.getText());

      JButton progressButton = new JButton("進行中へ");
      progressButton.setActionCommand(todo.getFileName());
      controller.setupProgressListener(progressButton);

      JButton clearTodoButton = new JButton("todo削除");
      clearTodoButton.setActionCommand(todo.getFileName());
      clearTodoButton.setForeground(Color.RED);
      controller.setupClearTodoListener(clearTodoButton);

      todoPanel.add(todoNameLabel);
      todoPanel.add(progressButton);
      todoPanel.add(clearTodoButton);

      pendingListPanel.add(todoPanel);

    }
    todoListContainerPanel.add(pendingListPanel);

    if (!pendingTodoList.isEmpty() && !in_progressTodoList.isEmpty()) {
      JSeparator separator1 = new JSeparator(SwingConstants.VERTICAL);
      todoListContainerPanel.add(separator1);
    }


    //進行中のtodoリストを表示するJPanelを作成
    JPanel progressListPanel = new JPanel();
    progressListPanel.setLayout(new BoxLayout(progressListPanel, BoxLayout.Y_AXIS));

    if (!in_progressTodoList.isEmpty()) {
      JLabel progressDescriptionLabel = new JLabel("実行中");
      progressListPanel.add(progressDescriptionLabel);
    }

    for(TodoItem todo:in_progressTodoList ){

      JPanel todoPanel = new JPanel();
      todoPanel.setLayout(new BoxLayout(todoPanel, BoxLayout.X_AXIS));

      JLabel todoNameLabel = new JLabel(todo.getText());

      JButton completedButton = new JButton("完了へ");
      completedButton.setActionCommand(todo.getFileName());
      controller.setupCompletedListener(completedButton);

      JButton clearTodoButton = new JButton("todo削除");
      clearTodoButton.setActionCommand(todo.getFileName());
      clearTodoButton.setForeground(Color.RED);
      controller.setupClearTodoListener(clearTodoButton);

      todoPanel.add(todoNameLabel);
      todoPanel.add(completedButton);
      todoPanel.add(clearTodoButton);

      progressListPanel.add(todoPanel);

    }
    todoListContainerPanel.add(progressListPanel);

    if (!in_progressTodoList.isEmpty() && !completedTodoList.isEmpty()) {
      JSeparator separator2 = new JSeparator(SwingConstants.VERTICAL);
      todoListContainerPanel.add(separator2);
    }

    //完了のtodoリストを表示するJPanelを作成
    JPanel CompletedListPanel = new JPanel();
    CompletedListPanel.setLayout(new BoxLayout(CompletedListPanel, BoxLayout.Y_AXIS));

    if (!completedTodoList.isEmpty()) {
      JLabel CompletedDescriptionLabel = new JLabel("完了済み");
      CompletedListPanel.add(CompletedDescriptionLabel);
    }

    for(TodoItem todo: completedTodoList){

      JPanel todoPanel = new JPanel();
      todoPanel.setLayout(new BoxLayout(todoPanel, BoxLayout.X_AXIS));

      JLabel todoNameLabel = new JLabel(todo.getText());

      JButton clearTodoButton = new JButton("todo削除");
      clearTodoButton.setActionCommand(todo.getFileName());
      clearTodoButton.setForeground(Color.RED);
      controller.setupClearTodoListener(clearTodoButton);

      todoPanel.add(todoNameLabel);
      todoPanel.add(clearTodoButton);

      CompletedListPanel.add(todoPanel);

    }
    todoListContainerPanel.add(CompletedListPanel);

    TODO_LIST_PANEL.add(todoListContainerPanel);
    TODO_LIST_PANEL.revalidate();
    TODO_LIST_PANEL.repaint();
  }
}
