package com.egor.shop.models;


import jakarta.persistence.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private String title,info,image;

    private short price;
    @JoinColumn(name = "user_id")
    @OneToOne(fetch = FetchType.EAGER)
    private User user;


    public Item() {
    }

    public Item(String title, String info, String image, short price,User user) {
        this.title = title;
        this.info = info;
        this.image = image;
        this.price = price;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public short getPrice() {
        return price;
    }

    public void setPrice(short price) {
        this.price = price;
    }
}
