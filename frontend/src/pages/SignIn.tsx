import AuthLayout from "@/components/AuthLayout"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Button } from "@/components/ui/button"
import { Eye, EyeOff } from "lucide-react"
import { useState } from "react"
import { Link } from 'react-router-dom'
import { useNavigate } from "react-router-dom";

export default function SignUp() {
    const[show, setShow] = useState(false)
    const[email, setEmail] = useState("")
    const[password, setPassword] = useState("")
    const[error, setError] = useState("")
    const navigate = useNavigate()

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault()
        setError("")
        try {
            const res = await fetch("http://localhost:8080/auth/login", {
                method: "POST",
                headers: {
                "Content-Type": "application/json",
                },
                body: JSON.stringify({ email, password }),
        });

        if(!res.ok){
            throw new Error("Invalid email or password")
        }
        const data = await res.json()

        localStorage.setItem("token", data.token);
        localStorage.setItem("user", JSON.stringify(data.user)); 
        navigate("/home");   
        } catch (error:any) {
            setError(error.message || "Something went wrong")
        }

    }


    return (
        <AuthLayout>
        <h1 className="text-5xl font-bold mb-2 text-center text-orange-500">Welcome Back</h1>
            <p className="text-muted-foreground mb-6 text-center">
                Please Sign In Below
            </p>
        <div className="space-y-2">
            <Label htmlFor="email">Email</Label>
            <Input id="email" type="email" className="mt-4" onChange={(e)=>setEmail(e.target.value)}/>
            <div className="flex items-center justify-space">
                <Label htmlFor="password">Password</Label>
                <Button
                    type="button"
                    variant="ghost"
                    size="icon"
                    className="relative text-muted-foreground hover:bg-transparent"
                    onClick={() => setShow(!show)}
                >
                    {show ? <EyeOff className="w-5 h-5" /> : <Eye className="w-5 h-5" />}
                </Button>
            </div>
            <Input id="password" type={show?"text":"password"} onChange={(e)=>setPassword(e.target.value)}/>
            
            <Button 
                className="w-full bg-orange-500 hover:bg-orange-600 active:bg-orange-700 mt-6"
                onClick={handleSubmit}
            >
                Sign In
            </Button>
            <p className="text-center text-muted-foreground mt-2">Don't have an Account? 
                <Link to="/signup" className="mx-2 underline underline-offset-1 text-blue-500 hover:text-blue-600 active:text-blue-700">
                    Sign Up
                </Link>
            </p>
        </div>
        </AuthLayout>
    )
}
