package todoApp;

public class TodoItem {
  private final String TEXT;
  private Progress progress;

  public TodoItem (String text, Progress progress) {
    this.TEXT = text;
    this.progress = progress;
  }

  public String getText() {
    return this.TEXT;
  }

  public Progress getProgress() {
    return this.progress;
  }

  public void setProgress(Progress progress) {
    this.progress = progress;
  }
}
