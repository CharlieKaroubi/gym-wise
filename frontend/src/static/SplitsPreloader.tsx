import { useEffect, useState } from "react";
import axiosInstance from "@/api/axiosInstance";
import { Outlet } from "react-router-dom";
import { Split } from "@/pages/Splits";

export default function SplitsPreloader() {
  const [cachedSplits, setCachedSplits] = useState<Split[] | null>(null);

  useEffect(() => {
    const local = localStorage.getItem("cachedSplits");
    if (local) {
      setCachedSplits(JSON.parse(local));
    } else {
      axiosInstance.get<Split[]>("/splits")
        .then(res => {
          setCachedSplits(res.data);
          localStorage.setItem("cachedSplits", JSON.stringify(res.data));
        });
    }
  }, []);

  return <Outlet context={{ cachedSplits }} />;
}