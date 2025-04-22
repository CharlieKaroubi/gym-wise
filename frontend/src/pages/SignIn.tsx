import AuthLayout from "@/components/AuthLayout"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Button } from "@/components/ui/button"
import { Eye, EyeOff } from "lucide-react"
import { useState } from "react"
import { Link } from 'react-router-dom'
import { useNavigate } from "react-router-dom"
import axiosInstance from "@/api/axiosInstance"


export default function SignIn() {

    type LoginResponse = {
        token: string;
        expiresIn: number;
    };

    const[show, setShow] = useState(false)
    const[email, setEmail] = useState("")
    const[password, setPassword] = useState("")
    const[error, setError] = useState("")
    const navigate = useNavigate()

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        setError("");
      
        try {
          const res = await axiosInstance.post<LoginResponse>("/auth/login", {
            email,
            password,
          });
      
          localStorage.setItem("token", res.data.token);
      
          const profileRes = await axiosInstance.get("/users/profile")
          console.log(profileRes.data);
          localStorage.setItem("user", JSON.stringify(profileRes.data));
          navigate("/home");
        } catch (err) {
            const message = (err as any)?.response?.data?.message || "Login failed";
            setError(message);
        }
    };


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
                className="w-full bg-orange-500 cursor-pointer hover:bg-orange-600 active:bg-orange-700 mt-6"
                onClick={(e) => {
                    e.preventDefault();
                    handleSubmit(e as unknown as React.FormEvent<HTMLFormElement>);
                }}
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

