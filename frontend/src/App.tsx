import Home from "@/pages/Home"
import SignUp from "@/pages/SignUp"
import SignIn from "@/pages/SignIn"
import { Routes, Route } from 'react-router-dom'
import Onboarding from "@/pages/Onboarding"
import ExercisesDashboard from "./pages/Exercises"

function App() {
  return(
    <Routes>
        <Route path="/" element={<Onboarding />} />
        <Route path="/home" element={<Home />} />
        <Route path="/signin" element={<SignIn />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/exercises" element={<ExercisesDashboard />} />
    </Routes>
  )
}

export default App
