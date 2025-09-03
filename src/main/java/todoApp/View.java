package todoApp;

import java.awt.Color;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

    JLabel descriptionLabel = new JLabel("「やりたいこと」を以下に記入して、「todo保存」ボタンを押してください")

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
  public JPanel getTOdoListPanel() {
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

  /**.
   * 履歴のUIを作成するメソッド
   *
   * @param fileNames 保存されているメモのファイル名のリスト
   */
  public void updateHistoryPanel(List<String> fileNames, Control control) {

    JPanel historyContainerPanel = new JPanel();
    historyContainerPanel.setLayout(new BoxLayout(historyContainerPanel, BoxLayout.Y_AXIS));

    for (String fileName : fileNames) {
      JPanel historyPanel = new JPanel();
      historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.X_AXIS));

      JLabel fileNameLabel = new JLabel(fileName);
      historyPanel.add(fileNameLabel);

      JButton editButton = new JButton("再編集");
      editButton.setActionCommand(fileName);
      control.setupEditListener(editButton);

      JButton clearHistoryButton = new JButton("履歴削除");
      clearHistoryButton.setActionCommand(fileName);
      clearHistoryButton.setForeground(Color.RED);
      control.setupClearHistoryListener(clearHistoryButton);

      historyPanel.add(editButton);
      historyPanel.add(clearHistoryButton);

      historyContainerPanel.add(historyPanel);

    }
    HISTORY_SCROLL_PANE.setViewportView(historyContainerPanel);

    HISTORY_SCROLL_PANE.revalidate();
    HISTORY_SCROLL_PANE.repaint();
  }
}
