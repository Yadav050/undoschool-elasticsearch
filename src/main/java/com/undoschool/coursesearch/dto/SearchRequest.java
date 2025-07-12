package com.undoschool.coursesearch.dto;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

public class SearchRequest {
    private String q;
    private Integer minAge;
    private Integer maxAge;
    private String category;
    private String type;
    private Double minPrice;
    private Double maxPrice;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDate;
    private String sort = "upcoming";
    private Integer page = 0;
    private Integer size = 10;

    public String getQ() { return q; }
    public void setQ(String q) { this.q = q; }
    public Integer getMinAge() { return minAge; }
    public void setMinAge(Integer minAge) { this.minAge = minAge; }
    public Integer getMaxAge() { return maxAge; }
    public void setMaxAge(Integer maxAge) { this.maxAge = maxAge; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Double getMinPrice() { return minPrice; }
    public void setMinPrice(Double minPrice) { this.minPrice = minPrice; }
    public Double getMaxPrice() { return maxPrice; }
    public void setMaxPrice(Double maxPrice) { this.maxPrice = maxPrice; }
    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
    public String getSort() { return sort; }
    public void setSort(String sort) { this.sort = sort; }
    public Integer getPage() { return page; }
    public void setPage(Integer page) { this.page = page; }
    public Integer getSize() { return size; }
    public void setSize(Integer size) { this.size = size; }
} 