import Home from "@/pages/Home"
import SignUp from "@/pages/SignUp"
import SignIn from "@/pages/SignIn"
import { Routes, Route } from 'react-router-dom'

function App() {
  return(
    <Routes>
        <Route path="/home" element={<Home />} />
        <Route path="/signin" element={<SignIn />} />
        <Route path="/signup" element={<SignUp />} />
    </Routes>
  )
}

export default App
