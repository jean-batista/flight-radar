// CSS
import "./App.css";

// Components
import Navbar from "./components/Navbar";

// React Router
import { BrowserRouter } from "react-router-dom";

// Router
import AppRouter from "./Routes";

function App() {
  return (
    <>
      <BrowserRouter>
        <Navbar />
        <AppRouter />
      </BrowserRouter>
    </>
  )
}

export default App;
