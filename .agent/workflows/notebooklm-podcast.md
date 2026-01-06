---
description: Generate Hinglish podcasts from local notes using NotebookLM with Windows-side download
---
# NotebookLM Podcast Generation

Generate audio podcasts from your markdown notes.
Split workflow: WSL handles generation, Windows handles status check & download.

## Prerequisites
- NotebookLM MCP authenticated (in WSL)
- Fresh cookies in `/home/microservices/cookies.txt`

## 1. Start Generation (WSL)
```bash
podcast-gen "Notes/path/to/note.md" --lang hi
```

## 2. Check Status & Download (Windows)

Navigate to `Notes/java-8,9/Podcast-Automation/` in PowerShell:

```powershell
.\podcast-helper.ps1 -Fetch "Notebook Title" -Source "C:\Path\To\Note.md"
```

To download ready files:
```powershell
.\podcast-helper.ps1
```

To clean up old notebooks (after downloading):
```powershell
.\podcast-helper.ps1 -Cleanup
```

## File Locations

| Item | Path |
|------|------|
| Windows Scripts | `Notes/java-8,9/Podcast-Automation/` |
| Tasks Table | `Notes/java-8,9/Podcast-Automation/podcast-tasks.md` |
| WSL Scripts | `/home/microservices/.podcast-tools/` |
