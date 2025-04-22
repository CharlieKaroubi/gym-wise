import { useEffect, useState } from 'react';
import axiosInstance from '@/api/axiosInstance';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Accordion, AccordionContent, AccordionItem, AccordionTrigger } from '@/components/ui/accordion';
import { useOutletContext } from "react-router-dom";
import { Navbar } from '@/components/Navbar';
import { Button } from '@/components/ui/button';
import SplitSkeletonCard from "@/components/SkeletonSplits";

export type Exercise = {
  exerciseName: string;
  setsReps: string;
};

export type SplitDay = {
  title: string;
  exercises: Exercise[];
};

export type Split = {
  id?: number;
  name: string;
  description: string;
  concentration: string;
  creator: string;
  days: SplitDay[];
};

type ContextType = {
    cachedSplits: Split[] | null;
};

export default function SplitsDashboard() {
  const { cachedSplits } = useOutletContext<ContextType>();
  const [splits, setSplits] = useState<Split[]>([]);
  const [error, setError] = useState<string>('');
  const [queryConcentration, setQueryConcentration] = useState("Hypertrophy");
  const [queryInput, setQueryInput] = useState("");
  const [loading, setLoading] = useState(false);

  const handleAddSplit = async (name: string) => {
    try {
      const res = await axiosInstance.post("/users/save-split", null, {
        params: {
          splitName: name
        }
      });
      console.log("Split added:", res.data);
    } catch (err) {
      console.error("Failed to add split", err);
    }
  };

  const handleSplitSearch = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setError('');
    setLoading(true);

    try {
        const res = await axiosInstance.get<Split[]>("/splits/similar", {
            params: {
              concentration: queryConcentration,
              input: queryInput,
            },
          });
        setSplits(res.data);
    } catch (err) {
        setError((err as any)?.response?.data?.message || "Failed to query splits");
    } finally{
        setLoading(false);
    }
 };

  

  useEffect(() => {
    if (cachedSplits) {
      setSplits(cachedSplits);
    } else {
      axiosInstance.get<Split[]>("/splits")
        .then(res => setSplits(res.data))
        .catch(err => setError(err.response?.data?.message || "Failed to load splits"));
    }
  }, [cachedSplits]);

  return (
    <>
    <Navbar />
    <div className="max-w-6xl mx-auto p-6">
      <h1 className="text-4xl font-bold text-center mb-6 text-orange-500">All Workout Splits</h1>
      
      <form onSubmit={handleSplitSearch} className="mb-6 bg-white p-4 rounded-lg shadow-md space-y-4">
        <div className="flex flex-col md:flex-row items-center gap-4">
            <div className="flex-1">
            <label htmlFor="concentration" className="block font-semibold text-sm mb-1">
                Concentration
            </label>
            <select
                id="concentration"
                value={queryConcentration}
                onChange={(e) => setQueryConcentration(e.target.value)}
                className="w-full p-2 border border-gray-300 rounded"
            >
                <option>Hypertrophy</option>
                <option>Strength</option>
                <option>Powerlifting</option>
                <option>High Frequency</option>
                <option>No Preference</option>
            </select>
            </div>

            <div className="flex-1">
            <label htmlFor="queryInput" className="block font-semibold text-sm mb-1">
                Describe it in Natural Language
            </label>
            <input
                id="queryInput"
                type="text"
                value={queryInput}
                onChange={(e) => setQueryInput(e.target.value)}
                className="w-full p-2 border border-gray-300 rounded"
                placeholder="e.g. A beginner friendly split focused on hypertrophy "
            />
            </div>
            {loading ?(
                <div role="status">
                <svg aria-hidden="true" className="w-10 h-10 mt-5 text-gray-200 animate-spin dark:text-gray-600 fill-blue-600" viewBox="0 0 100 101" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M100 50.5908C100 78.2051 77.6142 100.591 50 100.591C22.3858 100.591 0 78.2051 0 50.5908C0 22.9766 22.3858 0.59082 50 0.59082C77.6142 0.59082 100 22.9766 100 50.5908ZM9.08144 50.5908C9.08144 73.1895 27.4013 91.5094 50 91.5094C72.5987 91.5094 90.9186 73.1895 90.9186 50.5908C90.9186 27.9921 72.5987 9.67226 50 9.67226C27.4013 9.67226 9.08144 27.9921 9.08144 50.5908Z" fill="currentColor"/>
                    <path d="M93.9676 39.0409C96.393 38.4038 97.8624 35.9116 97.0079 33.5539C95.2932 28.8227 92.871 24.3692 89.8167 20.348C85.8452 15.1192 80.8826 10.7238 75.2124 7.41289C69.5422 4.10194 63.2754 1.94025 56.7698 1.05124C51.7666 0.367541 46.6976 0.446843 41.7345 1.27873C39.2613 1.69328 37.813 4.19778 38.4501 6.62326C39.0873 9.04874 41.5694 10.4717 44.0505 10.1071C47.8511 9.54855 51.7191 9.52689 55.5402 10.0491C60.8642 10.7766 65.9928 12.5457 70.6331 15.2552C75.2735 17.9648 79.3347 21.5619 82.5849 25.841C84.9175 28.9121 86.7997 32.2913 88.1811 35.8758C89.083 38.2158 91.5421 39.6781 93.9676 39.0409Z" fill="currentFill"/>
                </svg>
                <span className="sr-only">Loading...</span>
                </div>
            ) : (
                <button
                type="submit"
                className="bg-orange-500 mt-6 cursor-pointer text-white px-4 py-2 rounded hover:bg-orange-600 transition"
                disabled={loading}
                >
                Search
                </button>
            )}
            </div>
      </form>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-2 gap-6">
      {loading
    ? Array.from({ length: 4 }).map((_, i) => <SplitSkeletonCard key={i} />)
    : splits.map((split, i) => (
          <Card key={i} className="shadow-md">
            <CardHeader>
              <CardTitle className="flex flex-row items-center justify-between">
                <span>{split.name}</span>
                <span className="text-sm text-gray-500">Created By: {split.creator}</span>
                </CardTitle>
                <div className = "flex flex-row justify-between">
                  <p className="text-sm text-gray-500 mt-1">{split.concentration}</p>
                  <Button
                      variant="outline"
                      className="hover:bg-orange-600 cursor-pointer text-white hover:text-white bg-orange-500 w-20"
                      onClick={() => {
                        handleAddSplit(split.name);
                      }}
                    >
                      Add Split
                    </Button>
                </div>
            </CardHeader>
            <CardContent>
              <p className="text-gray-700 mb-4">{split.description}</p>
              <Accordion type="single" collapsible className="w-full">
                {split.days.map((day, idx) => (
                  <AccordionItem key={idx} value={`day-${i}-${idx}`}>
                    <AccordionTrigger>{day.title}</AccordionTrigger>
                    <AccordionContent>
                      {day.exercises.length === 0 ? (
                        <p className="text-sm text-gray-500">Rest Day</p>
                      ) : (
                        <ul className="list-disc list-inside space-y-1">
                          {day.exercises.map((ex, j) => (
                            <li key={j}>
                              <strong>{ex.exerciseName}</strong>: {ex.setsReps}
                            </li>
                          ))}
                        </ul>
                      )}
                    </AccordionContent>
                  </AccordionItem>
                ))}
              </Accordion>
            </CardContent>
          </Card>
        ))}
      </div>
    </div>
    </>
  );
}