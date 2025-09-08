package todoApp;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
   * @param controller Controllerクラスのインスタンスへの参照
   */
  public MainFrame(View view, Controller controller, DAO dao, Model model) {
    setTitle("シンプルなtodoアプリ");
    setSize(1200, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    addWindowClosingListener(dao, model);

    controller.makeTodoListPanel();

    setLayout(new BorderLayout());

    add(view.getTodoListPanel(), BorderLayout.CENTER);
    add(view.getTodoCreatePanel(), BorderLayout.NORTH);

    controller.setupTodoCreateListeners();
  }

  private void addWindowClosingListener(DAO dao, Model model) {
    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        dao.writeJSONFile(model.getTodoItemList());
        System.out.println("データを保存しました");
      }
    });
  }
}
