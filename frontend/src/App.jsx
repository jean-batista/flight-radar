// CSS
import "./App.css";

// Components
import Navbar from "./components/Navbar";

// React Router
import { BrowserRouter } from "react-router-dom";

// Router
import AppRouter from "./Routes";

import { AuthProvider } from "./context/AuthContext";

function App() {
  return (
    <>
      <AuthProvider >
        <BrowserRouter>
          <Navbar />
          <AppRouter />
        </BrowserRouter>
      </AuthProvider>
    </>
  )
}

export default App;
