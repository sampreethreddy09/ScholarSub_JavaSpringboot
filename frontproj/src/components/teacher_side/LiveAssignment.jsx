import React ,{useEffect} from "react";
import { BrowserRouter, Link, Route, Routes, useLocation } from 'react-router-dom';
import Assignment_view_teacher from "./Assignment_view_teacher";

export default function Liveassignment(){

    const [liveAssignments , setLiveAssignments] = React.useState([])
    // console.log(liveAssignments)

    const location = useLocation();
    const propsData = location.state;

    const fetchLiveAssignments = async () => {
        // console.log("inside annyname")
        try {
            // console.log("im here")

            const teacherId = propsData.uname; // Assuming the student ID is in propsData
            const url = `http://localhost:8080/api/teacher/liveassignments/${teacherId}`;

            var res = await fetch(url, {
                method: "GET",
                headers: {
                    'Content-Type': 'application/json', // Set the content type to JSON
                },
            });

            var reply = await res.json()
            console.log(reply)

            if (res.status === 200) {
                setLiveAssignments(reply); // Update the state with the fetched data
                // console.log(reply.activeAssignments);
            } else if (reply.error) {
                alert(reply.error);
            }
            //   console.log(myData);

        } catch (error) {
            alert(`Failed to fetch ${error}`);
        }
    }


    useEffect(()=>{
        // fetch(`http://localhost:8080/api/teacher/liveassignments/${propsData.uname}`)
        //     .then(res => { res.json() })
        //     .then(data => setLiveAssignments(data))

        fetchLiveAssignments();
    },[])

    async function delete_Assignment(a_id){
        console.log(a_id)
        var res = await fetch(`http://localhost:8080/api/assignment/${a_id}`,{
            method: "DELETE",
        })

        var reply = await res.text()
        console.log(reply)

        if(reply){
            console.log("done")
            alert("Assignment deleted successfully")
            
        }
        else if (reply.error){
            console.log(error)
        }
    }

    const alist = liveAssignments?.map((assignment)=>(
        <div key={assignment.id} className="assignment_box">
            <h4 className="details"> {assignment.name}</h4>
            <h4 className="details"> Created for Section : {assignment.secId}</h4>
            <h4 className="details"> Ends at : {new Date(assignment.endTime).toLocaleString()}</h4>
            <Link to={`/teacher/pastassignment/${assignment.id}`} state={assignment}>
            <button className="go_to_assignment">Go to the assignment</button></Link>
            <button className="delete_assignment" onClick={() => delete_Assignment(assignment.id)}>Delete </button>
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