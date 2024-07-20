import React, { useState } from "react";
import ToggleButton from "../ToggleButton";
import SelectDateTime from "./SelectDateTime";

export default function RecordTime({ setHealthData }) {
  const [today, setToday] = useState(false);
  

  return (
    <div className="w-full">
      <div className="flex justify-between items-center">
        <h2 className="text-sm">Daily health log</h2>
        <ToggleButton setToday={setToday} today={today}/>
      </div>
      <form action="" className="grid grid-cols-1 gap-4 text-xs mt-4">
        <SelectDateTime label={"Record"} today={today}/>
        <SelectDateTime label={"Finishes"} today={today}/>
      </form>
    </div>
  );
}
