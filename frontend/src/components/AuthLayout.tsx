import React from "react"
import logo from "@/assets/logo.svg"

export default function AuthLayout({ children }: { children: React.ReactNode }) {
  return (
    <div className="flex h-screen">
      {/* Left Section with Form */}
      <div className="w-full md:w-1/2 flex flex-col justify-center px-10 py-12">
      <img 
        src={logo} 
        className="absolute top-4 left-4 w-10 h-auto" 
        alt="logo"
      />
        <div className="max-w-md mx-auto">
          {children}
        </div>
      </div>

      {/* Right Section with Image */}
      <div className="hidden md:flex flex-col block w-1/2 bg-gray-100 font-logo items-center justify-center">
        <p className="font-logo text-8xl text-orange-500 mb-15">
          HypertroFi
        </p>
      </div>
    </div>
  )
}
