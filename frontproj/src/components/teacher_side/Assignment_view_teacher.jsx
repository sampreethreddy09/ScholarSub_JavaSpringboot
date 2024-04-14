import React, { useState ,useEffect } from "react";
import { Link} from 'react-router-dom';
import { useLocation } from "react-router-dom";
import { CsvToHtmlTable } from 'react-csv-to-table'

export default function Assignment_view_teacher(props){

    const location = useLocation();
    const propsData = location.state;
    // console.log(propsData)
    console.log("a_id",propsData.id)

    const [submissionsData, setSubmissionsData] = useState([])
    // const [csv, setCsv] = useState([])
    const [sws, setSws] = useState([])
    const [count, setCount] = useState()
    

    const getSubmissions = async() =>{
        var res = await fetch(`http://localhost:8080/api/teacher/submissions/${propsData.id}`,{
            method : "GET"
        })

        var reply = await res.json()
        console.log("reply",reply)
        if(reply){
            setSubmissionsData(reply.submissions)
            setCount(reply.count)
        }else if(reply.error){
            console.log(reply.error)
        }
    }

    useEffect(()=>{
        getSubmissions()
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

    useEffect(()=>{
        notsubmitted()
    },[])

    console.log(sws)

    const swslist = sws.map((s)=>{
        return <h4 key={s} className="assignment_view_page_h5"> - {s} </h4>
    })

    const slist = submissionsData.map((submission)=>(
        <div key={submission.id} className="assignment_view_box">
            <h4 className="assignment_view_box_details"> {submission.id}</h4>
            <h4 className="assignment_view_box_details"> {submission.studentId}</h4>

            <h4 className="assignment_view_box_details"> {submission.tos <= propsData.endTime ? "In Time" : "Late"} </h4>
            {/* <h4 className="assignment_view_box_details"> {submission.obtained_marks}</h4> */}
            {submission.obtained_marks ? 
                <h4 className="assignment_view_box_details">{submission.obtained_marks}</h4> :
                <h4 className="assignment_view_box_details">NA</h4>
            }

            <Link to={`/teacher/pastassignment/${propsData.a_id}/${submission.sub_id}`} state={submission}>
            <button className="go_to_submission">Go to Submission</button>
            </Link>
        </div>
    ))

    const [codeContent, setCodeContent] = useState('');
    const [resultReport, setResultReport] = useState({});

    // useEffect(() => {
    //     // Adjust the URL based on your Express server and file path
    //     const fileUrl = `http://localhost:8000/plagreport/output.csv`;

    //     // Fetch the content of the code file
    //     fetch(fileUrl)
    //         .then((response) => response.text())
    //         .then((data) => {
    //             setCodeContent(data);
    //         })
    //         .catch((error) => {
    //             console.error('Error fetching file content:', error);
    //         });
    // }, []);


    const handlePlag= async()=>{
        var res = await fetch(`http://localhost:8000/teacher/plagiarism/${propsData.id}`,{
            method: "GET",
        })

        if(!res.ok){
            setCodeContent(res.message)
        }

        else{

            var reply = await res.json()
            console.log(reply)
        
            console.log("plag check done")
            // Adjust the URL based on your Express server and file path
            const fileUrl = `http://localhost:8000/plagreport/output.csv`;

            // Fetch the content of the code file
            fetch(fileUrl)
                .then((response) => response.text())
                .then((data) => {
                    setCodeContent(data);
                })
                .catch((error) => {
                    console.error('Error fetching file content:', error);
                });
        }
    }

    // useEffect(() => {
    //     // Adjust the URL based on your Express server and file path
    //     const fileUrl = `http://localhost:8000/teacher/resultreport/${propsData.a_id}`;

    //     // Fetch the content of the code file
    //     fetch(fileUrl)
    //         .then((response) => response.json())
    //         .then((data) => {
    //             setResultReport(data[0]);
    //         })
    //         .catch((error) => {
    //             console.error('Error fetching Result Report:', error);
    //         });
    // },[])

    console.log("result report", resultReport)

    return(
        <div className="assignment_view_page">
            <h3 className="assignment_view_page_h3">{propsData.name}</h3>
            <h5 className="assignment_view_page_h5">No Of Submissions : {count}</h5>
            <div>
                {slist}
            </div>

            <button className="plag_button" onClick={handlePlag}> Get Plagiarism Report</button>

            { codeContent && <CsvToHtmlTable data = {codeContent} csvDelimiter="," tableClassName="table table-striped table-hover"
                className="csvTable"
            />}

            <div>
            <h3 className="assignment_view_page_h3">SRN's of Students not submitted</h3>
            <div className="assignment_view_page_h5">
                {swslist.length === 0 ? "Everyone Submitted" : swslist}
            </div>
            <h2 className="assignment_view_page_h3">Result Reports : </h2>
            <div>
                <h3 className="assignment_view_page_h5">Maximum Marks Obtained : {resultReport.max_mark}/{resultReport.total_marks} </h3>
                <h3 className="assignment_view_page_h5">Minimum Marks Obtained : {resultReport.min_marks}/{resultReport.total_marks}</h3>
                <h3 className="assignment_view_page_h5">Average Marks Obtained : {resultReport.avg_marks}/{resultReport.total_marks}</h3>
            </div>
            </div>

        </div>
    )
}