import React from 'react'

export default function NavBar() {
  return (
    <div className='border-b-[1px] pb-2 flex w-full justify-between'>
      <img src='/img/rows.png' alt="" className='h-6'/>
      <div className='flex space-x-2'>
        <button><img src="/img/bell.png" alt="" className='h-6' /></button>
        <button><img src="/img/user.png" alt="" className='h-6' /></button>
      </div>
    </div>
  )
}
