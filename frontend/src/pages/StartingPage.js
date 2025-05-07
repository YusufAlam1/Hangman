import React, { useState } from 'react';
import '../styles/startingPage.css';

function StartingPage({ onCreate, onJoin }) {
  const [name, setName] = useState('');
  const [roomCode, setRoomCode] = useState('');

  return (
    <div className="starting-container">
      <h1 className="title">Hangman Party</h1>

      <input
        type="text"
        placeholder="Enter your name"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />

      <input
        type="text"
        placeholder="Enter room code"
        value={roomCode}
        onChange={(e) => setRoomCode(e.target.value)}
      />

      <div className="button-row">
        <button onClick={() => onJoin(name, roomCode)}>Join</button>
        <button onClick={() => onCreate(name)}>Create</button>
      </div>
    </div>
  );
}

export default StartingPage;