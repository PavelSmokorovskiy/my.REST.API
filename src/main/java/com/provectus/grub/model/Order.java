package com.provectus.grub.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Document(collection = "orders")
public class Order {

//    @Id
    private int id;
    private String userAdmin;
    private List<User> users;
//    private List<Food> food;
    private Status status;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yy/MM/dd-HH:mm")
    private Date lockTime;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yy/MM/dd-HH:mm")
    private Date orderTime;
    private String comment;

    public static Builder builder() {
        return new Order().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder id(int id) {
            Order.this.id = id;
            return this;
        }

        public Builder userAdmin(String userAdmin) {
            Order.this.userAdmin = userAdmin;
            return this;
        }

        public Builder users(User userName, Food foodName) {
            if (Order.this.users == null) {
                Order.this.users = new ArrayList<>();
            }

            userName.addFood(foodName);

            Order.this.users.add(userName);

            return this;
        }


        public Builder status(Status status) {
            Order.this.status = status;
            return this;
        }

        public Builder lockTime(Date lockTime) {
            Order.this.lockTime = lockTime;
            return this;
        }

        public Builder orderTime(Date orderTime) {
            Order.this.orderTime = orderTime;
            return this;
        }

        public Builder comment(String comment) {
            Order.this.comment = comment;
            return this;
        }

        public Order build() {
            return Order.this;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(String userAdmin) {
        this.userAdmin = userAdmin;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
