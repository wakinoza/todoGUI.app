package todo.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
    JLabel descriptionLabel = new JLabel("「やりたいこと」を50字以内で記入して、「todo保存」ボタンを押してください");
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
  public String getTextInput() {
    return this.TEXT_INPUT.getText();
  }

  /**.
   * MainFrameにTODO_LIST_PANELとTODO_CREATE_PANELを配置するメソッド
   *
   * @param frame MainFrameのインスタンスへの参照
   */
  public void addToMainFrame(JFrame frame) {
    frame.add(this.TODO_LIST_PANEL, BorderLayout.CENTER);
    frame.add(this.TODO_CREATE_PANEL, BorderLayout.SOUTH);
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
   * todo保存ボタンのリスナーをセットアップするメソッド
   *
   * @param listener アクションリスナー
   */
  public void setupSaveButtonListener(ActionListener listener) {
    this.SAVE_BUTTON.addActionListener(listener);
  }

  /**.
   * テキスト削除ボタンのリスナーをセットアップするメソッド
   *
   * @param listener アクションリスナー
   */
  public void setupTextClearButtonListener(ActionListener listener) {
    this.TEXT_CLEAR_BUTTON.addActionListener(listener);
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

    todoListContainerPanel.add(getTodoListPanel(pendingTodoList, Color.MAGENTA, Progress.PENDING,controller));
    todoListContainerPanel.add(getTodoListPanel(in_progressTodoList, Color.BLUE, Progress.IN_PROGRESS,controller));
    todoListContainerPanel.add(getTodoListPanel(completedTodoList, Color.DARK_GRAY, Progress.COMPLETED,controller));

    TODO_LIST_PANEL.add(todoListContainerPanel);
    TODO_LIST_PANEL.revalidate();
    TODO_LIST_PANEL.repaint();
  }

  /**.
   *  指定の進捗のtodo一覧Panelを作成するメソッド
   *
   * @param list 指定進捗のTodoItemのList
   * @param color 説明テキストと枠の色
   * @param progress 進捗の列挙型
   * @param controller Controllerインスタンスへの参照
   * @return 指定の進捗のtodo一覧Panel
   */
  private JPanel getTodoListPanel (List<TodoItem> list, Color color, Progress progress, Controller controller) {

    Dimension fixedWidth = new Dimension(360, Integer.MAX_VALUE);

    JPanel answerListPanel = new JPanel();
    answerListPanel.setLayout(new BoxLayout(answerListPanel, BoxLayout.Y_AXIS));
    answerListPanel.setPreferredSize(fixedWidth);
    answerListPanel.setMaximumSize(fixedWidth);

    String labelText;
    if (progress == Progress.PENDING) {
      labelText = "未実施";
    } else if (progress == Progress.IN_PROGRESS){
      labelText = "進行中";
    } else {
      labelText = "完了済み";
    }

    JLabel descriptionLabel = new JLabel("【" + labelText + "】");
    descriptionLabel.setForeground(color);
    answerListPanel.add(descriptionLabel);

    for(TodoItem todo: list){

      JPanel todoPanel = new JPanel();
      todoPanel.setLayout(new BorderLayout());
      todoPanel.setMaximumSize(new Dimension(320, 70));

      Border colorBorder = BorderFactory.createLineBorder(color, 1);
      Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
      Border compoundBorder = BorderFactory.createCompoundBorder(colorBorder, emptyBorder);
      todoPanel.setBorder(compoundBorder);

      JTextArea todoTextArea = new JTextArea(todo.getText());
      todoTextArea.setLineWrap(true);
      todoTextArea.setWrapStyleWord(true);
      todoTextArea.setEditable(false);
      todoTextArea.setBackground(todoPanel.getBackground());

      if (progress == Progress.PENDING) {
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

        todoPanel.add(buttonPanel, BorderLayout.EAST);

      } else if (progress == Progress.IN_PROGRESS){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton completedButton = new JButton("完了済みへ");
        completedButton.setActionCommand(todo.getFileName());
        controller.setupCompletedListener(completedButton);

        JButton clearTodoButton = new JButton("todo削除");
        clearTodoButton.setActionCommand(todo.getFileName());
        clearTodoButton.setForeground(Color.RED);
        controller.setupClearTodoListener(clearTodoButton);

        buttonPanel.add(completedButton);
        buttonPanel.add(clearTodoButton);

        todoPanel.add(buttonPanel, BorderLayout.EAST);
      } else {

        JButton clearTodoButton = new JButton("todo削除");
        clearTodoButton.setActionCommand(todo.getFileName());
        clearTodoButton.setForeground(Color.RED);
        controller.setupClearTodoListener(clearTodoButton);

        todoPanel.add(clearTodoButton, BorderLayout.EAST);
      }

      todoPanel.add(todoTextArea, BorderLayout.CENTER);

      answerListPanel.add(todoPanel);
    }

    return answerListPanel;
  }

}
