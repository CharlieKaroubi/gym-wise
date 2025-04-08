import { Link } from 'react-router-dom'
import {Button} from '@/components/ui/button'

export function Navbar() {
    return (
      <header className="w-full p-4 shadow bg-white sticky top-0 z-50">
        <div className="flex items-center justify-between px-4">
          <h1 className="text-2xl font-bold text-orange-500 font-lilita">HypertroFi</h1>
          <nav className="space-x-4">
              <Link to="/signin" className="text-orange-500 hover:text-orange-600">
                <Button className="bg-orange-500 hover:bg-orange-600 cursor-pointer active:bg-orange-700">
                  Sign In
                </Button>
              </Link>
          </nav>
        </div>
      </header>
    )
  }