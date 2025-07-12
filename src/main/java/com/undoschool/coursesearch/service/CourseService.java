package com.undoschool.coursesearch.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.undoschool.coursesearch.document.CourseDocument;
import com.undoschool.coursesearch.dto.SearchRequest;
import com.undoschool.coursesearch.dto.SearchResponse;
import com.undoschool.coursesearch.repository.CourseRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final ElasticsearchOperations elasticsearchOperations;
    private final ObjectMapper objectMapper;

    public CourseService(CourseRepository courseRepository, ElasticsearchOperations elasticsearchOperations, ObjectMapper objectMapper) {
        this.courseRepository = courseRepository;
        this.elasticsearchOperations = elasticsearchOperations;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void initializeData() {
        try {
            if (courseRepository.count() > 0) {
                return;
            }
            ClassPathResource resource = new ClassPathResource("sample-courses.json");
            List<CourseDocument> courses = objectMapper.readValue(
                resource.getInputStream(),
                new TypeReference<List<CourseDocument>>() {}
            );
            courseRepository.saveAll(courses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SearchResponse searchCourses(SearchRequest request) {
        Criteria criteria = new Criteria();

        // Full-text search on title and description
        if (request.getQ() != null && !request.getQ().trim().isEmpty()) {
            Criteria textCriteria = new Criteria("title").matches(request.getQ())
                    .or(new Criteria("description").matches(request.getQ()));
            criteria = criteria.and(textCriteria);
        }

        // Age range filter
        if (request.getMinAge() != null) {
            criteria = criteria.and(new Criteria("minAge").greaterThanEqual(request.getMinAge()));
        }
        if (request.getMaxAge() != null) {
            criteria = criteria.and(new Criteria("maxAge").lessThanEqual(request.getMaxAge()));
        }

        // Price range filter
        if (request.getMinPrice() != null) {
            criteria = criteria.and(new Criteria("price").greaterThanEqual(request.getMinPrice()));
        }
        if (request.getMaxPrice() != null) {
            criteria = criteria.and(new Criteria("price").lessThanEqual(request.getMaxPrice()));
        }

        // Category filter
        if (request.getCategory() != null && !request.getCategory().trim().isEmpty()) {
            criteria = criteria.and(new Criteria("category").is(request.getCategory()));
        }

        // Type filter
        if (request.getType() != null && !request.getType().trim().isEmpty()) {
            criteria = criteria.and(new Criteria("type").is(request.getType()));
        }

        // Date filter
        if (request.getStartDate() != null) {
            criteria = criteria.and(new Criteria("nextSessionDate").greaterThanEqual(request.getStartDate()));
        }

        // Sorting
        Sort sort;
        switch (request.getSort()) {
            case "priceAsc":
                sort = Sort.by("price").ascending();
                break;
            case "priceDesc":
                sort = Sort.by("price").descending();
                break;
            default:
                sort = Sort.by("nextSessionDate").ascending();
                break;
        }

        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize(), sort);
        CriteriaQuery query = new CriteriaQuery(criteria, pageRequest);
        SearchHits<CourseDocument> searchHits = elasticsearchOperations.search(query, CourseDocument.class);

        SearchResponse response = new SearchResponse();
        response.setTotal(searchHits.getTotalHits());
        List<SearchResponse.CourseSummary> courses = searchHits.getSearchHits().stream()
            .map(SearchHit::getContent)
            .map(this::mapToCourseSummary)
            .collect(Collectors.toList());
        response.setCourses(courses);
        return response;
    }

    private SearchResponse.CourseSummary mapToCourseSummary(CourseDocument course) {
        SearchResponse.CourseSummary summary = new SearchResponse.CourseSummary();
        summary.setId(course.getId());
        summary.setTitle(course.getTitle());
        summary.setCategory(course.getCategory());
        summary.setPrice(course.getPrice());
        summary.setNextSessionDate(course.getNextSessionDate() != null ? course.getNextSessionDate().format(DateTimeFormatter.ISO_DATE_TIME) : null);
        return summary;
    }
} 