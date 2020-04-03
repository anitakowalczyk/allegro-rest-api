package pl.anitakowalczyk.surwejlansapi.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne(targetEntity = User.class)
    private User user;

    private String categoryId;
    private String phrase;
    private String sellerId;

    private Timestamp lastRefresh;

    private boolean active;

    public Subscription() {
    }

    public Subscription(String name, User user, String categoryId, String phrase,
                        String sellerId, Timestamp lastRefresh, boolean active) {
        this.name = name;
        this.user = user;
        this.categoryId = categoryId;
        this.phrase = phrase;
        this.sellerId = sellerId;
        this.lastRefresh = lastRefresh;
        this.active = active;
    }
}
