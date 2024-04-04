import React from 'react';
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom';
import GetStarted from './components/GetStarted';
import Login from './components/Login'
import Teacher from './components/Teacher';
import Student from './components/Student';
import CreateAssignment from './components/teacher_side/CreateAssignment';
import Pastassignment from './components/teacher_side/Pastassignment';
import Teacher_Liveassignment from './components/teacher_side/LiveAssignment';
import Liveassignment from './components/student_side/Student_Liveassignment';
import Student_Pastassignment from './components/student_side/Student_Pastassignment';
import Assignment_view_teacher from './components/teacher_side/Assignment_view_teacher';
import Submission_view_teacher from './components/teacher_side/Submission_view_teacher';
import Submission_View_Student from './components/student_side/Submission_View_Student';
import Assignmentupload from './components/student_side/Assignmentupload';


import './app.css'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<GetStarted />} />
        <Route path="/login" element={<Login />} />
        <Route path="/teacher" element={<Teacher />} />
        <Route path="/student" element={<Student />} />

        <Route path="/teacher/CreateAssignment" element={<CreateAssignment />} 
        />
        <Route path="/teacher/pastassignment" element={<Pastassignment />} />
        <Route path="/teacher/liveassignment" element={<Teacher_Liveassignment />} />

        <Route path="/student/liveassignment" element={<Liveassignment />} />
        <Route path="/student/pastassignment" element={<Student_Pastassignment />} />

        <Route path="/student/pastassignment/:s_id" element={<Submission_View_Student />}  />
        <Route path="/student/liveassignment/:s_id" element={<Assignmentupload />}  />

        <Route path="/teacher/pastassignment/:assignment_id" element={<Assignment_view_teacher />}  />
        <Route path="/teacher/pastassignment/:assignment_id/:submission_id" element={<Submission_view_teacher />}  />

      </Routes>
    </BrowserRouter>
  )
}

export default App
