import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom';

export default function ProtectedRoute({children}) {
    const navigate = useNavigate();

    useEffect(()=>{
        if(!window.sessionStorage.getItem('token')){
            navigate('/login');
        }
    // eslint-disable-next-line react-hooks/exhaustive-deps
    },[]);
    
  return (
    <>
      {children}
    </>
  )
}
