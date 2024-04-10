import React,{useEffect,useState} from "react";
import {  Link  ,useLocation} from 'react-router-dom';

export default function Student_Pastassignment(){

    const location = useLocation();
    const propsData = location.state;
    console.log(propsData)

    const [myData, setMyData] = useState([]);

    // var data = [
    //     {id: 1, aname : "Assignemnt-1", created_by : "Prof 1", end_date : 10},
    //     {id: 2, aname : "Assignemnt-2", created_by : "Prof 16", end_date : 20},
    //     {id: 3, aname : "Assignemnt-3", created_by : "Prof 32", end_date : 30},
    //     {id: 4, aname : "Assignemnt-1", created_by : "Prof 3", end_date : 5},
    // ]

    const fetchPastAssignments = async () =>{
        try {
            const studentId = propsData.uname; // Assuming the student ID is in propsData
            const url = `http://localhost:8080/api/assignments/past/${studentId}`;

            var res = await fetch(url, {
            method: "GET",
            headers: {
                'Content-Type': 'application/json', // Set the content type to JSON
          },
        });

        var reply = await res.json()
        // console.log(reply)

        if (res.status === 200) {
            setMyData(reply); // Update the state with the fetched data
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

    const [submissionStatus, setSubmissionStatus] = React.useState({});

    const isSubmitted = async () => {
        try {
            // Fetch all submissions for the current student
            const res = await fetch(`http://localhost:8080/api/student/submissions/${propsData.uname}`, {
                method: 'GET',
            });
            const submissions = await res.json();
    
            // Extract assignment IDs from the fetched submissions
            const submittedAssignmentIds = submissions.map(submission => submission.assignmentId);
    
            // Update submission status for each assignment
            const updatedStatus = {};
            myData.forEach((assignment) => {
                updatedStatus[assignment.id] = submittedAssignmentIds.includes(assignment.id) ? 1 : 0;
            });
            setSubmissionStatus(updatedStatus);
        } catch (error) {
            console.error(error);
        }
    };
    
    React.useEffect(() => {
        isSubmitted();
    }, [myData]);
    
    const alist = myData.map((assignment)=>(
        <div key={assignment.id} className="assignment_box">
            <h4 className="details"> {assignment.name}</h4>
            <h4 className="details"> Ended at : {new Date(assignment.endTime).toLocaleString()}</h4>
            <h4 className="assignment_view_box_details" style={{ paddingLeft: "3.6%", paddingBottom: "1.4%" }}>
                {submissionStatus[assignment.id] === 1 ? "Submitted" : "Not Submitted"}
            </h4>
            <Link to={`/student/pastassignment/${propsData.uname}`} state={assignment}>
            <button className="go_to_assignment">View Assignment</button>
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