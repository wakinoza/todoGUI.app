package todoApp;

import java.awt.Color;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

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

    JLabel textLabel = new JLabel("やりたいこと");
    TEXT_INPUT= new JTextField();

    JPanel todoInputPanel = new JPanel();
    todoInputPanel.setLayout(new BoxLayout(todoInputPanel, BoxLayout.X_AXIS));

    todoInputPanel.add(textLabel);
    todoInputPanel.add(TEXT_INPUT);

    SAVE_BUTTON = new JButton("todoを保存");
    SAVE_BUTTON.setBackground(Color.GREEN);
    TEXT_CLEAR_BUTTON = new JButton("削除");
    TEXT_CLEAR_BUTTON.setForeground(Color.RED);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

    buttonPanel.add(SAVE_BUTTON);
    buttonPanel.add(TEXT_CLEAR_BUTTON);

    TODO_CREATE_PANEL.add(descriptionLabel);
    TODO_CREATE_PANEL.add(todoInputPanel);
    TODO_CREATE_PANEL.add(buttonPanel);

    TODO_LIST_PANEL = new JPanel();
  }

  /**.
   * TEXT_INPUTのgetterメソッド
   *
   * @return TEXT_INPUTへの参照
   */
  @SuppressWarnings("EI_EXPOSE_REP")
  public JTextField getTextInput() {
    return this.TEXT_INPUT;
  }

  /**.
   * TODO_CREATE_PANELのgetterメソッド
   *
   * @return TODO_CREATE_PANELへの参照
   */
  @SuppressWarnings("EI_EXPOSE_REP")
  public JPanel getTodoCreatePanel() {
    return this.TODO_CREATE_PANEL;
  }

  /**.
   * SAVE＿BUTTONのgetterメソッド
   *
   * @return SAVE_BUTTONへの参照
   */
  @SuppressWarnings("EI_EXPOSE_REP")
  public JButton getSaveButton() {
    return this.SAVE_BUTTON;
  }

  /**.
   * TEXT_CLEAR_BUTTONのgetterメソッド
   *
   * @return TEXT_CLEAR_BUTTONへの参照
   */
  @SuppressWarnings("EI_EXPOSE_REP")
  public JButton getTextClearButton() {
    return this.TEXT_CLEAR_BUTTON;
  }

  /**.
   * TODO_LIST_PANELのgetterメソッド
   *
   * @return TODO_LIST_PANELへの参照
   */@SuppressWarnings("EI_EXPOSE_REP")
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


  public void updateTodoListPanel(Map<String, String> pendingFileNames, Map<String, String> progressFileNames, Map<String, String> CompletedFileNames,  Control control); {

    JScrollPane todoListContainerPanel = new JScrollPane();
    todoListContainerPanel.setLayout(new BoxLayout(todoListContainerPanel, BoxLayout.X_AXIS));

    //未実施のtodoリストを表示するJpanelを作成
    JPanel pendingListPanel = new JPanel();
    pendingListPanel.setLayout(new BoxLayout(pendingListPanel, BoxLayout.Y_AXIS));

    for(Map.Entry<String, String> entry : pendingFileNames.entrySet()){

      JPanel todoPanel = new JPanel();
      todoPanel.setLayout(new BoxLayout(todoPanel, BoxLayout.X_AXIS));

      JLabel todoNameLabel = new JLabel(entry.getValue());

      JButton progressButton = new JButton("進行中へ");
      progressButton.setActionCommand(entry.getKey());
      control.setupProgressListener(progressButton);

      JButton clearTodoButton = new JButton("todo削除");
      clearTodoButton.setActionCommand(entry.getKey());
      clearTodoButton.setForeground(Color.RED);
      control.setupClearTodoListener(clearTodoButton);

      todoPanel.add(todoNameLabel);
      todoPanel.add(progressButton);
      todoPanel.add(clearTodoButton);

      pendingListPanel.add(todoPanel);

    }
    todoListContainerPanel.add(pendingListPanel);

    //進行中のtodoリストを表示するJpanelを作成
    JPanel progressListPanel = new JPanel();
    progressListPanel.setLayout(new BoxLayout(progressListPanel, BoxLayout.Y_AXIS));

    for(Map.Entry<String, String> entry : progressFileNames.entrySet()){

      JPanel todoPanel = new JPanel();
      todoPanel.setLayout(new BoxLayout(todoPanel, BoxLayout.X_AXIS));

      JLabel todoNameLabel = new JLabel(entry.getValue());

      JButton completedButton = new JButton("完了へ");
      completedButton.setActionCommand(entry.getKey());
      control.setupProgressListener(completedButton);

      JButton clearTodoButton = new JButton("todo削除");
      clearTodoButton.setActionCommand(entry.getKey());
      clearTodoButton.setForeground(Color.RED);
      control.setupClearTodoListener(clearTodoButton);

      todoPanel.add(todoNameLabel);
      todoPanel.add(completedButton);
      todoPanel.add(clearTodoButton);

      progressListPanel.add(todoPanel);

    }
    todoListContainerPanel.add(progressListPanel);

    //完了のtodoリストを表示するJpanelを作成
    JPanel CompletedListPanel = new JPanel();
    CompletedListPanel.setLayout(new BoxLayout(CompletedListPanel, BoxLayout.Y_AXIS));

    for(Map.Entry<String, String> entry : CompletedFileNames.entrySet()){

      JPanel todoPanel = new JPanel();
      todoPanel.setLayout(new BoxLayout(todoPanel, BoxLayout.X_AXIS));

      JLabel todoNameLabel = new JLabel(entry.getValue());

      JButton clearTodoButton = new JButton("todo削除");
      clearTodoButton.setActionCommand(entry.getKey());
      clearTodoButton.setForeground(Color.RED);
      control.setupClearTodoListener(clearTodoButton);

      todoPanel.add(todoNameLabel);
      todoPanel.add(clearTodoButton);

      CompletedListPanel.add(todoPanel);

    }
    todoListContainerPanel.add(CompletedListPanel);

    TODO_LIST_PANEL.setViewportView(todoListContainerPanel);

    TODO_LIST_PANEL.revalidate();
    TODO_LIST_PANEL.repaint();
  }
}
