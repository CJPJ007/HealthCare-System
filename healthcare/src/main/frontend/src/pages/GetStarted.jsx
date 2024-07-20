import React from "react";
import { Link } from "react-router-dom";

export default function GetStarted() {
  return (

    <div className="h-screen max-w-md w-full p-4 flex flex-col justify-between items-center bg-white rounded-md shadow-md">
      <img src="/img/checklist.jpg" alt="" className="h-72" />
      <div>
      <h1 className="text-3xl font-bold text-center mb-4">HealthTrack</h1>
      <p>Monitor your health with ease</p>
      </div>
      <div className="flex space-x-2 justify-center">
      <Link to={"/register"} className="border-[1px] rounded-md flex flex-col items-center p-2 justify-between">
        <img src="/img/signup.png" alt="" className="h-6 object-contain" />
        <span className="text-xs">SignUp</span>
      </Link>
      <Link to={"/login"} className="border-[1px] rounded-md flex flex-col items-center justify-between p-2">
        <img src="/img/login.png" alt="" className="h-6 object-contain" />
        <span className="text-xs">Login</span>
      </Link>
      <button className="bg-[#a3534c] hover:bg-[#a3534c]/90 text-white font-bold py-2 px-4 rounded-md w-full">
        Get Started
      </button>
      </div>
    </div>
  );
}
