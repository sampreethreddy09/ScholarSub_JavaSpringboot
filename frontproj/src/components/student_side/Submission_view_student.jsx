import React from "react";
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom';
import { useLocation, useParams } from "react-router-dom";

export default function Submission_View_Student() {

    const location = useLocation();
    const propsData = location.state;
    console.log(propsData)

    let { s_id } = useParams();
    console.log("s_id", s_id)

    const [result, setResult] = React.useState([])
    console.log("result,", result)

    const fetchResult = async () => {
        try {
            var res = await fetch(`http://localhost:8000/student/fetchresult/${s_id}/${propsData.a_id}`, {
                method: "GET",
                // headers: {
                //     'Content-Type': 'application/json', // Set the content type to JSON
                // },
            });
            var reply = await res.json()
            console.log("reply", reply.result);
            console.log("Result fetched");
            setResult(reply.result[0])
        } catch (error) {
            console.error("Error adding result:", error);
            alert("Error fetching result");
        }
    }

    React.useEffect(() => {
        fetchResult();
    }, [])



    return (
        <div className="post_page">
            {
                result &&
                <>
                    <h1>Evaluation Details :</h1>
                    <h3 className="h3_post_assignment">Marks Assigned : {!result.obtained_marks ? "Not Assigned yet" : result.obtained_marks + "/" +result.max_marks}</h3>
                    <h3 className="h3_post_assignment">Feedback given : {!result.feedback ? "Not Given any":result.feedback} </h3>
                </>
            }

            <h1>Assignment Details :</h1>
            <h3 className="h3_post_assignment"> Description : </h3> {propsData.description}

            {/* <h3 className="h3_post_assignment"> Constraints</h3>
            {propsData.constraints} */}

        </div>
    )
}