import React from "react";
import { BrowserRouter, Link, Route, Routes, useLocation } from 'react-router-dom';
import Assignment_view_teacher from "./Assignment_view_teacher";

export default function Pastassignment(){

    const [pastAssignments , setPastAssignments] = React.useState([])
    console.log(pastAssignments)

    const location = useLocation();
    const propsData = location.state;

    React.useEffect(()=>{
        fetch(`http://localhost:8000/teacher/pastassignments/${propsData.uname}`)
            .then(res => res.json())
            .then(data => setPastAssignments(data.pastAssignments))
    },[])

    // console.log(pastAssignments)

    // var data = [
    //     {id: 1, aname : "Assignemnt-1", created_to : "Section-A", end_date : 10},
    //     {id: 2, aname : "Assignemnt-2", created_to : "Section-B", end_date : 20},
    //     {id: 3, aname : "Assignemnt-3", created_to : "Section-C", end_date : 30},
    //     {id: 4, aname : "Assignemnt-1", created_to : "Section-A", end_date : 5},
    //     {id: 5, aname : "Assignemnt-2", created_to : "Section-B", end_date : 80},
    //     {id: 6, aname : "Assignemnt-3", created_to : "Section-C", end_date : 2},
    //     {id: 7, aname : "Assignemnt-1", created_to : "Section-A", end_date : 10},
    //     {id: 8, aname : "Assignemnt-2", created_to : "Section-B", end_date : 20},
    //     {id: 9, aname : "Assignemnt-3", created_to : "Section-C", end_date : 30},
    //     {id: 10,aname : "Assignemnt-1", created_to : "Section-A", end_date : 5},
    //     {id: 11,aname : "Assignemnt-2", created_to : "Section-B", end_date : 80},
    //     {id: 12,aname : "Assignemnt-3", created_to : "Section-C", end_date : 2}
    // ]
    
    const alist = pastAssignments.map((assignment)=>(
        <div key={assignment.a_id} className="assignment_box">
            <h4 className="details"> {assignment.name}</h4>
            <h4 className="details"> Created to : {assignment.sec_id}</h4>
            <h4 className="details"> Ended at : {new Date(assignment.end_time).toLocaleString()}</h4>
            <Link to={`/teacher/pastassignment/${assignment.a_id}`} state={assignment}>
            <button className="go_to_assignment">Go to the assignment</button>
            </Link>
        </div>
    ))

    return(
        <div className="past_page">
            <h3 className="h3_past_assignments">Past Assignments</h3>
            <div className="root_past_assignments">
                {alist}
            </div>

            
        </div>
    )
}