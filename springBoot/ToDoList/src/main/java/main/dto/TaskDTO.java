package main.dto;

import lombok.Builder;
import lombok.Data;
import main.model.User;

@Data
@Builder

public class TaskDTO {

  private Long id;

  private String name;

  private String description;

  private String date;

  private UserDTO authorDTO;

  private User author;

  private String authorName;

  public TaskDTO() {
  }

  public TaskDTO(String name, String description, String date) {
    this.name = name;
    this.description = description;
    this.date = date;
  }

  public TaskDTO(String name, String description, String date, User author) {
    this.name = name;
    this.description = description;
    this.date = date;
    this.author = author;
  }

  public TaskDTO(Long id, String name, String description, String date, User author) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.date = date;
    this.author = author;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  public String getAuthorName() {
    return author.getUsername();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }
}
