// React Router
import { Route, Routes } from "react-router-dom";

// Pages
import FindFlight from "./pages/FindFlight/FindFlight";
import Home from "./pages/Home/Home";
import Login from "./pages/Login/Login";
import Register from "./pages/Register/Register";
import UserConfigs from "./pages/UserConfigs/UserConfigs";


const AppRouter = () => {

  return (
    <>
      <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/cadastro" element={<Register />} />
          <Route path="/login" element={<Login />} />
          <Route path="/planos" element={<FindFlight />} />
          <Route path="/configs" element={<UserConfigs />} />
      </Routes>
    </>
  )
}

export default AppRouter;