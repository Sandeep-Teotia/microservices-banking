# Contributing to Microservices Banking Application

First off, thank you for considering contributing to this project! 🎉

## 📋 Table of Contents

- [Code of Conduct](#code-of-conduct)
- [How Can I Contribute?](#how-can-i-contribute)
- [Development Setup](#development-setup)
- [Coding Standards](#coding-standards)
- [Commit Guidelines](#commit-guidelines)
- [Pull Request Process](#pull-request-process)
- [Testing Guidelines](#testing-guidelines)

## 📜 Code of Conduct

- Be respectful and inclusive
- Welcome newcomers and help them learn
- Focus on constructive feedback
- Respect differing viewpoints and experiences

## 🤝 How Can I Contribute?

### Reporting Bugs

Before creating bug reports, please check existing issues. When creating a bug report, include:

- **Clear title and description**
- **Steps to reproduce**
- **Expected vs actual behavior**
- **Environment details** (OS, Java version, etc.)
- **Logs and error messages**

### Suggesting Enhancements

Enhancement suggestions are tracked as GitHub issues. Include:

- **Clear use case**
- **Proposed solution**
- **Alternative solutions considered**
- **Impact on existing functionality**

### Code Contributions

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Write/update tests
5. Update documentation
6. Submit a pull request

## 🛠️ Development Setup

### Prerequisites

```bash
# Install Java 17
sudo apt-get install openjdk-17-jdk

# Install Maven
sudo apt-get install maven

# Install Docker
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh
```

### Local Setup

```bash
# Clone your fork
git clone https://github.com/YOUR_USERNAME/microservices.git
cd microservices

# Add upstream remote
git remote add upstream https://github.com/ORIGINAL_OWNER/microservices.git

# Build the project
mvn clean install
```

## 📝 Coding Standards

### Java Code Style

- Follow [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
- Use meaningful variable and method names
- Keep methods small and focused (< 20 lines ideally)
- Write self-documenting code with clear comments for complex logic
- Use Lombok annotations appropriately

### Code Organization

```java
// Order of class members:
1. Static constants
2. Instance variables
3. Constructors
4. Public methods
5. Protected methods
6. Private methods
7. Inner classes
```

### Example

```java
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    
    // Dependencies
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    
    @Override
    public AccountDto createAccount(CustomerDto customerDto) {
        // Implementation
    }
    
    private void validateCustomer(CustomerDto customerDto) {
        // Helper method
    }
}
```

## 📦 Commit Guidelines

### Commit Message Format

Follow [Conventional Commits](https://www.conventionalcommits.org/):

```
<type>(<scope>): <subject>

<body>

<footer>
```

### Types

- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `style`: Code style changes (formatting, etc.)
- `refactor`: Code refactoring
- `test`: Adding or updating tests
- `chore`: Maintenance tasks
- `perf`: Performance improvements

### Examples

```bash
feat(accounts): add customer search functionality

Implemented search by customer ID and mobile number.
Added pagination support for large result sets.

Closes #123

---

fix(cards): resolve null pointer exception in card validation

Added null check before accessing card details.

Fixes #456

---

docs: update README with Docker Compose instructions
```

### Commit Best Practices

- Use present tense ("add feature" not "added feature")
- Use imperative mood ("move cursor to..." not "moves cursor to...")
- Limit first line to 72 characters
- Reference issues and pull requests
- Keep commits atomic (one logical change per commit)

## 🔄 Pull Request Process

### Before Submitting

- [ ] Code follows project style guidelines
- [ ] All tests pass locally
- [ ] New tests added for new features
- [ ] Documentation updated
- [ ] No merge conflicts with main branch
- [ ] Commit messages follow conventions

### PR Description Template

```markdown
## Description
Brief description of changes

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Breaking change
- [ ] Documentation update

## Testing
Describe testing performed

## Checklist
- [ ] Code follows style guidelines
- [ ] Self-review completed
- [ ] Comments added for complex code
- [ ] Documentation updated
- [ ] No new warnings generated
- [ ] Tests added/updated
- [ ] All tests passing
```

### Review Process

1. Maintainer reviews code
2. Automated tests run
3. Feedback addressed
4. Approved by at least one maintainer
5. Merged to main branch

## 🧪 Testing Guidelines

### Unit Tests

```java
@Test
void testCreateAccount_Success() {
    // Given
    CustomerDto customerDto = createTestCustomer();
    
    // When
    AccountDto result = accountService.createAccount(customerDto);
    
    // Then
    assertNotNull(result);
    assertEquals(customerDto.getName(), result.getCustomerName());
}
```

### Integration Tests

```java
@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void testCreateAccountEndpoint() throws Exception {
        mockMvc.perform(post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson))
                .andExpect(status().isCreated());
    }
}
```

### Test Coverage

- Aim for 80%+ code coverage
- Focus on business logic
- Test edge cases and error conditions
- Use meaningful test names

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=AccountServiceTest

# Run with coverage
mvn test jacoco:report
```

## 🏗️ Branch Naming Convention

```
feature/short-description
bugfix/issue-number-description
hotfix/critical-issue
docs/documentation-update
refactor/code-improvement
```

Examples:
- `feature/customer-search`
- `bugfix/123-null-pointer-fix`
- `docs/api-documentation`

## 🔍 Code Review Checklist

### For Reviewers

- [ ] Code is readable and maintainable
- [ ] Logic is correct and efficient
- [ ] Error handling is appropriate
- [ ] Tests are comprehensive
- [ ] Documentation is clear
- [ ] No security vulnerabilities
- [ ] Performance considerations addressed

### For Contributors

- [ ] Self-reviewed code
- [ ] Removed debug code and comments
- [ ] Updated relevant documentation
- [ ] Added/updated tests
- [ ] Verified all tests pass
- [ ] Checked for code duplication

## 📚 Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Cloud Documentation](https://spring.io/projects/spring-cloud)
- [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
- [Conventional Commits](https://www.conventionalcommits.org/)

## ❓ Questions?

Feel free to open an issue for questions or reach out to maintainers.

---

Thank you for contributing! 🙏
