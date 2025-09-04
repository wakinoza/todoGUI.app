package todoApp;

/**.
 * todo情報を保持するクラス
 */
public class TodoItem {
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
  public TodoItem (String text, Progress progress) {
    this.TEXT = text;
    this.progress = progress;
  }

  /**.
   * 文字列情報のgetterメソッド
   *
   * @return 文字列情報
   */
  public String getText() {
    return this.TEXT;
  }

  /**.
   * 進捗情報のgetterメソッド
   *
   * @return 進捗情報
   */
  public Progress getProgress() {
    return this.progress;
  }

  /**.
   * 進捗情報のsetterメソッド
   *
   * @param progress 新しい進捗情報
   */
  public void setProgress(Progress progress) {
    this.progress = progress;
  }
}
