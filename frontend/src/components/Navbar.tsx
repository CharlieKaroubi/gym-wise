import { Link, useLocation } from "react-router-dom"
import { Button } from "@/components/ui/button"
import { isAuthenticated } from "@/static/Auth"

export function Navbar() {
  const location = useLocation()

  const isOnboardingRoute = location.pathname === "/"
  const isHomeRoute = location.pathname === "/home"
  const isExercisesRoute = location.pathname === "/exercises"
  const isSplitsRoute = location.pathname === "/splits"

  const navItems = isHomeRoute
    ? [
        { label: "Exercises", path: "/exercises" },
        { label: "Splits", path: "/splits" },
      ]
    : 
    isOnboardingRoute
    ? [
        { label: "Sign In", path: "/signin" },
      ]
    : isExercisesRoute
    ? [
        { label: "Profile", path: "/home" },
        { label: "Splits", path: "/splits" },
      ]
    : isSplitsRoute
    ? [
        { label: "Profile", path: "/home" },
        { label: "Exercises", path: "/exercises" },
      ]: [];
  

  return (
    <header className="w-full p-4 shadow bg-white sticky top-0 z-50">
      <div className="flex items-center justify-between px-4">
        {/* Logo */}
        {isAuthenticated() ? (
        <Link to="/home" className="text-2xl font-bold text-orange-500 font-lilita">
          HypertroFi
        </Link>
        ) : (
          <h1 className="text-2xl font-bold text-orange-500 font-lilita">HypertroFi</h1>
        )}

        {/* Right Side Navigation */}
        <nav className="flex items-center space-x-6">
          {navItems.map(({ label, path }) => (
            <Link
              key={path}
              to={path}
              className={`font-medium ${
                location.pathname === path
                  ? "text-orange-600"
                  : "text-orange-500 hover:text-orange-600"
              }`}
            >
              {label}
            </Link>
          ))}
        </nav>
      </div>
    </header>
  )
}
