---
description: Transform a lecture transcript into an interview-ready Obsidian note with infographics
---

# Transform Lecture Transcript into Obsidian Notes

This workflow uses the **obsidian-notes-style** rule file to transform lecture transcripts into comprehensive, interview-ready Obsidian notes, then reviews and enhances with infographics.

> [!note] Path Context
> This workflow operates from `/home/microservices/`. The `Notes/` folder is a symlink to the Obsidian vault, so paths like `Notes/.agent/` and `Notes/Notes/` are correct.

---

## Step 1: Get the Rule Files

Read the rule files to understand the formatting guidelines:

```
View file: Notes/.agent/rules/obsidian-notes-style.md
View file: Notes/.agent/rules/infographic-style-guide.md
```

---

## Step 2: Analyze the Transcript

1. **Identify the transcript file** - The user should provide the path to the lecture transcript
2. **Extract key information**:
   - Main topic/title
   - Key concepts and terminology
   - Code examples
   - Diagrams/flowcharts mentioned
   - Interview-relevant points

---

## Step 2.5: Segregation Analysis (REQUIRED for Multiple Transcripts)

When processing **multiple transcript files** (e.g., a folder with lectures 121-125), you MUST analyze whether they should become **one note or multiple notes**.

### 2.5.1 Present a Segregation Decision Table

Create a table like this for the user:

```markdown
### Transcript Analysis - Segregation Decision

| Lecture | Topic | Standalone? | Reasoning |
|---------|-------|-------------|-----------|
| 121 | [Topic name] | ✅ Yes / ❌ No | [Brief reason] |
| 122 | [Topic name] | ✅ Yes / ❌ No | [Brief reason] |
| ... | ... | ... | ... |

**Decision**: Create **N note(s)** because [reasoning].

**Proposed Note Structure**:
1. `{N}. {Title}.md` - Covers lectures X, Y, Z
2. `{N+1}. {Title}.md` - Covers lectures A, B (if applicable)
```

### 2.5.2 When to Create MULTIPLE Notes

Create **separate notes** when transcripts cover:

| Scenario | Example | Action |
|----------|---------|--------|
| **Different tools/technologies** | Eureka Server + OpenFeign Client | 2 notes |
| **Different patterns** | Service Discovery + Circuit Breaker | 2 notes |
| **Independent implementations** | Config Server Setup + MySQL Docker | 2 notes |
| **Very long single topic** | 10+ lectures on one concept | Split by subtopic |
| **Unrelated sections** | Security + Monitoring | 2 notes |

### 2.5.3 When to Create a SINGLE Note

Create **one comprehensive note** when transcripts cover:

| Scenario | Example | Action |
|----------|---------|--------|
| **Single cohesive narrative** | Problem → Solution → Implementation | 1 note |
| **Same tool/pattern** | API Gateway intro + architecture + setup | 1 note |
| **Progressive depth** | Basics → Deep dive → Hands-on | 1 note |
| **Tightly coupled concepts** | Predicates + Filters (both part of Gateway) | 1 note |

### 2.5.4 Present Decision and Proceed

After analyzing, **present the decision briefly** and proceed:

```
📋 **Segregation Analysis**: Based on the transcript content, I will create [N] note(s):
1. [Note 1 title] - covering [lectures X, Y, Z]
2. [Note 2 title] - covering [lectures A, B] (if applicable)

Proceeding with note creation...
```

**No user confirmation required** - proceed directly to Step 3 after presenting the decision.

---


## Step 3: Create the Note

Following the rules in `Notes/.agent/rules/obsidian-notes-style.md`:

1. **Create YAML frontmatter** with proper metadata
2. **Add PlantUML diagram** using the emoji/icon guidelines
3. **Structure content** with:
   - Abstract overview
   - Sectioned content with emojis
   - Code blocks with proper syntax highlighting
   - Interview callouts
   - Related notes links

---

## Step 4: Save the Note

Save to the appropriate folder:
- `Notes/Notes/Microservices/` for microservices-related content
- `Notes/Notes/Java 8/` for Java 8 related content
- `Notes/Notes/Java 9/` for Java 9 related content
- `Notes/Notes/DevOps/` for DevOps related content
- Use numbered prefix if part of a series (e.g., `7. Docker Compose.md`)

---

## Step 5: Infographic Generation

After note creation, identify sections that need visual representation (comparisons, flows, architecture, gotchas).

### Core Requirements

| Requirement | Description |
|-------------|-------------|
| **White Background** | Always use `#FFFFFF` - no dark backgrounds |
| **Smart Space Usage** | No oversized headings - maximize content area |
| **3D Icons** | Use smart 3D icons to explain concepts visually |
| **High Resolution** | Generate high quality so pixels remain sharp on zoom |
| **Capture Gotchas** | Highlight pitfalls with warning styling |
| **Config Snippets** | Include relevant configuration examples in visuals |

### Save & Embed

1. Copy generated image to: `Notes/Notes/assets/infographics/{name}.png`
2. Embed in note: `![[Notes/assets/infographics/{name}.png|900]]`
3. Add brief `> [!note]` callout explaining the infographic

---


## Step 6: Mark Transcript as Completed

After notes and infographics are created, rename only the **processed transcript files** (not the folder) by appending `-completed`.

```powershell
# Rename processed transcript files
$folder = "C:\full\path\to\Section X- Topic Name"
Get-ChildItem -LiteralPath $folder -Filter "*.md" | ForEach-Object {
    $newName = $_.BaseName + "-completed" + $_.Extension
    Rename-Item -LiteralPath $_.FullName -NewName $newName
}
```

---


## Step 7: Generate Podcast (Optional)

After the note and infographics are complete, offer to generate a podcast:

```
Would you like me to generate a NotebookLM podcast for this note?
Use: /generate-podcast
```

---

## Summary

This workflow produces:

1. ✅ **Segregation analysis** for multi-transcript scenarios (user confirmation before writing)
2. ✅ Interview-ready Obsidian note(s) with proper structure
3. ✅ PlantUML diagrams for technical concepts
4. ✅ High-quality infographics with white backgrounds
5. ✅ Visual representation of gotchas and best practices
6. ✅ Transcript marked as completed
7. ✅ Optional audio podcast for learning on-the-go