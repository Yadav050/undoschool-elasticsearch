# Course Search Application with Elasticsearch

A Spring Boot application that provides comprehensive course search functionality using Elasticsearch. This application demonstrates integration with Elasticsearch for advanced search capabilities including filtering, sorting, and pagination.

## Features Implemented

### Core Features ✅
- **Full-text search** on course titles and descriptions
- **Multiple filters** (category, price range, date range)
- **Sorting options** (price ascending/descending, date)
- **Pagination support** for large result sets
- **REST API endpoints** with comprehensive documentation

### Advanced Features ✅
- **Fuzzy search** with typo tolerance
- **Elasticsearch integration** with proper indexing
- **Docker Compose setup** for easy deployment
- **Integration tests** with Testcontainers

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- Docker and Docker Compose

## Quick Start

### 1. Launch Elasticsearch

```bash
# Start Elasticsearch using Docker Compose
docker-compose up -d

# Verify Elasticsearch is running
curl http://localhost:9200
```

You should see a JSON response indicating Elasticsearch is running.

### 2. Build and Run the Spring Boot Application

```bash
# Build the application
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### 3. Populate the Index with Sample Data

The application automatically loads 50 sample courses on startup. You can verify this by checking the application logs or making a search request.

### 4. Test the API Endpoints

#### Basic Search
```bash
curl http://localhost:8080/api/search
```

#### Search with Query
```bash
curl "http://localhost:8080/api/search?q=math"
```

#### Category Filter
```bash
curl "http://localhost:8080/api/search?category=Science"
```

#### Price Range Filter
```bash
curl "http://localhost:8080/api/search?minPrice=200&maxPrice=400"
```

#### Sorting
```bash
curl "http://localhost:8080/api/search?sort=priceAsc"
```

#### Pagination
```bash
curl "http://localhost:8080/api/search?page=0&size=5"
```

### 5. Bonus: Autocomplete and Fuzzy Search Tests

#### Fuzzy Search (Typo Tolerance)
```bash
# Search for "phyics" should match "Physics Workshop"
curl "http://localhost:8080/api/search?q=phyics"
```

#### Combined Filters with Fuzzy Search
```bash
curl "http://localhost:8080/api/search?q=math&category=Science&minPrice=100&maxPrice=500&sort=priceAsc"
```

## API Documentation

### Search Courses

**Endpoint:** `GET /api/search`

**Query Parameters:**
- `q` (optional): Search keyword for title and description
- `minAge` (optional): Minimum age filter
- `maxAge` (optional): Maximum age filter
- `category` (optional): Category filter (e.g., "Math", "Science", "Art")
- `type` (optional): Course type filter ("ONE_TIME", "COURSE", "CLUB")
- `minPrice` (optional): Minimum price filter
- `maxPrice` (optional): Maximum price filter
- `startDate` (optional): Start date filter (ISO-8601 format)
- `sort` (optional): Sort order ("upcoming", "priceAsc", "priceDesc")
- `page` (optional): Page number (default: 0)
- `size` (optional): Page size (default: 10)

**Response:**
```json
{
  "total": 15,
  "courses": [
    {
      "id": "1",
      "title": "Introduction to Mathematics",
      "category": "Math",
      "price": 299.99,
      "nextSessionDate": "2025-01-15T09:00:00Z"
    }
  ]
}
```

### Autocomplete Suggestions

**Endpoint:** `GET /api/search/suggest`

**Query Parameters:**
- `q` (required): Partial course title

**Response:**
```json
[
  "Introduction to Mathematics",
  "Advanced Mathematics",
  "Mathematics for Beginners"
]
```

## Example API Calls

### Basic Search
```bash
curl "http://localhost:8080/api/search?q=math&size=5"
```

### Filter by Category
```bash
curl "http://localhost:8080/api/search?category=Science&size=10"
```

### Price Range Filter
```bash
curl "http://localhost:8080/api/search?minPrice=200&maxPrice=400&sort=priceAsc"
```

### Age Range Filter
```bash
curl "http://localhost:8080/api/search?minAge=10&maxAge=15&size=10"
```

### Date Filter
```bash
curl "http://localhost:8080/api/search?startDate=2025-02-01T00:00:00Z&sort=upcoming"
```

### Multiple Filters
```bash
curl "http://localhost:8080/api/search?category=Technology&type=COURSE&minPrice=300&maxPrice=600&sort=priceDesc"
```

### Autocomplete
```bash
curl "http://localhost:8080/api/search/suggest?q=phy"
```

### Fuzzy Search (Bonus Feature)
```bash
# Search for "dinors" should match "Dinosaurs 101"
curl "http://localhost:8080/api/search?q=dinors"
```

## Project Structure

```
src/
├── main/
│   ├── java/com/undoschool/coursesearch/
│   │   ├── CourseSearchApplication.java
│   │   ├── controller/
│   │   │   └── CourseController.java
│   │   ├── document/
│   │   │   └── CourseDocument.java
│   │   ├── dto/
│   │   │   ├── SearchRequest.java
│   │   │   └── SearchResponse.java
│   │   ├── repository/
│   │   │   └── CourseRepository.java
│   │   └── service/
│   │       └── CourseService.java
│   └── resources/
│       ├── application.yml
│       ├── es-settings.json
│       └── sample-courses.json
└── test/
    └── java/com/undoschool/coursesearch/
        └── CourseSearchApplicationTests.java
