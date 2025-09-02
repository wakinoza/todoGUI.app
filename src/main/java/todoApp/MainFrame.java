package todoApp;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**.
 * GUI表示を司るクラス
 * JFrameの設定、必要なレイアウトマネージャやパネルの設置、コントローラークラスにリスナーを設定する
 */
public class MainFrame extends JFrame {

  /**.
   * コンストラクタ
   *
   * @param view    Viewクラスのインスタンスへの参照
   * @param control Controlクラスのインスタンスへの参照
   */
  public MainFrame(View view, Control control) {
    setTitle("シンプルなtodoアプリ");
    setSize(1200, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    control.makeTodoListPanel();

    setLayout(new BorderLayout());

    add(view.getTodoListPanel(), BorderLayout.CENTER);
    add(view.getTodoCreatePanel(), BorderLayout.EAST);

    control.setupTodoCreateListeners();
  }
}
