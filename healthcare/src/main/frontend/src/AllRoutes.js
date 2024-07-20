import React from 'react'
import { Route, Routes } from 'react-router-dom'
import Login from './pages/Login'
import Home from './pages/Home'
import PageNotFound from './pages/PageNotFound'
import Registration from './pages/Registration'
import GetStarted from './pages/GetStarted'
import ProtectedRoute from './components/ProtectedRoute'

export default function AllRoutes() {
  return (
    <Routes>
      <Route path='/login' element={<Login />}/>
      <Route path='/home' element={<ProtectedRoute><Home /></ProtectedRoute>}/>
      <Route path='/register' element={<Registration/>}/>
      <Route path='/' element={<GetStarted />}/>
      <Route path='/**' element={<PageNotFound />}/>
    </Routes>
  )
}
