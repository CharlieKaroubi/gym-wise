import Home from "@/pages/Home"
import SignUp from "@/pages/SignUp"
import SignIn from "@/pages/SignIn"
import { Routes, Route } from 'react-router-dom'
import Onboarding from "@/pages/Onboarding"
import ExercisesDashboard from "./pages/Exercises"
import SplitsDashboard from "./pages/Splits"
import SplitsPreloader from "./static/SplitsPreloader"

function App() {
  return(
    <Routes>
        <Route path="/" element={<Onboarding />} />
        <Route path="/signin" element={<SignIn />} />
        <Route path="/signup" element={<SignUp />} />
        <Route element={<SplitsPreloader />}>
          <Route path="/home" element={<Home />} />
          <Route path="/exercises" element={<ExercisesDashboard />} />
          <Route path="/splits" element={<SplitsDashboard />} />
        </Route>
    </Routes>
  )
}

export default App
