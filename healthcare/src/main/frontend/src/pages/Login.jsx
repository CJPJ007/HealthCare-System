import React, { useRef, useState } from "react";
import { fetchUserDetails } from "../service/UserService";
import { useNavigate } from "react-router-dom";
import Loader from "../components/Loader";

export default function Login() {
  const formRef = useRef({});
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const handleLogin = async (e) => {
    setLoading(true);
    e.preventDefault();
    const result = await fetchUserDetails(
      formRef.current.email.value,
      formRef.current.password.value
    );
    if (result) {
      setLoading(false);
      navigate("/home");
    }
    setLoading(false);
  };

  return (
    <div className="h-screen max-w-md w-full p-4 flex flex-col justify-center items-center bg-white rounded-md shadow-md">
      {loading ? <Loader /> : ""}
      <h1 className="text-3xl font-bold text-center mb-4">HealthTrack</h1>
      <form ref={formRef} className="space-y-2">
        <label className="block" htmlFor="email">
          Email
        </label>
        <input
          type="email"
          id="email"
          className="w-full p-2 text-sm text-gray-700 border-[1px] focus:outline-none rounded-md"
          name="email"
          placeholder="example@example.com"
        />
        <label className="block" htmlFor="password">
          Password
        </label>
        <input
          type="password"
          id="password"
          className="w-full p-2 text-sm text-gray-700 border-[1px] focus:outline-none rounded-md"
          name="password"
          placeholder="Password"
        />
        <button
          className="bg-[#a3534c] hover:bg-[#a3534c]/90 text-white font-bold py-2 px-4 rounded-md w-full focus:outline-none"
          type="submit"
          onClick={handleLogin}
        >
          Login
        </button>
      </form>
    </div>
  );
}
