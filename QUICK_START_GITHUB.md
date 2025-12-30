# Quick Start Guide - Push to GitHub

## 🚀 Quick Steps (5 Minutes)

### 1. Create GitHub Repository
- Go to https://github.com/new
- Name: `microservices-banking`
- Visibility: Public or Private
- **DO NOT** initialize with README
- Click "Create repository"

### 2. Add Remote and Push

```bash
# Navigate to project directory
cd /home/microservices

# Add all new files
git add README.md CONTRIBUTING.md LICENSE .gitignore .github/

# Commit documentation
git commit -m "docs: add comprehensive project documentation and CI/CD pipeline"

# Add remote (replace YOUR_USERNAME)
git remote add origin git@github.com:YOUR_USERNAME/microservices-banking.git

# Rename branch to main (modern convention)
git branch -M main

# Push to GitHub
git push -u origin main
```

### 3. Verify
Visit: https://github.com/YOUR_USERNAME/microservices-banking

---

## 📝 Before You Push - Security Checklist

Run these commands to check for sensitive data:

```bash
# Check for potential secrets
grep -r "password" --include="*.yml" --include="*.properties" .
grep -r "secret" --include="*.yml" --include="*.properties" .
grep -r "api.key" --include="*.yml" --include="*.properties" .

# Review what will be committed
git status
git diff

# Review commit history
git log --oneline -5
```

---

## 🔑 SSH Setup (One-Time)

```bash
# Generate SSH key
ssh-keygen -t ed25519 -C "sandeepteotia50@gmail.com"

# Start ssh-agent
eval "$(ssh-agent -s)"

# Add key
ssh-add ~/.ssh/id_ed25519

# Copy public key
cat ~/.ssh/id_ed25519.pub
```

Then:
1. Copy the output
2. Go to GitHub.com → Settings → SSH and GPG keys
3. Click "New SSH key"
4. Paste and save

Test: `ssh -T git@github.com`

---

## 🌿 Recommended Branch Strategy

```bash
# Create develop branch
git checkout -b develop
git push -u origin develop

# For new features
git checkout -b feature/feature-name
# ... make changes ...
git push -u origin feature/feature-name
# Create PR on GitHub: feature/feature-name → develop

# For releases
git checkout main
git merge develop
git tag -a v1.0.0 -m "Release v1.0.0"
git push origin main --tags
```

---

## 🔧 Common Commands

```bash
# Check status
git status

# View changes
git diff

# Add files
git add <file>
git add .  # Add all

# Commit
git commit -m "type: description"

# Push
git push

# Pull latest
git pull

# View history
git log --oneline -10

# Create branch
git checkout -b branch-name

# Switch branch
git checkout branch-name

# Merge branch
git merge branch-name

# Delete branch
git branch -d branch-name
```

---

## 📊 Commit Message Types

- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation
- `style`: Formatting
- `refactor`: Code restructuring
- `test`: Adding tests
- `chore`: Maintenance

Example: `feat(accounts): add customer search API`

---

## ⚠️ Troubleshooting

### "Permission denied (publickey)"
→ Set up SSH key (see above)

### "Updates were rejected"
```bash
git pull origin main --rebase
git push origin main
```

### Wrong remote URL
```bash
git remote set-url origin git@github.com:USERNAME/repo.git
```

### Undo last commit (not pushed)
```bash
git reset --soft HEAD~1
```

---

## 📚 Full Documentation
See `.agent/workflows/push-to-github.md` for complete guide

---

**Ready to push? Follow the Quick Steps above! 🚀**
