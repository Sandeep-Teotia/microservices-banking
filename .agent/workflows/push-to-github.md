---
description: Push code to GitHub repository following industry best practices
---

# Pushing Code to GitHub Repository - Industry Best Practices

This workflow guides you through creating a GitHub repository and pushing your microservices project following industry standards.

## Prerequisites Checklist

- [ ] Git is installed and configured
- [ ] GitHub account is created
- [ ] SSH key is set up (recommended) OR personal access token is ready
- [ ] Code is tested and working locally
- [ ] Sensitive data is not in the repository

## Step 1: Verify Git Configuration

Check your Git identity:

```bash
git config user.name
git config user.email
```

If not set, configure them:

```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

**Current Configuration:**
- Name: sandeep-teotia
- Email: sandeepteotia50@gmail.com

## Step 2: Set Up SSH Key (Recommended for Security)

### Check if SSH key exists:

```bash
ls -la ~/.ssh
```

### If no SSH key exists, create one:

```bash
ssh-keygen -t ed25519 -C "sandeepteotia50@gmail.com"
# Press Enter to accept default location
# Enter a passphrase (recommended) or leave empty
```

### Add SSH key to ssh-agent:

```bash
eval "$(ssh-agent -s)"
ssh-add ~/.ssh/id_ed25519
```

### Copy SSH public key:

```bash
cat ~/.ssh/id_ed25519.pub
```

### Add to GitHub:
1. Go to GitHub.com → Settings → SSH and GPG keys
2. Click "New SSH key"
3. Paste your public key
4. Click "Add SSH key"

### Test SSH connection:

```bash
ssh -T git@github.com
```

## Step 3: Create GitHub Repository

### Option A: Using GitHub Web Interface (Recommended for first-time)

1. Go to https://github.com/new
2. **Repository name**: `microservices-banking` (or your preferred name)
3. **Description**: "Spring Boot microservices banking application with Spring Cloud Config"
4. **Visibility**: 
   - ✅ **Public** (if you want to showcase your work)
   - ⚠️ **Private** (if it contains sensitive information)
5. **DO NOT** initialize with README, .gitignore, or license (we already have these)
6. Click "Create repository"

### Option B: Using GitHub CLI (gh)

```bash
# Install GitHub CLI first
# For Debian/Ubuntu:
curl -fsSL https://cli.github.com/packages/githubcli-archive-keyring.gpg | sudo dd of=/usr/share/keyrings/githubcli-archive-keyring.gpg
echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/githubcli-archive-keyring.gpg] https://cli.github.com/packages stable main" | sudo tee /etc/apt/sources.list.d/github-cli.list > /dev/null
sudo apt update
sudo apt install gh

# Authenticate
gh auth login

# Create repository
gh repo create microservices-banking --public --source=. --remote=origin --description="Spring Boot microservices banking application"
```

## Step 4: Prepare Your Local Repository

### Review changes:

```bash
git status
```

### Add new files:

```bash
git add README.md CONTRIBUTING.md LICENSE .gitignore
```

### Commit the documentation:

```bash
git commit -m "docs: add comprehensive project documentation

- Add detailed README with architecture and setup instructions
- Add CONTRIBUTING.md with coding standards and guidelines
- Add MIT LICENSE
- Enhance .gitignore with comprehensive patterns for Java/Spring Boot"
```

### Review commit history:

```bash
git log --oneline -5
```

## Step 5: Connect to Remote Repository

### Add remote (replace USERNAME with your GitHub username):

**Using SSH (Recommended):**
```bash
git remote add origin git@github.com:USERNAME/microservices-banking.git
```

**Using HTTPS (Alternative):**
```bash
git remote add origin https://github.com/USERNAME/microservices-banking.git
```

### Verify remote:

```bash
git remote -v
```

### If you need to change the remote URL:

```bash
git remote set-url origin git@github.com:USERNAME/microservices-banking.git
```

## Step 6: Push to GitHub

### Push to main branch:

```bash
# If your branch is named 'master', rename it to 'main' (modern convention)
git branch -M main

