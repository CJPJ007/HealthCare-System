import React from "react";

export default function ToggleButton({ setToday, today }) {
  const handleToggle = () => {
    setToday(!today);
  };

  return (
    <button
      className="inline-flex items-center cursor-pointer"
      onClick={handleToggle}
    >
      <input type="checkbox" value="" className="sr-only peer" />
      <div className="relative w-7 h-4 peer-focus:outline-none  rounded-full peer shadow-md shadow-slate-200 peer-checked:after:translate-x-full rtl:peer-checked:after:-translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:start-[2px] after:bg-[#a3534c] after:rounded-full after:h-3 after:w-3 after:transition-all peer-checked:bg-[#a3534c] peer-checked:after:bg-white"></div>
    </button>
  );
}
