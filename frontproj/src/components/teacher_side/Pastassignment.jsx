import React,{useEffect,useState} from "react";
import { Link , useLocation } from 'react-router-dom';

export default function Pastassignment(){

    const [pastAssignments , setPastAssignments] = useState([])
    //console.log(pastAssignments)

    const location = useLocation();
    const propsData = location.state;

    const fetchPastAssignments = async () =>{
        try {
            const teacherId = propsData.uname; // Assuming the student ID is in propsData
            const url = `http://localhost:8080/api/teacher/pastassignments/${teacherId}`;

            var res = await fetch(url, {
            method: "GET",
            headers: {
                'Content-Type': 'application/json', // Set the content type to JSON
          },
        });

        var reply = await res.json()
        // console.log(reply)

        if (res.status === 200) {
            setPastAssignments(reply); // Update the state with the fetched data
          } else if (reply.error) {
            alert(reply.error);
          }
            
        } catch (error) {
            alert(`Failed to fetch ${error}`);
        }
    }

    useEffect(()=>{
        fetchPastAssignments();
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
    
    const alist = pastAssignments?.map((assignment)=>(
        <div key={assignment.id} className="assignment_box">
            <h4 className="details"> {assignment.name}</h4>
            <h4 className="details"> Created to : {assignment.secId}</h4>
            <h4 className="details"> Ended at : {new Date(assignment.endTime).toLocaleString()}</h4>
            <Link to={`/teacher/pastassignment/${assignment.id}`} state={assignment}>
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