# Push with upstream tracking
git push -u origin main
```

### Verify on GitHub:
Visit https://github.com/USERNAME/microservices-banking

## Step 7: Set Up Branch Protection (Industry Best Practice)

### On GitHub:
1. Go to repository → Settings → Branches
2. Click "Add rule"
3. Branch name pattern: `main`
4. Enable:
   - ✅ Require pull request reviews before merging
   - ✅ Require status checks to pass before merging
   - ✅ Require branches to be up to date before merging
   - ✅ Include administrators (optional)
5. Save changes

## Step 8: Create Development Workflow

### Create a development branch:

```bash
git checkout -b develop
git push -u origin develop
```

### Set develop as default branch (optional):
1. Go to repository → Settings → Branches
2. Change default branch to `develop`

### Branch Strategy (GitFlow):

```
main (production-ready code)
  ↑
develop (integration branch)
  ↑
feature/* (new features)
bugfix/* (bug fixes)
hotfix/* (urgent production fixes)
```

## Step 9: Add Repository Topics and Details

### On GitHub repository page:
1. Click ⚙️ (settings icon) next to "About"
2. Add topics:
   - `spring-boot`
   - `microservices`
   - `spring-cloud`
   - `java`
   - `docker`
   - `maven`
   - `spring-cloud-config`
   - `banking-application`
3. Add website URL (if you have deployed version)
4. Save changes

## Step 10: Set Up GitHub Actions (CI/CD)

Create `.github/workflows/maven.yml`:

```yaml
name: Java CI with Maven

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main, develop ]

jobs:
  build:
    runs-on: ubuntu-latest
    
    steps:
    - uses: actions/checkout@v3
    
    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'temurin'
        cache: maven
    
    - name: Build with Maven
      run: mvn -B package --file pom.xml
    
    - name: Run tests
      run: mvn test
```

## Best Practices Summary

### ✅ DO:
- Write meaningful commit messages
- Keep commits atomic (one logical change)
- Use branches for features/fixes
- Review code before pushing
- Keep sensitive data out of repository
- Update documentation regularly
- Use .gitignore properly
- Tag releases semantically (v1.0.0, v1.1.0)

### ❌ DON'T:
- Commit sensitive data (passwords, API keys)
- Push directly to main/master
- Commit large binary files
- Use vague commit messages ("fix", "update")
- Commit commented-out code
- Ignore merge conflicts
- Force push to shared branches

## Ongoing Workflow

### Daily Development:

```bash
# 1. Start new feature
git checkout develop
git pull origin develop
git checkout -b feature/new-feature

# 2. Make changes and commit
git add .
git commit -m "feat: add new feature description"

# 3. Push feature branch
git push -u origin feature/new-feature

# 4. Create Pull Request on GitHub
# 5. After review and approval, merge to develop
# 6. Delete feature branch
git branch -d feature/new-feature
git push origin --delete feature/new-feature
```

### Creating a Release:

```bash
# 1. Merge develop to main
git checkout main
git merge develop

# 2. Tag the release
git tag -a v1.0.0 -m "Release version 1.0.0"

# 3. Push with tags
git push origin main --tags
```

## Troubleshooting

### Issue: "Permission denied (publickey)"
**Solution**: Set up SSH key properly (see Step 2)

### Issue: "Updates were rejected because the remote contains work"
**Solution**: 
```bash
git pull origin main --rebase
git push origin main
```

### Issue: "Large files detected"
**Solution**: Use Git LFS or remove large files
```bash
git rm --cached large-file
git commit --amend
```

### Issue: Accidentally committed sensitive data
**Solution**: 
```bash
# Remove from history (use with caution)
git filter-branch --force --index-filter \
  "git rm --cached --ignore-unmatch path/to/sensitive/file" \
  --prune-empty --tag-name-filter cat -- --all

# Force push (only if repository is private and you're the only user)
git push origin --force --all
```

## Additional Resources

- [GitHub Docs](https://docs.github.com)
- [Git Best Practices](https://git-scm.com/book/en/v2)
- [Conventional Commits](https://www.conventionalcommits.org/)
- [GitFlow Workflow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow)
- [Semantic Versioning](https://semver.org/)

---

**Remember**: Your repository is your portfolio. Keep it clean, well-documented, and professional! 🚀
