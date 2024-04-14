import React, { useEffect, useState } from "react";
import { useLocation, useParams } from "react-router-dom";


export default function Assignmentupload() {

    const location = useLocation();
    const propsData = location.state;

    let { s_id } = useParams();

    const [filename, setFilename] = useState("")
    const [file, setFile] = useState(null);
    const [codeContent, setCodeContent] = useState('');
    const [isS, setIsS] = useState(false);

    const handleFileChange = (event) => {
        const selectedFile = event.target.files[0];
        setFile(selectedFile);
    };

    const getAssignmentFile = async () => {
        
            var res = await fetch(`http://localhost:8080/api/assignment/file/${propsData.id}`, {
                method: "GET"
            })
            var reply = await res.json()

            if (reply) {
                    setFilename(reply[0].name)
                    console.log("filename", reply[0].name)
            }
            else if (reply.error) {
                console.log(error)
            }
    }

    const handleSubmit = async () => {

        if (isS) {
            alert("You have already submitted the assignment");
            return;
        }

        const formData = new FormData();
        formData.append("files", file);
        formData.append("s_id", s_id);
        formData.append("a_id", propsData.id);
        formData.append("end_time", propsData.endTime);
        formData.append("allow_late_submission", propsData.allowLateSubmission);

        // for (const key in propsData) {
        //     formData.append(key, propsData[key]);
        // }

        fetch("http://localhost:8080/api/student/upload", {
            method: "POST",
            body: formData,
            // headers: {
            //    'content-type' : "multipart/form-data"
            // }

        })
            .then(response => {
                if (!response.ok) {
                    if (response.status === 410) {
                        alert("Deadline has passed , you cannot submit now")
                    }
                    throw new Error(`Error! : ${response.message}`);
                }
                return response.text();
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

    const isSubmitted = async () => {
        try {
            const res = await fetch(`http://localhost:8080/api/student/submissions/${s_id}`, {
                method: 'GET',
            });
            const reply = await res.json();
            console.log("isS is", reply);

            // Check if there is a submission for the current assignment ID
            setIsS(reply.some(submission => submission.assignmentId === propsData.id));
        } catch (error) {
            console.error(error);
        }
    };

    var fileExtension = extractExtension(filename);

    useEffect(() => {
        if(propsData.inputFilesThere){
            getAssignmentFile()
        }
    }, [propsData])

    useEffect(() => {
        if (filename === "") return;
        // Adjust the URL based on your Express server and file path
        const fileUrl = `http://localhost:8080/file-content/${propsData.id}/${filename}`;

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

    useEffect(() => {
        isSubmitted();
    }, []);

    return (
        <div className="past_page">
            <h3 className="h3_past_assignments">Assignment Upload - {propsData.name} </h3>

            {isS ? <div>
                <h3 className="h3_past_assignments"> You have already submitted the Assignment</h3>
                {/* <h2>Submitted File :</h2> */}
            </div>

                :

                ""
            }

            <div>
                <h3 className="h3_past_assignments">Description :</h3>
                <h5 className="assignment_view_page_h5">{propsData.description}</h5>
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
                                src={`http://localhost:8080/files/${propsData.id}/${filename}`}
                                width="800"
                                height="400"
                            ></iframe>
                        </div>
                    }
                </>
                }




                <h3 className="h3_past_assignments">Upload Submission</h3>
                <h3 className="assignment_view_page_h5">Constraints : {propsData.constraints}</h3>
                <input type="file" className="choose_upload_assignment" onChange={handleFileChange}></input> <br></br>
                <button type="Submit" className="upload_assignment" onClick={handleSubmit}> Upload</button>
            </div>


        </div>
    )
}