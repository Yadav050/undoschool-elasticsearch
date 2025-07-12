# Git Repository Setup Guide

## Step 1: Initialize Git Repository

```bash
# Navigate to your project directory
cd C:\Users\anmol\Desktop\PROJECTS\UndoSchool

# Initialize Git repository
git init

# Add all files
git add .

# Make initial commit
git commit -m "Initial project setup with Spring Boot and Elasticsearch"
```

## Step 2: Create Incremental Commits (as per assignment requirements)

### Commit 1: Project Setup
```bash
git add pom.xml docker-compose.yml README.md .gitignore
git commit -m "Setup: Maven configuration and Docker Compose for Elasticsearch"
```

### Commit 2: Core Application Structure
```bash
git add src/main/java/com/undoschool/coursesearch/CourseSearchApplication.java
git add src/main/java/com/undoschool/coursesearch/document/
git add src/main/java/com/undoschool/coursesearch/repository/
git commit -m "Core: Application structure with entity mapping and repository"
```

### Commit 3: Service Layer
```bash
git add src/main/java/com/undoschool/coursesearch/service/
git commit -m "Service: Business logic implementation for course search"
```

### Commit 4: REST API Implementation
```bash
git add src/main/java/com/undoschool/coursesearch/controller/
git add src/main/java/com/undoschool/coursesearch/dto/
git commit -m "API: REST endpoints with search, filtering, and sorting"
```

### Commit 5: Sample Data and Configuration
```bash
git add src/main/resources/
git commit -m "Data: Sample courses and Elasticsearch configuration"
```

### Commit 6: Testing
```bash
git add src/test/
git commit -m "Tests: Integration tests with Testcontainers"
```

### Commit 7: Documentation
```bash
git add SUBMISSION_CHECKLIST.md PROJECT_SUMMARY.md SUBMISSION_GUIDE.md FINAL_VERIFICATION.md
git commit -m "Docs: Comprehensive documentation and submission guides"
```

## Step 3: Push to Repository

### Option A: GitHub (Recommended)
1. Create a new repository on GitHub
2. Follow GitHub's instructions to push your local repository

### Option B: GitLab
1. Create a new repository on GitLab
2. Follow GitLab's instructions to push your local repository

### Option C: Bitbucket
1. Create a new repository on Bitbucket
2. Follow Bitbucket's instructions to push your local repository

## Step 4: Repository URL
After pushing, you'll have a URL like:
- GitHub: `https://github.com/yourusername/undoschool-elasticsearch`
- GitLab: `https://gitlab.com/yourusername/undoschool-elasticsearch`
- Bitbucket: `https://bitbucket.org/yourusername/undoschool-elasticsearch`

## Step 5: Submission
Include the repository URL in your assignment submission. 