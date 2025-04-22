import axiosInstance from "@/api/axiosInstance";
import { Navbar } from "@/components/Navbar";
import { useEffect, useState} from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table"


type UserSplit = {
  name: string;
  creator: string;
}

export default function Home() {
  const [userSplits, setUserSplits] = useState<UserSplit[]>([]);
  const [error, setError] = useState<string>('');

  const loadUserSplits = async () => {
    try {
      const response = await axiosInstance.get<UserSplit[]>('/users/profile/splits');
      if (Array.isArray(response.data)) {
        setUserSplits(response.data);
      } else {
        setError("Invalid response format");
      }
    } catch (err) {
      console.error("Error fetching exercises:", err);
      if (err instanceof Error && err.name === 'SyntaxError') {
        localStorage.removeItem("cachedExercises");
      }
      setError((err as any).response?.data?.message || 'Failed to load exercises');
    }
  };
  
  useEffect(() => {
    loadUserSplits();
  }, []);


  
  return (
    <>
      <Navbar></Navbar>
      <div className="flex justify-center mt-10 mx-10">
        <div className="w-1/3">
        <div className="flex justify-center w-full mb-4">
          <h2 className="text-xl font-semibold">Your Saved Splits</h2>
        </div>
          <Table>
            <TableHeader>
              <TableRow className="hover:bg-transparent">
              <TableHead className="w-[100px]">Split Name</TableHead>
              <TableHead className="text-right">Creator</TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {userSplits.map((split) => (
                <TableRow 
                  className="hover:bg-transparent"
                  key={split.name}>
                  <TableCell className="font-medium">{split.name}</TableCell>
                    <TableCell className="text-right">{split.creator}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </div>
      </div>
    </>
  )

}
