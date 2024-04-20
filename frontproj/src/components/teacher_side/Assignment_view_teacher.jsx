import React, { useState ,useEffect } from "react";
import { Link} from 'react-router-dom';
import { useLocation } from "react-router-dom";

export default function Assignment_view_teacher(props){

    const location = useLocation();
    const propsData = location.state;
    // console.log(propsData)
    // console.log("a_id",propsData.id)

    const [submissionsData, setSubmissionsData] = useState([])
    const [sws, setSws] = useState([])
    const [count, setCount] = useState()
    

    const getSubmissions = async() =>{
        var res = await fetch(`http://localhost:8080/api/teacher/submissions/${propsData.id}`,{
            method : "GET"
        })

        var reply = await res.json()
        console.log("reply",reply)
        if(reply){
            setSubmissionsData(reply)
            setCount(reply.length)
        }else if(reply.error){
            console.log(reply.error)
        }
    }

    console.log(submissionsData)

    useEffect(()=>{
        getSubmissions();
        notsubmitted();
    },[])

    const notsubmitted = async() =>{
        var res = await fetch(`http://localhost:8080/api/students/notsubmitted/${propsData.id}`,{
            method : "GET"
        })

        var reply = await res.json()
        console.log(reply)
        if(reply){
            setSws(reply)
        }else if(reply.error){
            console.log(reply.error)
        }
    }

    console.log(sws)

    const swslist = sws.map((s)=>{
        return <h4 key={s} className="assignment_view_page_h5"> - {s} </h4>
    })

    const slist = submissionsData?.map((submission)=>(
        <div key={submission.id} className="assignment_view_box">
            {/* <h4 className="assignment_view_box_details"> {index}</h4> */}
            <h4 className="assignment_view_box_details"> {submission.studentId}</h4>

            <h4 className="assignment_view_box_details"> {submission.tos <= propsData.endTime ? "In Time" : "Late"} </h4>
            {/* <h4 className="assignment_view_box_details"> {submission.obtained_marks}</h4> */}
            {submission.obtainedMarks ? 
                <h4 className="assignment_view_box_details">{submission.obtainedMarks}</h4> :
                <h4 className="assignment_view_box_details">NA</h4>
            }

            <Link to={`/teacher/pastassignment/${propsData.id}/${submission.id}`} state={submission}>
            <button className="go_to_submission">Go to Submission</button>
            </Link>
        </div>
    ))

    const [resultReport, setResultReport] = useState([]);


    useEffect(() => {
        // Adjust the URL based on your Express server and file path
        const fileUrl = `http://localhost:8080/api/assignment/stats/${propsData.id}`;

        // Fetch the content of the code file
        fetch(fileUrl)
            .then((response) => response.json())
            .then((data) => {
                setResultReport(data[0]);
            })
            .catch((error) => {
                console.error('Error fetching Result Report:', error);
            });
    },[])

    console.log("result report", resultReport)

    return(
        <div className="assignment_view_page">
            <h3 className="assignment_view_page_h3">{propsData.name}</h3>
            <h5 className="assignment_view_page_h5">No Of Submissions : {count}</h5>
            <div>
                {slist}
            </div>

            <div>
            <h3 className="assignment_view_page_h3">SRN's of Students not submitted</h3>
            <div className="assignment_view_page_h5">
                {swslist.length === 0 ? "Everyone Submitted" : swslist}
            </div>
            <h2 className="assignment_view_page_h3">Result Reports : </h2>
            <div>
                <h3 className="assignment_view_page_h5">Maximum Marks Obtained : {resultReport[0]} / {resultReport[3]} </h3>
                <h3 className="assignment_view_page_h5">Minimum Marks Obtained : {resultReport[1]} / {resultReport[3]}</h3>
                <h3 className="assignment_view_page_h5">Average Marks Obtained : {resultReport[2]} / {resultReport[3]}</h3>
            </div>
            </div>

        </div>
    )
}