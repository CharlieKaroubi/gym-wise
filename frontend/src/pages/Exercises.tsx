import { use, useEffect, useState } from 'react';
import axiosInstance from '@/api/axiosInstance';
import { Card, CardHeader, CardTitle, CardContent } from '@/components/ui/card';
import { Input } from '@/components/ui/input';
import { cn } from '@/lib/utils'; 
import { Navbar } from '@/components/Navbar';

export type Exercise = {
  id: number;
  name: string;
  force: string;
  level: string;
  mechanic: string;
  equipment: string;
  instructions: string;
  primaryMuscles: string;
  secondaryMuscles: string;
};

const level_map ={
    "Beginner": "text-green-500",
    "Intermediate": "text-yellow-500",
    "Advanced": "text-red-500"
}

export default function ExercisesDashboard() {
  const [exercises, setExercises] = useState<Exercise[]>([]);
  const [error, setError] = useState<string>('');
  const [search, setSearch] = useState<string>('');

  const fetchExercises = async () => {
    try {
      const cached = localStorage.getItem("cachedExercises");
      
      if (cached) {
        const parsed = JSON.parse(cached);
        if (Array.isArray(parsed)) {
          setExercises(parsed);
          return;
        }
      }
      // If no valid cache, fetch from API
      const response = await axiosInstance.get<Exercise[]>('/exercises');
      
      if (Array.isArray(response.data)) {
        setExercises(response.data);
        localStorage.setItem("cachedExercises", JSON.stringify(response.data));
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
    fetchExercises();
  }, []);

  const filtered = exercises.filter(ex => {
    const matchSearch = ex.name.toLowerCase().includes(search.toLowerCase());
    return matchSearch
  });

  return (
    <>
    <Navbar />
    <div className="max-w-7xl mx-auto p-6">
      <h1 className="text-5xl text-orange-500 text-center font-bold mb-4">Exercise Library</h1>

      {/* Filters */}
      <div className="flex flex-col md:flex-row gap-4 mb-6">
        <div className="flex-1">
          <Input
            id="search"
            placeholder="Search exercises..."
            value={search}
            onChange={e => setSearch(e.target.value)}
          />
        </div>
      </div>

      {error && <p className="text-red-500 mb-4">{error}</p>}

      {/* Cards Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {filtered.map(ex => (
          <Card key={ex.id} className="hover:shadow-lg transition-shadow">
            <CardHeader>
              <CardTitle>{ex.name}</CardTitle>
            </CardHeader>
            <CardContent>
              <p><strong>Force:</strong> {ex.force}</p>
              <p><strong>Level:</strong> <span className={cn(level_map[ex.level as keyof typeof level_map] || '')}>{ex.level}</span></p>
              <p><strong>Equipment:</strong> {ex.equipment}</p>
              <p><strong>Primary Muscles:</strong> {ex.primaryMuscles}</p>
              <p><strong>Secondary Muscles:</strong> {ex.secondaryMuscles}</p>
              <details className="mt-2">
                <summary className="cursor-pointer text-sm font-medium">Instructions</summary>
                <p className="mt-1 text-sm whitespace-pre-wrap">{ex.instructions}</p>
              </details>
            </CardContent>
          </Card>
        ))}
      </div>
    </div>
    </>
  );
}