package com.backend.studycenter.shared.beanscope;

import java.util.Objects;

public class Document {

  private String title;
  private String content;
  private long maxContentSize = 10;

  public Document() {
  }

  public Document(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public Document(String title, String content, long maxContentSize) {
    this.title = title;
    this.content = content;
    this.maxContentSize = maxContentSize;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Document document)) {
      return false;
    }
    return Objects.equals(title, document.title) && Objects.equals(content,
        document.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, content);
  }

  @Override
  public String toString() {
    return "Document{" +
        "title='" + title + '\'' +
        ", content='" + content + '\'' +
        '}';
  }

  public long getMaxContentSize() {
    return maxContentSize;
  }

  public void setMaxContentSize(long maxContentSize) {
    this.maxContentSize = maxContentSize;
  }
}
