import {
    Carousel,
    CarouselContent,
    CarouselItem,
    CarouselNext,
    CarouselPrevious,
  } from "@/components/ui/carousel"
  import { Card, CardHeader, CardTitle, CardContent } from "@/components/ui/card"
  
  export default function CardSlider() {
    return (
      <div className="w-full flex justify-center mt-10 px-4">
        <div className="relative w-full max-w-7xl">
          <Carousel>
            <CarouselContent>
              <CarouselItem className="flex justify-center">
                <Card className="w-full max-w-5xl h-[400px] p-6">
                  <CardHeader>
                    <CardTitle className="text-5xl text-center text-orange-500 text-shadow-sm">Welcome</CardTitle>
                  </CardHeader>
                  <CardContent className="text-xl md:text-4xl text-center">
                    HypertroFi is here to<span className="text-orange-500 text-shadow-xs"> optimize </span> 
                    your gym experience. If you're into science-based lifting
                    then this is the place for you. We provide you with a <span className="text-orange-500 text-shadow-xs">modern </span> 
                    and <span className="text-orange-500 text-shadow-xs">interactive </span> database of exercises, 
                    where you can rate and review exercises. You can also
                    create your own workout plans and share them with the community. Get started 
                    today and the gains will follow!
                  </CardContent>
                </Card>
              </CarouselItem>
  
              <CarouselItem className="flex justify-center">
              <Card className="w-full max-w-5xl h-[400px] p-6">
                  <CardHeader>
                    <CardTitle className="text-5xl text-center text-orange-500 text-shadow-sm">Features</CardTitle>
                  </CardHeader>
                  <CardContent className="text-xl md:text-4xl text-center">
                    Interactive exercise database<br />
                    Rate and review exercises<br />
                    Community Driven<br />
                    Create and share workout plans<br />
                    
                  </CardContent>
                </Card>
              </CarouselItem>
  
              <CarouselItem className="flex justify-center">
                <Card className="w-full max-w-5xl h-[400px] p-6">
                    <CardHeader>
                        <CardTitle className="text-5xl text-center text-orange-500 text-shadow-sm">Welcome</CardTitle>
                    </CardHeader>
                    <CardContent className="text-xl md:text-4xl text-center">
                        HypertroFi is here to<span className="text-orange-500 text-shadow-xs"> optimize </span> 
                        your gym experience. If you're into science-based lifting
                        then this is the place for you. We provide you with a <span className="text-orange-500 text-shadow-xs">modern </span> 
                        and <span className="text-orange-500 text-shadow-xs">interactive </span> database of exercises, 
                        where you can rate and review exercises. You can also
                        create your own workout plans and share them with the community. Get started 
                        today and the gains will follow!
                    </CardContent>
                    </Card>
              </CarouselItem>
            </CarouselContent>
  
            <CarouselPrevious className="absolute left-2 top-1/2 -translate-y-1/2 z-10 bg-white rounded-full shadow-md hover:bg-gray-200 transition w-10 h-10 sm:w-12 sm:h-12" />
            <CarouselNext className="absolute right-2 top-1/2 -translate-y-1/2 z-10 bg-white rounded-full shadow-md hover:bg-gray-200 transition w-10 h-10 sm:w-12 sm:h-12" />
          </Carousel>
        </div>
      </div>
    )
  }
  