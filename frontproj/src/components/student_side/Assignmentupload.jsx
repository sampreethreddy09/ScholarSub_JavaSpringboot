import React, { useEffect, useState } from "react";
import { useLocation, useParams } from "react-router-dom";
import Submission from "../../documents/submission.pdf"
import axios from "axios";


export default function Assignmentupload() {

    const location = useLocation();
    const propsData = location.state;
    // console.log("props",propsData)
    let { s_id } = useParams();
    // console.log("s_id",s_id)

    const [aDetails, setADetails] = React.useState([])
    const [filename, setFilename] = React.useState("")

    const [file, setFile] = React.useState(null);
    // console.log(file)

    const handleFileChange = (event) => {
        const selectedFile = event.target.files[0];
        setFile(selectedFile);
    };

    const getAssignmentDetails = async () => {
        var res = await fetch(`http://localhost:8000/student/assignments/${propsData.a_id}`, {
            method: "GET"
        })
        var reply = await res.json()
        // console.log("reply is", reply)
        if (reply) {
            setADetails(reply.assignment)
            if (reply.files) {
                setFilename(reply.files[0].name)
            }
        }
        else if (reply.error) {
            console.log(error)
        }
    }

    React.useEffect(() => {
        getAssignmentDetails()
    }, [])

    const [subStatus, setSubStatus] = React.useState(false)

    const handleSubmit = async () => {
        if (isS) {
            alert("You have already submitted the assignment");
            return;
        }
        const formData = new FormData();
        formData.append("file", file);
        formData.append("s_id", s_id);
        for (const key in propsData) {
            formData.append(key, propsData[key]);
        }


        fetch("http://localhost:8000/student/upload", {
            method: "POST",
            body: formData,
            // headers: {
            //    'content-type' : "multipart/form-data"
            // }

        })
        .then(response => {
            if (!response.ok) {
                if(response.status === 410){
                    alert("Deadline has passed , you cannot submit now")
                }
                throw new Error(`Error! : ${response.message}`);
            }
            return response.json();
          })
            .then((data) => {
                alert("Submission made successfully");
                setSubStatus(true)
            })
            .catch((error) => {
                console.error("Error submitting", error);
                // Handle the error
            });
    }

    // console.log("state", aDetails)

    const [codeContent, setCodeContent] = useState('');

    React.useEffect(() => {
        // Adjust the URL based on your Express server and file path
        const fileUrl = `http://localhost:8000/file-content/${filename}`;

        // Fetch the content of the code file
        fetch(fileUrl)
            .then((response) => response.text())
            .then((data) => {
                setCodeContent(data);
            })
            .catch((error) => {
                console.error('Error fetching file content:', error);
            });
    }, [filename]);

    const handleDownload = () => {
        const blob = new Blob([codeContent], { type: 'text/plain' });
        const url = URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = `${filename}`; // Adjust the filename as needed
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
        URL.revokeObjectURL(url);
    };

    function extractExtension(filename) {
        // Split the filename by dot (.)
        var parts = filename.split('.');

        // Get the last part of the array, which should be the extension
        var extension = parts[parts.length - 1];

        return extension;
    }
    var fileExtension = extractExtension(filename);

    //   useEffect(()=>{
    //     const isSubmitted = async () => {
    //         var res = await fetch(`http://localhost:8000/student/assignments/${propsData.a_id}`, {
    //             method: "GET"
    //         })
    //         var reply = await res.json()
    //         console.log("reply is", reply)
    //         if (reply) {
    //             setADetails(reply.assignment)
    //             if(reply.files){
    //                 setFilename(reply.files[0].name)
    //             }
    //         }
    //         else if (reply.error) {
    //             console.log(error)
    //         }
    //     }

    //     isSubmitted();
    //   })
    const [isS, setIsS] = React.useState(false)

    const isSubmitted = async () => {
        var res = await fetch(`http://localhost:8000/student/fetchresult/${s_id}/${propsData.a_id}`, {
            method: 'GET',

        })
        var reply = await res.json()
        console.log("isS is", reply.result)
        if (reply) {
            if (reply.result.length >= 1) setIsS(1)
            else if (reply.result.length == 0) setIsS(0);
        }
        else if (reply.error) {
            console.log(error)
        }
    }
    console.log("isS", isS)

    React.useEffect(() => {
        isSubmitted();
    }, [])

    console.log("file name", filename)

    return (
        <div className="past_page">
            <h3 className="h3_past_assignments">Student Assignment Upload</h3>

            {isS ? <div>
                <h3 className="h3_past_assignments"> You have already submitted the Assignment</h3>
                {/* <h2>Submitted File :</h2> */}
            </div>

                :

                ""
            }


            <div>
                <h3 className="h3_past_assignments">Description :</h3>
                <h5 className="assignment_view_page_h5">{aDetails.description}</h5>
                {(filename !== "") && <>

                    {(fileExtension !== "pdf") ?
                        <div>
                            <h2 className="h3_past_assignments">Input Code files Viewer</h2>
                            <div style={{ position: 'relative', background: 'white' }}>
                                <div
                                    style={{ // Important: Set position to relative
                                        background: 'white',
                                        color: 'black',
                                        padding: '10px',
                                        // border: '1px solid #ddd',
                                        borderRadius: '5px',
                                        maxHeight: '400px',
                                        overflow: 'auto',
                                    }}
                                >
                                    <pre>
                                        <code>{codeContent}</code>
                                    </pre>
                                    <button
                                        onClick={handleDownload}
                                        style={{
                                            position: 'absolute', // Set to absolute
                                            top: '15px',
                                            right: '30px',
                                            padding: '5px',
                                            background: '#007bff',
                                            color: 'white',
                                            border: 'none',
                                            borderRadius: '3px',
                                            cursor: 'pointer',
                                        }}
                                    >
                                        Download
                                    </button>
                                </div>

                            </div>
                        </div>
                        :
                        <div>    <h3 className="h3_past_assignments">Manual :</h3>
                            <iframe
                                title="File Viewer"
                                src={`http://localhost:8000/files/${filename}`}
                                width="800"
                                height="400"
                            ></iframe>
                        </div>
                    }
                </>
                }




                <h3 className="h3_past_assignments">Upload Submission</h3>
                <h3 className="assignment_view_page_h5">Constraints : {aDetails.constraints}</h3>
                <input type="file" className="choose_upload_assignment" onChange={handleFileChange}></input> <br></br>
                <button type="Submit" className="upload_assignment" disabled={subStatus}  onClick={handleSubmit}> Upload</button>
            </div>


        </div>
    )
}