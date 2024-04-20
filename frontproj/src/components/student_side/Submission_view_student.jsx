import React, { useState, useEffect } from "react";
import { useLocation, useParams } from "react-router-dom";
import "../css/submissionS.css"

export default function Submission_View_Student() {

    const location = useLocation();
    const propsData = location.state;
    console.log(propsData)

    let { s_id } = useParams();
    console.log("s_id", s_id)

    const [result, setResult] = useState([])
    console.log("result,", result)

    const fetchResult = async () => {
        try {
            var res = await fetch(`http://localhost:8080/api/fetchresult/${s_id}/${propsData.id}`, {
                method: "GET",
                // headers: {
                //     'Content-Type': 'application/json', // Set the content type to JSON
                // },
            });
            var reply = await res.json()
            console.log("reply", reply[0]);
            setResult(reply[0])
        } catch (error) {
            console.error("Error adding result:", error);
            // alert("Error fetching result");
        }
    }

    useEffect(() => {
        fetchResult();
    }, [])



    return (
        <div className="post_page">
            <h2>{propsData.name}</h2>
            {
                result &&
                <div className="evalclass">
                    <h3>Evaluation Details -</h3>
                    <h5 className="h3_post_assignment">Feedback given : {!result.feedback ? "Not Given any" : result.feedback} </h5>
                    <h5 className="h3_post_assignment">Marks Assigned : {!result.obtainedMarks ? "Not Assigned yet" : result.obtainedMarks + "/" + propsData.maxMarks}</h5>
                </div>
            }

            <div className="assgnclass">
                <h3>Assignment Details -</h3>
                <h5 className="h3_post_assignment"> Description : {propsData.description} </h5> 
            </div>

            {/* <h3 className="h3_post_assignment"> Constraints</h3>
            {propsData.constraints} */}

        </div>
    )
}