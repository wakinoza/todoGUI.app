package todo.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.SwingUtilities;

/**.
 * メインクラス
 * イベントディスパッチスレッドでUIを生成すし、必要なクラスのインスタンスを生成する
 */
public class Main {
  /**. JSONファイルを保存するディレクトリのパス*/
  private static final String SAVE_DIR = "todos";

  /**.
   * メインメソッド
   *
   * @param args コマンドライン引数
   */
  public static void main(String[] args) {
    Path saveDirPath = Paths.get(SAVE_DIR);
    if (!Files.exists(saveDirPath)) {
      try {
        Files.createDirectories(saveDirPath);
        System.out.println("ディレクトリが作成されました: " + saveDirPath.toAbsolutePath());
      } catch (IOException e) {
        System.err.println("ディレクトリの作成に失敗しました。アプリケーションを終了します。");
        e.printStackTrace();
        return;
      }
    }

    SwingUtilities.invokeLater(() -> {
      Model model = new Model();
      DAO dao = new DAO(model);
      View view = new View();
      Controller controller = new Controller(model, view);
      MainFrame frame = new MainFrame(view, controller, dao, model);
      frame.setVisible(true);
    });
  }

  /**. getterメソッド*/
  public static String getSaveDir() {
    return SAVE_DIR;
  }
}
