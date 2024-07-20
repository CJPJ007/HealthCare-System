import React, { useState } from "react";
import { registerUserDetails } from "../service/UserService";
import { useNavigate } from "react-router-dom";
import Loader from "../components/Loader";

const Registration = () => {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [age, setAge] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    setLoading(true);
    event.preventDefault();
    const result = await registerUserDetails({
      firstName,
      lastName,
      email,
      password,
      age,
    });
    if (result) {
      setLoading(false);
      navigate("/login");
    }
    setLoading(false);
  };

  return (
    <div className="h-screen max-w-md w-full p-4 flex flex-col justify-center items-center bg-white rounded-md shadow-md">
      {loading ? <Loader /> : ""}
      <h1 className="text-3xl font-bold text-center mb-4">HealthTrack</h1>
      <h2>Registration</h2>
      <form onSubmit={handleSubmit} className="space-y-2">
        <label className="block" htmlFor="firstName">
          First Name:
        </label>
        <input
          type="text"
          value={firstName}
          onChange={(event) => setFirstName(event.target.value)}
          name="firstName"
          className="w-full p-2 text-sm text-gray-700 border-[1px] focus:outline-none rounded-md"
          required
        />
        <br />
        <label>Last Name:</label>
        <input
          type="text"
          value={lastName}
          onChange={(event) => setLastName(event.target.value)}
          className="w-full p-2 text-sm text-gray-700 border-[1px] focus:outline-none rounded-md"
          required
        />
        <br />
        <label>Email:</label>
        <input
          type="email"
          value={email}
          onChange={(event) => setEmail(event.target.value)}
          className="w-full p-2 text-sm text-gray-700 border-[1px] focus:outline-none rounded-md"
          required
        />
        <br />
        <label>Password:</label>
        <input
          type="password"
          value={password}
          onChange={(event) => setPassword(event.target.value)}
          className="w-full p-2 text-sm text-gray-700 border-[1px] focus:outline-none rounded-md"
          required
        />
        <br />
        <label>Age:</label>
        <input
          type="number"
          value={age}
          onChange={(event) => setAge(event.target.value)}
          className="w-full p-2 text-sm text-gray-700 border-[1px] focus:outline-none rounded-md"
          required
        />
        <br />
        <button
          type="submit"
          className="bg-[#a3534c] hover:bg-[#a3534c]/90 text-white font-bold py-2 px-4 rounded-md w-full focus:outline-none"
        >
          Register
        </button>
      </form>
    </div>
  );
};

export default Registration;
