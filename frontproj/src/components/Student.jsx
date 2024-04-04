import { Link, useLocation } from "react-router-dom";
import React from "react";
import Lottie from 'react-lottie';
import student_logo from "../images/student.json"
import Liveassignment from "./student_side/Student_Liveassignment";
import Pastassignment from "./student_side/Student_Pastassignment";

export default function Student(props){

    const location = useLocation();
    const propsData = location.state;
    // console.log(propsData)

    const defaultOptions = {
        loop: true,
        autoplay: true,
        animationData: student_logo,
        rendererSettings: {
          preserveAspectRatio: "xMidYMid slice"
        }
      };

    const [isLive, setIsLive] = React.useState(2)
    // console.log(isLive)

    return(
        <div className="student_outer">
            <div className="student">
                <h1 className="Title">ScholarSub</h1>   
                <h2>Hello, {propsData.uname}</h2>
            </div>
            <div className="teacher_operations">
                <Link to="/student/liveassignment" state={propsData}> 
                <button name = "Live" className="teacher_operation_buttons" onClick={()=> setIsLive(1)} > Live Assignments</button>
                </Link>
                
                <Link to="/student/pastassignment" state={propsData}>
                <button name = "Student_Past" className="teacher_operation_buttons" onClick={()=> setIsLive(0)}> Past Assignments</button>
                </Link>

                {/* <button className="teacher_operation_buttons"> Live Assignment</button>
                <button className="teacher_operation_buttons"> Past Assignments</button> */}
            </div>
            <div className="animation_logo"> 
                <Lottie
                    options={defaultOptions}
                    height={450}
                    width={500}
                />
            </div>
            <div className="workspace">
                    {isLive==1 && <Liveassignment /> }
                    {isLive==0 && <Pastassignment /> }
                </div>
         </div>
    )
}