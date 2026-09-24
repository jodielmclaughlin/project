import { HashRouter, Route, Routes, } from "react-router-dom";

import './App.css'
import EmployeeListPage from "./pages/EmployeeListPage";
import EditEmployee from "./pages/EditEmployee";
import CreateEmployee from "./pages/CreateEmployee";

function App() {
  return (
    <HashRouter>    
      <div>

        <Routes>
          <Route path="/" element={<EmployeeListPage />} />
          <Route path="/employees/new" element={<CreateEmployee />} />
          <Route path="/employees/:id/edit" element={<EditEmployee />} />
        </Routes>
        
      </div>
    </HashRouter>
  )
}


export default App
