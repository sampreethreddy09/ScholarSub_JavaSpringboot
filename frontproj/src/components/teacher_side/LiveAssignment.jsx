import React from "react";
import { BrowserRouter, Link, Route, Routes, useLocation } from 'react-router-dom';
import Assignment_view_teacher from "./Assignment_view_teacher";

export default function Liveassignment(){

    const [liveAssignments , setLiveAssignments] = React.useState([])
    // console.log(liveAssignments)

    const location = useLocation();
    const propsData = location.state;

    React.useEffect(()=>{
        fetch(`http://localhost:8000/teacher/liveassignments/${propsData.uname}`)
            .then(res => res.json())
            .then(data => setLiveAssignments(data.liveAssignments))
    },[])

    async function delete_Assignment(a_id){
        console.log(a_id)
        var res = await fetch(`http://localhost:8000/teacher/assignmentdelete/${a_id}`,{
            method: "GET",
        })

        var reply = await res.json()
        console.log(reply)

        if(reply){
            console.log("done")
            alert("Assignment deleted successfully")
            
        }
        else if (reply.error){
            console.log(error)
        }
    }

    const alist = liveAssignments.map((assignment)=>(
        <div key={assignment.a_id} className="assignment_box">
            <h4 className="details"> {assignment.name}</h4>
            <h4 className="details"> Created for Section : {assignment.sec_id}</h4>
            <h4 className="details"> Ends at : {new Date(assignment.end_time).toLocaleString()}</h4>
            <Link to={`/teacher/pastassignment/${assignment.a_id}`} state={assignment}>
            <button className="go_to_assignment">Go to the assignment</button></Link>
            <button className="delete_assignment" onClick={() => delete_Assignment(assignment.a_id)}>Delete </button>
        </div>
    ))

    return(
        <div className="past_page">
            <h3 className="h3_past_assignments">Live Assignments</h3>
            <div className="root_past_assignments">
                {alist}
            </div>

            
        </div>
    )
}