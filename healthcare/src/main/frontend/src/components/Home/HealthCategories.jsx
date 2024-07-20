import React, { useEffect, useState } from "react";
import { getCategories } from "../../service/HealthService";
import Loader from "../Loader";

export default function HealthCategories({ setHealthData }) {
  const [categories, setCategories] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);
    console.log("After setloading");
    const fetchCategories = async () => {
      const data = await getCategories();
      console.log("fetchCategories : ", data);
      setCategories(data);
      setLoading(false);
    };
    fetchCategories();
  }, []);

  return (
    <div className="w-full space-x-2">
      {loading ? <Loader /> : ""}
      <h2 className="text-sm mb-4">Categories:</h2>
      <div className="grid grid-cols-4 gap-2">
        {categories.map((category) => {
          return (
            <span
              className="bg-[#a3534c] rounded-3xl py-1 px-2 text-xs text-white text-center hover:cursor-pointer"
              onClick={() =>
                setHealthData((state) => ({
                  ...state,
                  category: category,
                }))
              }
            >
              {category}
            </span>
          );
        })}
      </div>
    </div>
  );
}
