package com.provectus.grub.model;


import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;

@Entity
@Document(collection = "food")
public class Food {

//    @Id
    private int id;
//    @Indexed(unique = true)
    private String foodName;
    private String description;
    private Category category;
    private Image image;
    private Status status;
    private int rating;
    private double price;
    private int amount;

    public static Builder builder(){
        return new Food().new Builder();
    }

    public class Builder {

        private Builder(){
        }

        public Builder id(int id){
            Food.this.id = id;
            return this;
        }

        public Builder foodName(String foodName){
            Food.this.foodName = foodName;
            return this;
        }

        public Builder description(String description){
            Food.this.description = description;
            return this;
        }

        public Builder category(Category category){
            Food.this.category = category;
            return this;
        }

        public Builder image(Image image){
            Food.this.image = image;
            return this;
        }

        public Builder status(Status status){
            Food.this.status = status;
            return this;
        }

        public Builder rating(int rating){
            Food.this.rating = rating;
            return this;
        }

        public Builder price(double price){
            Food.this.price = price;
            return this;
        }

        public Builder amount(int amount){
            Food.this.amount = amount;
            return this;
        }

        public Food build(){
            return Food.this;
        }
    }

    private Food() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
