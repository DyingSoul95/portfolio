package main.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "TASK")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  private String description;

  private String date;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User author;


  public String getAuthorName() {
    return author.getUsername();
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public Task() {
  }

  public Task(String name, String description, String date) {
    this.name = name;
    this.description = description;
    this.date = date;
  }

  public Task(String name, String description, String date, User author) {
    this.name = name;
    this.description = description;
    this.date = date;
    this.author = author;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getDate() {
    return date;
  }

  public User getAuthor() {
    return author;
  }
}
