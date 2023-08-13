package ru.kolotovkin.lastproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String message;

    private String date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User author;

    private String authorName;

    public Message(String message, String authorName) {
        this.message = message;
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }
}
