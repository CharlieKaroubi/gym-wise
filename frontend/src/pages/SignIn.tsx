import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Checkbox } from "@/components/ui/checkbox"
import { Label } from "@/components/ui/label"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"

export default function SignInPage() {
  return (
    <div className="flex h-screen">
      {/* Left Form Section */}
      <div className="w-full md:w-1/2 flex flex-col justify-center px-10 py-12">
        <div className="max-w-md mx-auto">
          <h1 className="text-3xl font-bold mb-2 text-center text-orange-500">Ready to Optimize your Gym Routine?</h1>
          <p className="text-muted-foreground mb-6 text-center">
            Get started with HypertroFi today
          </p>

          <div className="space-y-4">
            <div>
              <Label htmlFor="name">Full name</Label>
              <Input id="name" placeholder="Jane Doe" className="mt-2"/>
            </div>

            <div>
              <Label htmlFor="email">Email</Label>
              <Input id="email" type="email" placeholder="janedoe@mail.com" className="mt-2"/>
            </div>

            <div className="flex items-center space-x-2">
              <Checkbox id="terms" />
              <Label htmlFor="terms">
                I agree to the <span className="text-blue-500 underline cursor-pointer">Terms & Conditions</span>
              </Label>
            </div>

            <Button className="w-full bg-orange-500 hover:bg-orange-600 cursor-pointer text-white font-semibold">
              Sign up
            </Button>
          </div>
        </div>
      </div>

      {/* Right Image Section */}
      <div className="hidden md:block w-1/2 bg-gray-100">
        <img
          src="/your-image.png"
          alt="Sign up illustration"
          className="object-contain h-full w-full"
        />
      </div>
    </div>
  )
}
