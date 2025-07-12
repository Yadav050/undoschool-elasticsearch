package com.undoschool.coursesearch.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Document(indexName = "courses")
public class CourseDocument {
    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String title;
    @Field(type = FieldType.Text)
    private String description;
    @Field(type = FieldType.Keyword)
    private String category;
    @Field(type = FieldType.Keyword)
    private String type;
    @Field(type = FieldType.Keyword)
    private String gradeRange;
    @Field(type = FieldType.Integer)
    private Integer minAge;
    @Field(type = FieldType.Integer)
    private Integer maxAge;
    @Field(type = FieldType.Double)
    private Double price;
    @Field(type = FieldType.Date, format = {}, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime nextSessionDate;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getGradeRange() { return gradeRange; }
    public void setGradeRange(String gradeRange) { this.gradeRange = gradeRange; }
    public Integer getMinAge() { return minAge; }
    public void setMinAge(Integer minAge) { this.minAge = minAge; }
    public Integer getMaxAge() { return maxAge; }
    public void setMaxAge(Integer maxAge) { this.maxAge = maxAge; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public LocalDateTime getNextSessionDate() { return nextSessionDate; }
    public void setNextSessionDate(LocalDateTime nextSessionDate) { this.nextSessionDate = nextSessionDate; }
} 