import { Navbar } from "@/components/Navbar"
import CardSlider from "@/components/CardSlider"

export default function Onboarding() {
    return (
        <>
        <Navbar />
        <main className="flex flex-col items-center mt-6 px-4">
            <h1 className="text-7xl font-extrabold text-center leading-tighter">
            <span className="text-orange-500 font-main font-semibold">HypertroFi</span>
            </h1>
            <CardSlider />
        </main>
        </>
    )
}