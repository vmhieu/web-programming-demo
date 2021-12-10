import Home from "./main-component/home";
import { BrowserRouter as Router, Route, Routes  ,useNavigate } from 'react-router-dom'
import Login from "./main-component/login";
import { useEffect } from "react";
import { useSelector } from 'react-redux'


function App() {
  
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path='/' element={<Login />} />
          <Route path="/home" element={<Home />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
