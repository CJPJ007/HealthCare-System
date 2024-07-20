import React, { useState } from 'react'
import NavBar from '../components/Home/NavBar'
import TaskBar from '../components/Home/TaskBar'
import LogData from '../components/Home/LogData'
import RecordTime from '../components/Home/RecordTime';
import HealthCategories from '../components/Home/HealthCategories';

export default function Home() {
  const [healthData,setHealthData] = useState({});
  
  return (
    <div className="h-screen max-w-md w-full p-4 flex flex-col justify-between items-center bg-white rounded-md shadow-md">
      <NavBar/>
      <LogData setHealthData={setHealthData}/>
      <RecordTime setHealthData={setHealthData} />
      <HealthCategories setHealthData={setHealthData}/>
      <TaskBar/>
    </div>
  )
}
