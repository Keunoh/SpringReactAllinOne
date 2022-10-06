import { BrowserRouter } from 'react-router-dom';
import RootRouter from 'router/RootRouter';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <RootRouter />
      </BrowserRouter>
    </div>
  );
}

export default App;
