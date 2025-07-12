package com.fooddelivery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Item name is required")
    @Size(min = 2, max = 100, message = "Item name must be between 2 and 100 characters")
    @Column(name = "item_name")
    private String itemName;
    
    @Column(length = 1000)
    private String description;
    
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    
    @NotBlank(message = "Category is required")
    private String category;
    
    @Column(name = "image_url")
    private String imageUrl;
    
    @Column(name = "is_vegetarian")
    private Boolean isVegetarian;
    
    @Column(name = "is_vegan")
    private Boolean isVegan;
    
    @Column(name = "is_gluten_free")
    private Boolean isGlutenFree;
    
    @Column(name = "spice_level")
    @Enumerated(EnumType.STRING)
    private SpiceLevel spiceLevel;
    
    @Column(name = "preparation_time")
    private Integer preparationTime; // in minutes
    
    @Column(name = "calories")
    private Integer calories;
    
    @Column(name = "ingredients", length = 1000)
    private String ingredients;
    
    @DecimalMin(value = "0.0", message = "Rating cannot be negative")
    @DecimalMax(value = "5.0", message = "Rating cannot exceed 5.0")
    private Double rating;
    
    @Min(value = 0, message = "Review count cannot be negative")
    @Column(name = "review_count")
    private Integer reviewCount;
    
    @Column(name = "is_available")
    private Boolean isAvailable;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;
    
    public enum SpiceLevel {
        MILD, MEDIUM, HOT, EXTRA_HOT
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (isAvailable == null) {
            isAvailable = true;
        }
        if (isVegetarian == null) {
            isVegetarian = false;
        }
        if (isVegan == null) {
            isVegan = false;
        }
        if (isGlutenFree == null) {
            isGlutenFree = false;
        }
        if (rating == null) {
            rating = 0.0;
        }
        if (reviewCount == null) {
            reviewCount = 0;
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Constructors
    public MenuItem() {}
    
    public MenuItem(String itemName, String description, BigDecimal price, String category, Restaurant restaurant) {
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.category = category;
        this.restaurant = restaurant;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    
    public Boolean getIsVegetarian() { return isVegetarian; }
    public void setIsVegetarian(Boolean isVegetarian) { this.isVegetarian = isVegetarian; }
    
    public Boolean getIsVegan() { return isVegan; }
    public void setIsVegan(Boolean isVegan) { this.isVegan = isVegan; }
    
    public Boolean getIsGlutenFree() { return isGlutenFree; }
    public void setIsGlutenFree(Boolean isGlutenFree) { this.isGlutenFree = isGlutenFree; }
    
    public SpiceLevel getSpiceLevel() { return spiceLevel; }
    public void setSpiceLevel(SpiceLevel spiceLevel) { this.spiceLevel = spiceLevel; }
    
    public Integer getPreparationTime() { return preparationTime; }
    public void setPreparationTime(Integer preparationTime) { this.preparationTime = preparationTime; }
    
    public Integer getCalories() { return calories; }
    public void setCalories(Integer calories) { this.calories = calories; }
    
    public String getIngredients() { return ingredients; }
    public void setIngredients(String ingredients) { this.ingredients = ingredients; }
    
    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
    
    public Integer getReviewCount() { return reviewCount; }
    public void setReviewCount(Integer reviewCount) { this.reviewCount = reviewCount; }
    
    public Boolean getIsAvailable() { return isAvailable; }
    public void setIsAvailable(Boolean isAvailable) { this.isAvailable = isAvailable; }
    
    public Restaurant getRestaurant() { return restaurant; }
    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public List<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }
}
