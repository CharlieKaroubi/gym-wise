import AuthLayout from "@/components/AuthLayout"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Button } from "@/components/ui/button"
import { Eye, EyeOff } from "lucide-react"
import { useState } from "react"

export default function SignUp() {
    const[show, setShow] = useState(false)
    const[showConfirm, setShowConfirm] = useState(false)

    return (
        <AuthLayout>
        <h1 className="text-5xl font-bold mb-2 text-center text-orange-500">Ready to Optimize your Gym Routine?</h1>
            <p className="text-muted-foreground mb-6 text-center text-lg mt-5">
            Get started with HypertroFi today
            </p>
        <div className="space-y-4">
                <div>
                    <Label htmlFor="name">Username</Label>
                    <Input id="email" type="email" className="mt-4"/>
                </div>
            <div>
            <Label htmlFor="email">Email</Label>
            <Input id="email" type="email" className="mt-4"/>
            </div>
            <div>
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
            <Input id="password" type={show?"text":"password"} className="mt-2"/>
            </div>
            <div>
            <div className="flex items-center justify-space">
                <Label htmlFor="password">Confirm Password</Label>
                <Button
                    type="button"
                    variant="ghost"
                    size="icon"
                    className="relative text-muted-foreground hover:bg-transparent"
                    onClick={() => setShowConfirm(!showConfirm)}
                >
                    {showConfirm ? <EyeOff className="w-5 h-5" /> : <Eye className="w-5 h-5" />}
                </Button>
            </div>
            <Input id="password" type={showConfirm?"text":"password"} className="mt-2"/>
            </div>
            <Button className="w-full bg-orange-500 hover:bg-orange-600 active:bg-orange-700 mt-3">Sign Up</Button>
        </div>
        </AuthLayout>
    )
}
