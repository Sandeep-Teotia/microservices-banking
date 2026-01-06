# NotebookLM Podcast Workflow

Generate Hinglish audio podcasts from your markdown notes.
Uses **Ephemeral Notebooks**: Generate -> Download -> Auto-Delete.

## Architecture

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                              WORKFLOW OVERVIEW                               │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                              │
│   WSL/Linux Side                          Windows Side                       │
│   ─────────────                           ────────────                       │
│   1. podcast-gen                                                             │
│      └─> Creates Temp Notebook                                               │
│      └─> Starts generation                                                   │
│                                                                              │
│   2. [WAIT 3-5 MIN]                       3. .\podcast-helper.ps1 -Fetch     │
│                                              └─> Calls podcast-fetch (WSL)   │
│                                              └─> Updates Table + Note Embed  │
│                                                                              │
│                                           4. .\podcast-helper.ps1            │
│                                              └─> Downloads Files             │
│                                              └─> User marks 'completed'      │
│                                                                              │
│                                           5. .\podcast-helper.ps1 -Cleanup   │
│                                              └─> Calls podcast-cleanup (WSL) │
│                                              └─> Deletes 'completed' NBs     │
└─────────────────────────────────────────────────────────────────────────────┘
```

## Step 1: Start Generation (WSL)
```bash
podcast-gen "Notes/path/to/note.md" --lang hi
```
Creates a *temporary* notebook for this specific file.

## Step 2: Check & Fetch (Windows)
```powershell
.\podcast-helper.ps1 -Fetch "Notebook Title" -Source "C:\Path\To\Note.md"
```
Checks if audio is ready. Adds to `podcast-tasks.md` with status `pending`.

## Step 3: Download (Windows)
```powershell
.\podcast-helper.ps1
```
Downloads actual files to your drive.
**Important:** After saving, verify the status in `podcast-tasks.md` is updated to `completed`.

## Step 4: Cleanup (Windows)
```powershell
.\podcast-helper.ps1 -Cleanup
```
This reads `podcast-tasks.md`. Any task marked `completed` will have its corresponding notebook **deleted from Google Servers**.
This keeps your dashboard clean!

---

## File Locations

| Item | Path |
|------|------|
| Tasks Tracking | `/home/microservices/Notes/java-8,9/podcast-tasks.md` |
| PowerShell Script | `Notes/java-8,9/podcast-helper.ps1` |
| Cookies | `/home/microservices/cookies.txt` |

## Tracking Table

Located at `Notes/java-8,9/podcast-tasks.md`.

| Status | Title | Source File | ... | Notebook ID |
|--------|-------|-------------|-----|-------------|
| completed | Note 1 | ... | ... | abc-123 |
| deleted | Note 2 | ... | ... | def-456 |

---

## Troubleshooting

- **Auth Expired**: Run `notebooklm-mcp-auth --file cookies.txt` in WSL.
- **Cleanup Fails**: Ensure the Status column says exactly `completed` (lowercase).
