import React from "react";
import { Link, useLocation } from 'react-router-dom';

export default function Liveassignment(props) {

    const location = useLocation();
    const propsData = location.state;
    // console.log(propsData)



    const [myData, setMyData] = React.useState([]);

    // var data = [
    //     {id: 1, aname : "Assignemnt-1", created_by : "Naga Sundari", end_date : 10, isSubmitted : true},
    //     {id: 2, aname : "Assignemnt-2", created_by : "Badri Prasad", end_date : 20, isSubmitted : false},
    //     {id: 3, aname : "Assignemnt-11", created_by : "Naga Sundari", end_date : 10, isSubmitted : true},
    //     {id: 4, aname : "Assignemnt-8", created_by : "Badri Prasad", end_date : 20, isSubmitted : false},

    // ]

    const fetchLiveAssignments = async () => {
        // console.log("inside annyname")
        try {
            // console.log("im here")

            const studentId = propsData.uname; // Assuming the student ID is in propsData
            const url = `http://localhost:8080/api/assignments/live/${studentId}`;

            var res = await fetch(url, {
                method: "GET",
                headers: {
                    'Content-Type': 'application/json', // Set the content type to JSON
                },
            });

            var reply = await res.json()
            console.log(reply)

            if (res.status === 200) {
                setMyData(reply); // Update the state with the fetched data
                // console.log(reply.activeAssignments);
            } else if (reply.error) {
                alert(reply.error);
            }
            //   console.log(myData);

        } catch (error) {
            alert(`Failed to fetch ${error}`);
        }
    }

    React.useEffect(() => {
        // console.log("inside")
        fetchLiveAssignments();
    }, [])

    const [submissionStatus, setSubmissionStatus] = React.useState({});

    const isSubmitted = async () => {
        try {
            // Fetch all submissions for the current student
            const res = await fetch(`http://localhost:8080/api/student/submissions/${propsData.uname}`, {
                method: 'GET',
            });
            const submissions = await res.json();

            console.log("submissions", submissions)
    
            // Extract assignment IDs from the fetched submissions
            const submittedAssignmentIds = submissions.map(submission => submission.assignmentId);

            console.log("submittedAssignmentIds", submittedAssignmentIds)
    
            // Update submission status for each assignment
            const updatedStatus = {};
            myData.forEach((assignment) => {
                updatedStatus[assignment.id] = submittedAssignmentIds.includes(assignment.id) ? 1 : 0;
            });

            console.log("updatedStatus", updatedStatus)

            setSubmissionStatus(updatedStatus);

            

        } catch (error) {
            console.error(error);
        }
    };
    
    React.useEffect(() => {
        isSubmitted();
    }, [myData]);
    

    const alist = myData.map((assignment) => (
        <div key={assignment.id} className="assignment_box">
            <h4 className="details"> {assignment.name}</h4>
            <h4 className="details"> Deadline: {new Date(assignment.endTime).toLocaleString()}</h4>
            <h4 className="assignment_view_box_details" style={{ paddingLeft: "3.6%", paddingBottom: "1.4%" }}>
                {submissionStatus[assignment.id] === 1 ? "Submitted" : "Not Submitted"}
            </h4>
            <Link to={`/student/liveassignment/${propsData.uname}`} state={assignment}>
                <button className="go_to_assignment">
                    {submissionStatus[assignment.id] === 1 
                        ? "View Assignment"
                        : "Upload Assignment"}
                </button>
            </Link>
        </div>
    ));
    return (
        <div className="past_page">
            <h3 className="h3_past_assignments">Live Assignments</h3>
            <div className="root_past_assignments">
                {alist}
            </div>
        </div>
    )
}
