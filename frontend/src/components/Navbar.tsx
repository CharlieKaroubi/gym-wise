export function Navbar() {
    return (
      <header className="w-full p-4 shadow bg-white sticky top-0 z-50">
        <div className="max-w-7xl mx-auto flex items-center justify-between">
          <h1 className="text-2xl font-bold text-orange-500 font-lilita">HypertroFi</h1>
          <nav className="space-x-4">
            <a href="#" className="text-muted-foreground hover:text-primary">Home</a>
            <a href="#" className="text-muted-foreground hover:text-primary">Exercises</a>
            <a href="#" className="text-muted-foreground hover:text-primary">Profile</a>
          </nav>
        </div>
      </header>
    )
  }