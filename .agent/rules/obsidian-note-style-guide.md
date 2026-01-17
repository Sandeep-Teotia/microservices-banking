---
trigger: always_on
description: Rules for creating interview-ready Obsidian notes from lecture transcripts
---

# Obsidian Notes Style Guide (Rules)

These rules define the persistent guidelines for transforming lecture transcripts into interview-ready Obsidian notes.

## General Structure

- Notes must be **logically structured** and emphasize **reasoning**
- Always capture the **"Why"** (Motivation) before the **"How"** (Implementation)
- Preserve the logical narrative: Problem → Failed Attempts → Solution
- Add YAML frontmatter with specific, content-focused tags
- Date property type should be `DateTime` timestamp
- Include `lectures:` property listing source transcript numbers (e.g., `lectures: [87, 88, 89]`)
- **File naming**: Use sequential numbering prefix: `{N}. {Title}.md` (e.g., `6. Liveness and Readiness Probes.md`)
  - Check existing notes in the folder to determine the next number



## Content Quality

- **Theory BEFORE code**: Explain the concept fully in prose first (what, why, what-if-not), THEN show code
- **Prose-to-code ratio**: More explanation than code
- Each code block should be preceded AND followed by explanatory prose
- **Narrative comments in code**: Comments should tell a story, not just label syntax
- **"What if this rule didn't exist?"**: Describe chaos/bugs that would occur without the restriction
- Use **real-world context** in examples (`orderId`, `customerName` not abstract `x`, `y`)
- Include **1-2 sentence analogies** for abstract concepts
- Add **thread-safety warnings** for workarounds

## Obsidian Features

- Use callouts (`> [!tip]`, `> [!warning]`) for gotchas and concepts
- Use inline highlights with `==text==`
- Use Obsidian callouts (`> [!note]-`) for collapsible sections
- **Do NOT use** `<details>`/`<summary>` HTML tags
- Ensure **bidirectional linking** with related notes (e.g., `[[Java8-Streams]]` ↔ `[[Java8-Lambdas]]`)
- Add searchable tags to notes

## PlantUML Diagrams

- Use `!theme sunlust` exclusively - NO custom colors
- **Do NOT use** `skinparam backgroundColor` or `skinparam defaultFontColor`
- Make diagrams self-explanatory with descriptive labels and meaningful arrow text
- **Technology icons are PREFERRED in ALL diagrams** (architecture, sequence, component, etc.) - they enhance readability and make diagrams more visually informative
  - Use `scale=0.25` for component/deployment diagrams
  - Use `scale=0.15` for sequence diagrams (smaller participant boxes)

### PlantUML Syntax Pitfalls

Common errors to avoid (add new rows as issues are discovered):

| ❌ Don't | ✅ Do Instead | Why |
|----------|---------------|-----|
| `note right:` inside element blocks | `note right of <element>` after defining element | Embedded notes don't render |
| `skinparam backgroundColor` | Rely on `!theme sunlust` | Theme handles colors |
| Custom hex colors (`#FF5733`) | Use theme's built-in palette | Consistency across diagrams |

> [!note]- Example: Correct Note Syntax
> ```plantuml
> ' ✅ Define element first, then add note
> rectangle "My Box" as box
> note right of box
>   This renders properly
> end note
> ```

### PlantUML Reference: Generic Emojis (Unicode Block 26)

| Emoji Name | Unicode | Symbol |
| :--- | :--- | :--- |
| sunny | `<:2600:>` | ☀ |
| cloud | `<:2601:>` | ☁ |
| open_umbrella | `<:2602:>` | ☂ |
| umbrella | `<:2614:>` | ☔ |
| warning | `<:26A0:>` | ⚠ |
| zap | `<:26A1:>` | ⚡ |
| check_mark | `<:2714:>` | ✔ |
| cross_mark | `<:2716:>` | ✖ |
| arrow_right | `<:27A1:>` | ➡ |
| gear | `<:2699:>` | ⚙ |

*(Use `<:XXXX:>` syntax for maximum compatibility)*

> [!info] Complete Emoji Reference
> For the **full list of 80+ emojis** (Unicode Block 26 & 27), see `Notes/AI_PlantUML_Emoji_Guide.md`.

### PlantUML Reference: Tech Icons

> [!warning] Absolute Paths Required
> PlantUML requires **full absolute file paths** for icons. Relative paths like `Notes/assets/icons/...` will show "Cannot decode" errors.

**Recommended scales:**
- `scale=0.25` → Component/deployment diagrams
- `scale=0.15` → Sequence diagrams (smaller participant boxes)

**Usage:** Copy icon definitions from `Notes/PlantUML Icons Guide.md` which contains the correct absolute paths.

Example:
```plantuml
' Copy these from PlantUML Icons Guide (with full absolute paths)
!define SPRING <img:C:/Users/.../Notes/assets/icons/spring.png{scale=0.15}>
!define RABBITMQ <img:C:/Users/.../Notes/assets/icons/rabbitmq.png{scale=0.15}>

participant "SPRING Config Server" as config
participant "RABBITMQ Message Broker" as mq
```


> [!info] Complete Icon Reference
> For the **full list of available icons** (databases, tools, security, etc.), see `Notes/PlantUML Icons Guide.md`.

## Animated SVG

- Export as SVG into `Notes/Notes/assets/` folder
- Link via Obsidian wikilink: `![[assets/<file>.svg]]`
- Use **modern design** (glassmorphism, clean typography)
- Include **slow, infinite animations** to explain concepts step by step
- Add **comments** inside SVG for gotchas or reasoning

## Rule of Thumb: Diagram Choice

- **PlantUML**: For explaining concepts
- **Animated SVG**: For explaining processes

## Guardrails

- Always read the **entire transcript** thoroughly before creating notes
- Notes should only be generated after the complete transcript has been fully reviewed from start to finish
- Focus on weaving in rationale and making theoretical sections smoother and intuitive
