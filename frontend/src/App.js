import logo from './logo.svg';
import StartingPage from './pages/StartingPage';

function App() {
  const handleCreate = (name) => {
    console.log("Create room for", name);
  };

  const handleJoin = (name, code) => {
    console.log(`${name} is joining room ${code}`);
  };
  
  return (
    <div className="App">
      <StartingPage onCreate={handleCreate} onJoin={handleJoin}/>
    </div>
  );
}

export default App;
