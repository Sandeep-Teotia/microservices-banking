# Podcast Automation Helper for Windows
#
# Usage: 
#   Download Mode: .\podcast-helper.ps1
#   Fetch Mode:    .\podcast-helper.ps1 -Fetch "Notebook Title" -Source "C:\Path\To\File.md"

param(
    [Parameter(Mandatory=$false)]
    [string]$TasksFile = "\\wsl$\Debian\home\microservices\Notes\java-8,9\podcast-tasks.md",

    [Parameter(Mandatory=$false)]
    [string]$Fetch,

    [Parameter(Mandatory=$false)]
    [string]$Source
)

Write-Host "=== NotebookLM Podcast Helper ===" -ForegroundColor Cyan
Write-Host ""

# --- FETCH MODE ---
if ($Fetch) {
    if (-not $Source) {
        Write-Host "Error: -Source path is required when using -Fetch" -ForegroundColor Red
        exit 1
    }

    Write-Host "Checking status for: $Fetch" -ForegroundColor Yellow
    
    # Convert Windows path to WSL path 
    # E.g. C:\Users\Foo -> /mnt/c/Users/Foo
    $wslSource = $Source.Replace("\", "/").Replace("C:", "/mnt/c").Replace("D:", "/mnt/d")
    
    # Verify WSL command is available
    $checkCmd = "wsl ls /root/.local/bin/podcast-fetch"
    Invoke-Expression $checkCmd | Out-Null
    if ($LASTEXITCODE -ne 0) {
         Write-Host "Error: podcast-fetch not found in WSL." -ForegroundColor Red
         exit 1
    }

    # Run podcast-fetch inside WSL
    $cmd = "wsl /root/.local/bin/podcast-fetch `"$Fetch`" --source `"$wslSource`""
    
    Write-Host "Running in WSL: $cmd" -ForegroundColor Gray
    Invoke-Expression $cmd
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "`n[SUCCESS] Podcast fetched and added to tracking list." -ForegroundColor Green
        Write-Host "Run the script without arguments to download it." -ForegroundColor Yellow
    } else {
        Write-Host "`n[INFO] Not ready yet or failed. See error above." -ForegroundColor Red
    }
    
    exit $LASTEXITCODE
}

# --- DOWNLOAD MODE ---
# Check if file exists
if (-not (Test-Path $TasksFile)) {
    Write-Host "Tasks file not found: $TasksFile" -ForegroundColor Red
    Write-Host "Run podcast-fetch on WSL first to create tasks." -ForegroundColor Yellow
    exit 1
}

# Read the file
$content = Get-Content $TasksFile -Raw

# Parse the table to find pending items
# Match rows that start with "| pending |"
$pattern = '\| pending \| ([^|]+) \| ([^|]+) \| ([^|]+) \| \[([^\]]+)\]\(([^)]+)\) \| \[Download\]\(([^)]+)\) \| ([^|]+) \|'

$matches = [regex]::Matches($content, $pattern)

if ($matches.Count -eq 0) {
    Write-Host "No pending downloads found." -ForegroundColor Green
    Write-Host "All tasks are completed or the file is empty."
    exit 0
}

Write-Host "Found $($matches.Count) pending download(s):`n" -ForegroundColor Yellow

$index = 1
foreach ($match in $matches) {
    $title = $match.Groups[1].Value.Trim()
    $sourceFile = $match.Groups[2].Value.Trim()
    $outputFile = $match.Groups[3].Value.Trim()
    $notebookUrl = $match.Groups[5].Value.Trim()
    $audioUrl = $match.Groups[6].Value.Trim()
    
    Write-Host "[$index] $title" -ForegroundColor White
    Write-Host "    Source: $sourceFile" -ForegroundColor Gray
    Write-Host "    Save to: $outputFile" -ForegroundColor Gray
    Write-Host ""
    $index++
}

Write-Host "Options:" -ForegroundColor Cyan
Write-Host "  Enter number (1-$($matches.Count)) to download that item"
Write-Host "  Enter 'a' to open all download links"
Write-Host "  Enter 'o' to open the tasks file"
Write-Host "  Enter 'q' to quit"
Write-Host ""

$choice = Read-Host "Your choice"

switch ($choice) {
    'q' { exit 0 }
    'o' { 
        Start-Process $TasksFile
        Write-Host "Opened tasks file."
    }
    'a' {
        foreach ($match in $matches) {
            $audioUrl = $match.Groups[6].Value.Trim()
            Start-Process $audioUrl
            Start-Sleep -Milliseconds 500
        }
        Write-Host "`nOpened all download links in browser." -ForegroundColor Green
        Write-Host "Save each file to the 'Output' path shown above." -ForegroundColor Yellow
        Write-Host "`nAfter saving, edit $TasksFile and change 'pending' to 'completed'." -ForegroundColor Cyan
    }
    default {
        $num = [int]$choice - 1
        if ($num -ge 0 -and $num -lt $matches.Count) {
            $match = $matches[$num]
            $title = $match.Groups[1].Value.Trim()
            $audioUrl = $match.Groups[6].Value.Trim()
            $outputFile = $match.Groups[3].Value.Trim()
            
            Write-Host "`nOpening download for: $title" -ForegroundColor Green
            Start-Process $audioUrl
            
            Write-Host "`n>>> SAVE THE FILE AS:" -ForegroundColor Magenta
            Write-Host "    $outputFile" -ForegroundColor White
            Write-Host "`nAfter saving, edit $TasksFile and change 'pending' to 'completed'." -ForegroundColor Cyan
        } else {
            Write-Host "Invalid choice." -ForegroundColor Red
        }
    }
}
