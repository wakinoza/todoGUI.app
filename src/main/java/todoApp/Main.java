package todoApp;

import java.io.File;
import javax.swing.SwingUtilities;

/**.
 * メインクラス
 * イベントディスパッチスレッドでUIを生成すし、必要なクラスのインスタンスを生成する
 */
public class Main {
  /**. テキストファイルを保存するディレクトリのパス*/
  private static final String SAVE_DIR = "todos/";

  public static void main(String[] args) {
    File dir = new File(SAVE_DIR);
    if (!dir.exists()) {
      boolean success = dir.mkdir();
      if (!success) {
        // ディレクトリ作成に失敗したら、アプリを起動しない
        System.err.println("ディレクトリの作成に失敗しました。アプリケーションを終了します。");
        return;
      }
    }

    SwingUtilities.invokeLater(() -> {
      Model model = new Model();
      View view = new View();
      Controller controller = new Controller(model, view);
      MainFrame frame = new MainFrame(view, controller);
      frame.setVisible(true);
    });

  }

  public static String getSaveDir() {
    return SAVE_DIR;
  }
}
