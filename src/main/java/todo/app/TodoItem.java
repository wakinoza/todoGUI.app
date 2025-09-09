package todo.app;

/**.
 * todo情報を保持するクラス
 */
public class TodoItem {
  /**. JSONファイルのファイル名*/
  private  String fileName;

  /**. テキスト入力欄の文字列情報*/
  private  String text;

  /**. 進捗情報を文字する列挙子*/
  private Progress progress;

  public TodoItem () {

  }

  /**.
   * コンストラクタ
   *
   * @param text テキスト入力欄の文字列情報
   * @param progress 進捗情報を文字する列挙子
   */
  public TodoItem (String fileName, String text, Progress progress) {
    this.fileName = fileName;
    this.text = text;
    this.progress = progress;
  }

  /**. getterメソッド*/
  public String getFileName() {
    return this.fileName;
  }
  public String getText() {
    return this.text;
  }
  public Progress getProgress() {
    return this.progress;
  }

  /**. setterメソット*/
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }
  public void setText(String text) {
    this.text = text;
  }
  public void setProgress(Progress progress) {
    this.progress = progress;
  }
}
