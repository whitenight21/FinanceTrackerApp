package sk.tmconsulting.jednoduchyeshop.model;

import jakarta.persistence.*;

import java.time.LocalDate;

// 7. Create the Tricko class

// 8. add @Entity - to connect with the database
@Entity

// 9. add @Table (name = ..)  .. to create the table in mySQL
@Table(name="records")

public class Financialrecord {

// 10. GeneratedValue - allows the autoincremation of 1
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // tento riadok zabezpeci autoinkrement > id autom. zvyusuje

// 11. add @Column for each, and the name, and it cannot be nullable  ... to all the private strings

    @Column(name="id", updatable = false, nullable = false)
    private Long id;
    @Column(name="category", nullable = false)  // farba
    private String category;
    @Column(name="amount", nullable = false)  // velkost
    private Double amount;
    @Column(name="name", nullable = false)  // menoZakaznika
    private String name;
    @Column(name="date", nullable = false)  // date
    private LocalDate date;
    @Column(name="type", nullable = false)  // name
    private String type;



// 12. Generate the GETTERS and SETTERS
    // Go to : controller.JednoduchyEshopController.java

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}




