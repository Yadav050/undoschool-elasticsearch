package com.undoschool.coursesearch;

import com.undoschool.coursesearch.document.CourseDocument;
import com.undoschool.coursesearch.dto.SearchRequest;
import com.undoschool.coursesearch.dto.SearchResponse;
import com.undoschool.coursesearch.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
class CourseSearchApplicationTests {

    @Container
    static ElasticsearchContainer elasticsearch = new ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch:8.11.0")
        .withExposedPorts(9200)
        .withEnv("discovery.type", "single-node")
        .withEnv("xpack.security.enabled", "false");

    @DynamicPropertySource
    static void elasticsearchProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.elasticsearch.uris", () -> "http://localhost:" + elasticsearch.getMappedPort(9200));
    }

    @Autowired
    private CourseService courseService;

    @Test
    void contextLoads() {
        assertNotNull(courseService);
    }

    @Test
    void testBasicSearch() {
        SearchRequest request = new SearchRequest();
        request.setQ("math");
        request.setSize(5);

        SearchResponse response = courseService.searchCourses(request);

        assertNotNull(response);
        assertTrue(response.getTotal() > 0);
        assertNotNull(response.getCourses());
        assertTrue(response.getCourses().size() > 0);
    }

    @Test
    void testCategoryFilter() {
        SearchRequest request = new SearchRequest();
        request.setCategory("Science");
        request.setSize(10);

        SearchResponse response = courseService.searchCourses(request);

        assertNotNull(response);
        assertTrue(response.getTotal() > 0);
        response.getCourses().forEach(course -> 
            assertEquals("Science", course.getCategory()));
    }

    @Test
    void testPriceRangeFilter() {
        SearchRequest request = new SearchRequest();
        request.setMinPrice(200.0);
        request.setMaxPrice(400.0);
        request.setSize(10);

        SearchResponse response = courseService.searchCourses(request);

        assertNotNull(response);
        assertTrue(response.getTotal() > 0);
        response.getCourses().forEach(course -> {
            assertTrue(course.getPrice() >= 200.0);
            assertTrue(course.getPrice() <= 400.0);
        });
    }

    @Test
    void testSorting() {
        SearchRequest request = new SearchRequest();
        request.setSort("priceAsc");
        request.setSize(5);

        SearchResponse response = courseService.searchCourses(request);

        assertNotNull(response);
        assertTrue(response.getTotal() > 0);
        
        // Check if prices are in ascending order
        for (int i = 1; i < response.getCourses().size(); i++) {
            assertTrue(response.getCourses().get(i).getPrice() >= 
                      response.getCourses().get(i-1).getPrice());
        }
    }
} 