package todoApp;

/**.
 * todo情報を保持するクラス
 */
public class TodoItem {
  /**. JSONファイルのファイル名*/
  private final String FILE_NAME;

  /**. テキスト入力欄の文字列情報*/
  private final String TEXT;

  /**. 進捗情報を文字する列挙子*/
  private Progress progress;

  /**.
   * コンストラクタ
   *
   * @param text テキスト入力欄の文字列情報
   * @param progress 進捗情報を文字する列挙子
   */
  public TodoItem (String fileName, String text, Progress progress) {
    this.FILE_NAME = fileName;
    this.TEXT = text;
    this.progress = progress;
  }

  /**. getterメソッド*/
  public String getFileName() {
    return this.FILE_NAME;
  }
  public String getText() {
    return this.TEXT;
  }
  public Progress getProgress() {
    return this.progress;
  }

  /**. setterメソット*/
  public void setProgress(Progress progress) {
    this.progress = progress;
  }
}
