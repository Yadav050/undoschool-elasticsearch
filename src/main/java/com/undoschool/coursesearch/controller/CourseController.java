package com.undoschool.coursesearch.controller;

import com.undoschool.coursesearch.dto.SearchRequest;
import com.undoschool.coursesearch.dto.SearchResponse;
import com.undoschool.coursesearch.service.CourseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/search")
    public SearchResponse searchCourses(SearchRequest request) {
        return courseService.searchCourses(request);
    }
} 