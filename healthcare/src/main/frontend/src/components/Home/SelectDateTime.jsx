import React from "react";

export default function SelectDateTime({ today, label }) {
    
  const range = (start, stop) => {
    return Array.from({ length: stop - start }, (_, i) => start + i);
  };

  const getStop = () => {
    let date = new Date();
    return new Date(date.getFullYear(), date.getMonth() + 1, 0).getDate();
  };

  const getToday = () => {
    return <option selected>{new Date().getDate()}</option>;
  };

  const getOptions = () => {
    return (
      <>
        <option disabled selected>
          Select Date
        </option>
        {range(1, getStop(), 1).map((value) => (
          <option key={value}>{value}</option>
        ))}
      </>
    );
  };

  return (
    <div className="flex justify-between items-center">
      <label htmlFor="record">{label}</label>
      {today}
      <div className="space-x-2">
        <select
          name="recordDate"
          className="border rounded-md focus:outline-none p-1"
        >
          {today ? getToday() : getOptions()}
        </select>
        <select
          name="recordDate"
          className="border rounded-md focus:outline-none p-1"
        >
          <option disabled selected>
            Select Time
          </option>
          {range(0, 24, 1).map((value) => {
            return (
              <>
                <option key={value + 1}>{value}:00</option>
                <option key={value + 2}>{value}:15</option>
                <option key={value + 3}>{value}:30</option>
                <option key={value + 4}>{value}:45</option>
              </>
            );
          })}
        </select>
      </div>
    </div>
  );
}
