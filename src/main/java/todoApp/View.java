package todoApp;

import static javax.swing.text.StyleConstants.setForeground;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

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

    JPanel descriptionPanel = new JPanel();
    descriptionPanel.setLayout(new BorderLayout());
    JLabel descriptionLabel = new JLabel("「やりたいこと」を以下に記入して、「todo保存」ボタンを押してください");
    descriptionPanel.add(descriptionLabel,BorderLayout.WEST);

    TEXT_INPUT= new JTextField();

    JPanel buttonPanel = new JPanel();
    JPanel buttonContainerPanel = new JPanel();
    SAVE_BUTTON = new JButton("todoを保存");
    TEXT_CLEAR_BUTTON = new JButton("削除");
    TEXT_CLEAR_BUTTON.setForeground(Color.RED);

    buttonContainerPanel.setLayout(new BoxLayout(buttonContainerPanel, BoxLayout.X_AXIS));
    buttonContainerPanel.add(SAVE_BUTTON);
    buttonContainerPanel.add(TEXT_CLEAR_BUTTON);

    buttonPanel.setLayout(new BorderLayout());
    buttonPanel.add(buttonContainerPanel,BorderLayout.EAST);

    TODO_CREATE_PANEL.add(descriptionPanel);
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

  /**.
   * todo一覧を表示するメソッド
   *
   * @param pendingTodoList 進捗が「pending」のtodoItemインスタンスのList
   * @param in_progressTodoList 進捗が「in_progress」のtodoItemインスタンスのList
   * @param completedTodoList 進捗が「completed」のtodoItemインスタンスのList
   * @param controller Controllerインスタンスへの参照
   */
  public void updateTodoListPanel(List<TodoItem> pendingTodoList,
                                  List<TodoItem> in_progressTodoList,
                                  List<TodoItem> completedTodoList, Controller controller) {

    TODO_LIST_PANEL.removeAll();

    JPanel todoListContainerPanel = new JPanel();
    todoListContainerPanel.setLayout(new BoxLayout(todoListContainerPanel, BoxLayout.X_AXIS));

    Dimension fixedWidth = new Dimension(350, Integer.MAX_VALUE);

    //未実施のtodoリストを表示するJPanelを作成
    JPanel pendingListPanel = new JPanel();
    pendingListPanel.setLayout(new BorderLayout());

    JPanel pendingDescriptionPanel = new JPanel();
    pendingDescriptionPanel.setLayout(new BorderLayout());
    JLabel pendingDescriptionLabel = new JLabel("【未実施】");
    pendingDescriptionLabel.setForeground(Color.MAGENTA);
    pendingDescriptionPanel.add(pendingDescriptionLabel,BorderLayout.CENTER);

    pendingListPanel.add(pendingDescriptionPanel,BorderLayout.NORTH);

    JPanel pendingListContainerPanel = new JPanel();
    pendingListContainerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    pendingListContainerPanel.setPreferredSize(fixedWidth);
    pendingListContainerPanel.setMaximumSize(fixedWidth);

    for(TodoItem todo: pendingTodoList){

      JPanel todoPanel = new JPanel();
      todoPanel.setMaximumSize(new Dimension(300, Integer.MAX_VALUE));
      todoPanel.setLayout(new BorderLayout());

      Border mazendaBorder = BorderFactory.createLineBorder(Color.MAGENTA, 1);
      Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
      Border compoundBorder = BorderFactory.createCompoundBorder(mazendaBorder, emptyBorder);
      todoPanel.setBorder(compoundBorder);

      JTextArea todoTextArea = new JTextArea(todo.getText());
      todoTextArea.setLineWrap(true);
      todoTextArea.setWrapStyleWord(true);
      todoTextArea.setEditable(false);
      todoTextArea.setBackground(todoPanel.getBackground());

      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

      JButton progressButton = new JButton("進行中へ");
      progressButton.setActionCommand(todo.getFileName());
      controller.setupProgressListener(progressButton);

      JButton clearTodoButton = new JButton("todo削除");
      clearTodoButton.setActionCommand(todo.getFileName());
      clearTodoButton.setForeground(Color.RED);
      controller.setupClearTodoListener(clearTodoButton);

      buttonPanel.add(progressButton);
      buttonPanel.add(clearTodoButton);

      todoPanel.add(todoTextArea, BorderLayout.CENTER);
      todoPanel.add(buttonPanel, BorderLayout.EAST);

      pendingListContainerPanel.add(todoPanel);
    }
    pendingListPanel.add(pendingListContainerPanel,BorderLayout.CENTER);

    todoListContainerPanel.add(pendingListPanel);

    JSeparator separator1 = new JSeparator(SwingConstants.VERTICAL);
    todoListContainerPanel.add(separator1);

    //進行中のtodoリストを表示するJPanelを作成
    JPanel progressListPanel = new JPanel();
    progressListPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    progressListPanel.setPreferredSize(fixedWidth);
    progressListPanel.setMaximumSize(fixedWidth);

    JLabel progressDescriptionLabel = new JLabel("【実行中】");
    progressDescriptionLabel.setForeground(Color.BLUE);
    progressListPanel.add(progressDescriptionLabel);

    for(TodoItem todo:in_progressTodoList ){

      JPanel todoPanel = new JPanel();
      todoPanel.setMaximumSize(new Dimension(300, Integer.MAX_VALUE));
      todoPanel.setLayout(new BorderLayout());

      Border blueBorder = BorderFactory.createLineBorder(Color.BLUE, 1);
      Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
      Border compoundBorder = BorderFactory.createCompoundBorder(blueBorder, emptyBorder);
      todoPanel.setBorder(compoundBorder);

      JTextArea todoTextArea = new JTextArea(todo.getText());
      todoTextArea.setLineWrap(true);
      todoTextArea.setWrapStyleWord(true);
      todoTextArea.setEditable(false);
      todoTextArea.setBackground(todoPanel.getBackground());

      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

      JButton completedButton = new JButton("完了へ");
      completedButton.setActionCommand(todo.getFileName());
      controller.setupCompletedListener(completedButton);

      JButton clearTodoButton = new JButton("todo削除");
      clearTodoButton.setActionCommand(todo.getFileName());
      clearTodoButton.setForeground(Color.RED);
      controller.setupClearTodoListener(clearTodoButton);

      buttonPanel.add(completedButton);
      buttonPanel.add(clearTodoButton);

      todoPanel.add(todoTextArea, BorderLayout.CENTER);
      todoPanel.add(buttonPanel, BorderLayout.EAST);

      progressListPanel.add(todoPanel);

    }
    todoListContainerPanel.add(progressListPanel);

    JSeparator separator2 = new JSeparator(SwingConstants.VERTICAL);
    todoListContainerPanel.add(separator2);

    //完了のtodoリストを表示するJPanelを作成
    JPanel CompletedListPanel = new JPanel();
    CompletedListPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    CompletedListPanel.setPreferredSize(fixedWidth);
    CompletedListPanel.setMaximumSize(fixedWidth);


    JLabel CompletedDescriptionLabel = new JLabel("【完了済み】");
    CompletedDescriptionLabel.setForeground(Color.DARK_GRAY);
    CompletedListPanel.add(CompletedDescriptionLabel);

    for(TodoItem todo: completedTodoList){

      JPanel todoPanel = new JPanel();
      todoPanel.setMaximumSize(new Dimension(300, Integer.MAX_VALUE));
      todoPanel.setLayout(new BorderLayout());

      Border darkGrayBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
      Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
      Border compoundBorder = BorderFactory.createCompoundBorder(darkGrayBorder, emptyBorder);
      todoPanel.setBorder(compoundBorder);

      JTextArea todoTextArea = new JTextArea(todo.getText());
      todoTextArea.setLineWrap(true);
      todoTextArea.setWrapStyleWord(true);
      todoTextArea.setEditable(false);
      todoTextArea.setBackground(todoPanel.getBackground());

      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

      JButton clearTodoButton = new JButton("todo削除");
      clearTodoButton.setActionCommand(todo.getFileName());
      clearTodoButton.setForeground(Color.RED);
      controller.setupClearTodoListener(clearTodoButton);

      todoPanel.add(todoTextArea, BorderLayout.CENTER);
      todoPanel.add(clearTodoButton, BorderLayout.EAST);

      CompletedListPanel.add(todoPanel);

    }
    todoListContainerPanel.add(CompletedListPanel);

    TODO_LIST_PANEL.add(todoListContainerPanel);
    TODO_LIST_PANEL.revalidate();
    TODO_LIST_PANEL.repaint();
  }
}
