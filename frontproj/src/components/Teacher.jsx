import React from "react";
import { Link, useLocation } from "react-router-dom";
import Lottie from 'react-lottie';
import teacher_logo from "../images/teacher.json"
import CreateAssignment from "./teacher_side/CreateAssignment";
import Pastassignment from "./teacher_side/Pastassignment";
import Teacher_Liveassignment from "./teacher_side/LiveAssignment"

export default function Teacher(props) {

    const location = useLocation();
    const propsData = location.state;
    console.log(propsData)

    const defaultOptions = {
        loop: true,
        autoplay: true,
        animationData: teacher_logo,
        rendererSettings: {
            preserveAspectRatio: "xMidYMid slice"
        }
    };

    const [isPost, setIsPost] = React.useState(3)

    console.log(isPost)
    return (
        <div className="teacher_outer">
            <div className="teacher">
                <h1 className="Title">ScholarSub</h1>
                <h2 className="uname"><span className="hello">👋</span>Hello {propsData.uname}</h2>
            </div>
            <div className="workspace_outer">
                <div className="teacher_operations">
                    <Link to="/teacher/CreateAssignment" state={propsData}>
                        <button name="Post" className="teacher_operation_buttons" onClick={() => setIsPost(1)} > Create Assignment</button>
                    </Link>

                    <Link to="/teacher/liveassignment" state={propsData}>
                    <button name = "Live" className="teacher_operation_buttons" onClick={()=> setIsPost(2)}> Live Assignments</button>
                    </Link>
                    
                    <Link to="/teacher/pastassignment" state={propsData}>
                        <button name="Past" className="teacher_operation_buttons" onClick={() => setIsPost(0)}> Past Assignments</button>
                    </Link>

                    {/* <button name = "Post" className="teacher_operation_buttons" onClick={()=> setIsPost(true)} > Create Assignment</button> */}
                    {/* <button name = "Past" className="teacher_operation_buttons" onClick={()=> setIsPost(false)}> Past Assignments</button> */}
                </div>
                <div className="animation_logo">
                    <Lottie
                        options={defaultOptions}
                        height={450}
                        width={500}
                    />
                </div>
                <div className="workspace">
                    {isPost==1 && <CreateAssignment /> }
                    {isPost==0 && <Pastassignment /> }
                    {isPost==2 && <Teacher_Liveassignment />}
                </div>
            </div>
        </div>
    )
}