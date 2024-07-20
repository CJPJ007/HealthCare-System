import React from 'react'

export default function LogData({setHealthData}) {
  return (
    <div className='w-full'>
    <h1 className='text-xl mb-4'>Log new health data</h1>
    <form className='flex flex-col space-y-2'>
      <input placeholder='Metric' className='border-[1px] rounded-md p-2 text-xs' onChange={(e)=>{setHealthData(state=>{
        return {...state, metric:e.target.value}
      })}}/>
      <input placeholder='Value' className='border-[1px] rounded-md p-2 text-xs' onChange={(e)=>{setHealthData(state=>{
        return {...state, value:e.target.value}
      })}}/>
    </form>
    </div>
  )
}
