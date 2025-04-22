import { Link, useLocation } from "react-router-dom"
import { Button } from "@/components/ui/button"
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu"
import { useEffect, useState } from "react"
import axiosInstance from "@/api/axiosInstance"

type UserProfile = {
  username: string;
  email: string;
}

export function Navbar() {
  const location = useLocation()

  const[userProfile, setUserProfile] = useState<UserProfile>({
    username: "",
    email: "",
  })

  const loadUserProfile = async () => {
      try {
        const res = await axiosInstance.get<UserProfile>("/users/profile");
        if (res.data) {
          setUserProfile(res.data);
        } else {
          console.error("Invalid response format");
        }
      } catch (err) {
        console.error("Failed to load user profile", err);
      }
  }

  const isOnboardingRoute = location.pathname === "/"
  const isHomeRoute = location.pathname === "/home"
  const isExercisesRoute = location.pathname === "/exercises"
  const isSplitsRoute = location.pathname === "/splits"

  useEffect(() => {
    if (!isOnboardingRoute) {
      loadUserProfile()
    }
  }, [isOnboardingRoute])

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
        { label: "Home", path: "/home" },
        { label: "Splits", path: "/splits" },
      ]
    : isSplitsRoute
    ? [
        { label: "Home", path: "/home" },
        { label: "Exercises", path: "/exercises" },
      ]: [];
  

  return (
    <header className="w-full p-4 shadow bg-white sticky top-0 z-50">
      <div className="flex items-center justify-between px-4">
        {/* Logo */}
        <Link to="/home" className="text-2xl font-bold text-orange-500 font-lilita">
          HypertroFi
        </Link>

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
          {!isOnboardingRoute && (
            <DropdownMenu>
            <DropdownMenuTrigger className="font-medium text-orange-500 hover:text-orange-600 cursor-pointer">
              Profile
            </DropdownMenuTrigger>
            <DropdownMenuContent>
              <DropdownMenuLabel>My Account</DropdownMenuLabel>
              <DropdownMenuSeparator />
              <DropdownMenuItem><span className="font-bold">Username:</span>{userProfile.username}</DropdownMenuItem>
              <DropdownMenuItem><span className="font-bold">Email:</span>{userProfile.email}</DropdownMenuItem>
            </DropdownMenuContent>
          </DropdownMenu>
            )}
        </nav>
      </div>
    </header>
  )
}
