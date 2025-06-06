##### <i>**This document outlines the intended design. Actual implementation may vary or may not be listed as of yet.</i>

# Real-Time Hangman Web App – Project Plan

A full-stack web app that taking the boring classroom whiteboard hangman game into a real-time, multiplayer experience with customizable lobbies, responsive design, and live WebSocket synchronization.

---

## 1. User Flow

### 1.1 Starting Page
- Enter player name
- Enter room code or create a new room
- Choose avatar
- UI design should be simple and intuitive
- (Tip: Consider wireframing this screen in Figma)

### 1.2 Room Setup (Host Options)
- Set number of players
- Set time limit per guess (in seconds)
- Select number of rounds (1–10)
- Choose game mode (Classic or Race)
- Toggle hints (on/off)
- Toggle custom word input (on/off)
- (Note: Add a profanity filter if public rooms are supported)

---

## 2. Lobby System
- Room code and invite link are displayed
- Players and avatars are shown in a waiting list (avatars will resemble stickmen) >>o
- "Start Game" button is only enabled for the host
- (Optional: allow host transfer if needed)

---

## 3. Game Mechanics

### 3.1 Host Word Selection
- Host selects the word
- Optional: category hint
- (Consider: auto-select a random word if host is idle for too long)

### 3.2 Core Gameplay Loop
- Players take turns guessing letters
- Correct letter:
  - Revealed in all positions
  - Awards points (see scoring)
- Incorrect letter:
  - Causes the man to slip on the bar
  - Optionally shows a "guesses left" indicator (color-coded)
- Option to guess the full word:
  - If correct: ends round
  - If incorrect: counts as 1 wrong guess
- If word is guessed: trigger a “hangman escape” animation

### 3.3 Scoring System
- Players earn points based on correctness and speed
- Example formula:
  - 50 points per correct letter × a time multiplier (e.g., 0.97 for 0.97s response)
  - Multiples of same letter (like 3 L's) multiply base points
- Full word guess:
  - All players earn bonus points
- Deduction logic:
  - Still undecided if wrong guesses or “final life” results in point loss

### 3.4 Power-Ups (Optional Feature – Beta Later)
- Some letters may trigger power-ups or traps:
  - Extra life
  - Reveal a random letter
  - Loss of life (penalty)
- These may be:
  - Random
  - Manually assigned by host
- Note: Full word guesses bypass power-up triggers to avoid logic bugs

### 3.5 Post-Round Summary
- Show:
  - The correct word
  - Who guessed which letters
  - Number of incorrect guesses
  - Live leaderboard (inspired by Skribbl.io)

---

## 4. Game Modes

### 4.1 Classic Mode
- Turn-based word guessing
- Shared word across players

### 4.2 Race Mode
- Players receive a preset batch of X words
- Each player solves independently
- If a player fails (hangman dies), they are eliminated
- Winner is:
  - First to solve all words within time
  - Or the player with the most words solved while still alive

---

## 5. Visual Design

- Character is hanging from a **pull-up bar**, not a noose
- Bar cracks with each wrong guess
- PG-friendly graphics and animations
- Optional sound effects and motion
- (Consider: colorblind-safe palette and keyboard accessibility)

---

## 6. Real-Time Infrastructure (WebSockets)

- Use **Socket.io** for real-time multiplayer features:
  - Room creation and joining
  - Game state synchronization (guesses, scores, word progress)
  - Optional in-game chat
  - Host handoff logic
  - Reconnection support (if user disconnects)

  Event Naming
  join_room, start_game, letter_guess, word_guess, disconnect, host_transfer, etc.

---

## 7. Room Metadata

- Every room should track:
  - Room code (unique)
  - Host
  - Players and their avatars
  - Game mode and round settings
  - Custom word setting
- Store this in:
  - Memory (for simplicity)
  - Or a lightweight backend database
- (Optional: store logs or analytics for replays/stats)

---

## 8. UX Inspiration

- Take inspiration from different web games ex...
<ul>
<li>Skribbl.io</li>
<li>Gartic-Phone</li>
<li>CodeNames</li>
<li>SpyFall</li>
<li>Other Hangman Games</li>
</ul>

---

## 9. Technical Architechture
- Add a “Technical Architecture” section with bullet points like:
- Frontend: React (w/ socket.io-client)
- Backend: Node.js with Express + Socket.io server
- State management: Context API or Zustand
- Communication: JSON-based messages over WebSocket
- Deployment: Vercel (frontend), Render / Railway (backend)

--- 

## 9. Technical Notes

- **Responsiveness**: prioritize mobile-first design (most users will be on phones)
- **Accessibility** (Future Work):
  - Colorblind-safe visuals
  - Screen reader support
  - Keyboard navigation

---

## Status: Planning & Prototyping
- MVP focus: Classic Mode with turn-based letter guessing and room creation
- Future milestones:
  - Add scoring
  - Polish UI
  - Implement power-ups
  - Expand to Race Mode