```

## Sample Data

The application includes 50 sample courses with the following characteristics:

- **Categories:** Math, Science, Art, Technology, Music, Language Arts, Sports, etc.
- **Types:** ONE_TIME, COURSE, CLUB
- **Age Ranges:** 6-18 years
- **Price Range:** $79.99 - $649.99
- **Session Dates:** January 2025 - September 2025

## Testing

### Run Integration Tests with Testcontainers
```bash
# Run all tests against an ephemeral Elasticsearch instance
mvn test
```

The tests use Testcontainers to spin up a temporary Elasticsearch instance, ensuring isolated testing without affecting your local development environment.

### Manual Testing Examples

1. **Search for math courses:**
   ```bash
   curl "http://localhost:8080/api/search?q=math"
   ```

2. **Find science courses:**
   ```bash
   curl "http://localhost:8080/api/search?category=Science"
   ```

3. **Test fuzzy search with typos:**
   ```bash
   curl "http://localhost:8080/api/search?q=phyics"
   ```

4. **Combined filters and sorting:**
   ```bash
   curl "http://localhost:8080/api/search?category=Science&minPrice=200&maxPrice=500&sort=priceAsc"
   ```

## Configuration

### Elasticsearch Settings
- **Host:** localhost:9200
- **Version:** 8.11.0
- **Security:** Disabled for local development
- **Memory:** 512MB allocated

### Application Settings
- **Port:** 8080
- **Connection Timeout:** 30 seconds
- **Socket Timeout:** 30 seconds

## Troubleshooting

### Elasticsearch Connection Issues
1. Ensure Docker is running
2. Check if Elasticsearch container is up: `docker ps`
3. Verify Elasticsearch is accessible: `curl http://localhost:9200`

### Application Startup Issues
1. Check if port 8080 is available
2. Verify Elasticsearch is running before starting the application
3. Check application logs for detailed error messages

### Data Loading Issues
1. Check if `sample-courses.json` exists in `src/main/resources`
2. Verify JSON format is valid
3. Check application logs for data loading messages

## Performance Considerations

- Uses Elasticsearch filters for efficient querying
- Implements pagination to handle large result sets
- Uses completion suggester for fast autocomplete
- Fuzzy search with configurable fuzziness levels

## Future Enhancements

- Add authentication and authorization
- Implement course enrollment functionality
- Add course ratings and reviews
- Implement advanced analytics and reporting
- Add support for course images and media
- Implement real-time notifications

## License

This project is created for educational purposes as part of an internship assignment. 