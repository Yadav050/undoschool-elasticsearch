package com.undoschool.coursesearch.dto;

import java.util.List;

public class SearchResponse {
    private Long total;
    private List<CourseSummary> courses;

    public Long getTotal() { return total; }
    public void setTotal(Long total) { this.total = total; }
    public List<CourseSummary> getCourses() { return courses; }
    public void setCourses(List<CourseSummary> courses) { this.courses = courses; }

    public static class CourseSummary {
        private String id;
        private String title;
        private String category;
        private Double price;
        private String nextSessionDate;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }
        public Double getPrice() { return price; }
        public void setPrice(Double price) { this.price = price; }
        public String getNextSessionDate() { return nextSessionDate; }
        public void setNextSessionDate(String nextSessionDate) { this.nextSessionDate = nextSessionDate; }
    }
} 