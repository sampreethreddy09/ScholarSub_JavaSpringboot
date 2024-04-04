import React from "react";
import {  Link  ,useLocation} from 'react-router-dom';

export default function Student_Pastassignment(){

    const location = useLocation();
    const propsData = location.state;
    console.log(propsData)

    const [myData, setMyData] = React.useState([]);

    // var data = [
    //     {id: 1, aname : "Assignemnt-1", created_by : "Prof 1", end_date : 10},
    //     {id: 2, aname : "Assignemnt-2", created_by : "Prof 16", end_date : 20},
    //     {id: 3, aname : "Assignemnt-3", created_by : "Prof 32", end_date : 30},
    //     {id: 4, aname : "Assignemnt-1", created_by : "Prof 3", end_date : 5},
    // ]

    const fetchPastAssignments = async () =>{
        console.log("inside annyname")
        try {
            console.log("im here")

            const studentId = propsData.uname; // Assuming the student ID is in propsData
            const url = `http://localhost:8000/student/pastassignments/${studentId}`;

            var res = await fetch(url, {
            method: "GET",
            headers: {
                'Content-Type': 'application/json', // Set the content type to JSON
          },
        });

        var reply = await res.json()
        // console.log(reply)

        if (res.status === 200) {
            setMyData(reply.pastAssignments); // Update the state with the fetched data
            console.log(reply.pastAssignments);
          } else if (reply.error) {
            alert(reply.error);
          }
            
        } catch (error) {
            alert(`Failed to fetch ${error}`);
        }
    }

    React.useEffect(()=>{
        console.log("inside")
        fetchPastAssignments();
    },[])

    const [submissionStatus, setSubmissionStatus] = React.useState({});

    const isSubmitted = async (a_id) => {
        try {
            var res = await fetch(`http://localhost:8000/student/fetchresult/${propsData.uname}/${a_id}`, {
                method: 'GET',
            });

            var reply = await res.json();

            if (reply && reply.result.length >= 1) {
                setSubmissionStatus((prevStatus) => ({ ...prevStatus, [a_id]: 1 }));
            } else if (reply && reply.result.length === 0) {
                setSubmissionStatus((prevStatus) => ({ ...prevStatus, [a_id]: 0 }));
            }
        } catch (error) {
            console.error(error);
        }
    };

    React.useEffect(() => {
        myData.forEach((assignment) => {
            isSubmitted(assignment.a_id);
        });
    }, [myData]);
    
    const alist = myData.map((assignment)=>(
        <div key={assignment.a_id} className="assignment_box">
            <h4 className="details"> {assignment.name}</h4>
            <h4 className="details"> Ended at : {new Date(assignment.end_time).toLocaleString()}</h4>
            <h4 className="assignment_view_box_details" style={{ paddingLeft: "3.6%", paddingBottom: "1.4%" }}>
                {submissionStatus[assignment.a_id] === 1 ? "Submitted" : "Not Submitted"}
            </h4>
            <Link to={`/student/pastassignment/${propsData.uname}`} state={assignment}>
            <button className="go_to_assignment">Go to the assignment</button>
            </Link>
        </div>
    ))

    return(
        <div className="past_page">
            <h3 className="h3_past_assignments">Student Past Assignments</h3>
            <div className="root_past_assignments">
                {alist}
            </div>
        </div>
    )
}