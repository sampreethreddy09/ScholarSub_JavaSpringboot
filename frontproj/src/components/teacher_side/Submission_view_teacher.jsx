import React from "react";
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom';
import { useLocation } from "react-router-dom";

export default function Submission_view_teacher(props){

    const location = useLocation();
    const propsData = location.state;
    // console.log("props", propsData)
    // console.log(propsData.id)

    const [subData, setSubData] = React.useState({
        assigned_marks : propsData.obtained_marks,
        assigned_feedback : propsData.feedback,
    })
    // console.log("subdata", subData)
    const [aDetails, setADetails] = React.useState([])
    // console.log("as_details",aDetails)

    const [codeContent, setCodeContent] = React.useState("");
    const [filename, setFilename] = React.useState("");

    const getAssignmentDetails = async() =>{
        var res =await fetch(`http://localhost:8000/student/assignments/${propsData.a_id}`,{
            method : "GET"
        })
        var reply =await res.json()
        // console.log("reply is",reply.assignment)
        if(reply){
            setADetails(reply.assignment)
        }    
        else if(reply.error){
            console.log(error)
        }
    }

    React.useEffect(()=>{
        getAssignmentDetails()
    },[])


    function handleChange(event){
        setSubData((prevSubData)=>{
            return{
                ...prevSubData,
                [event.target.name] : event.target.value
            }
        })
    }

    const handleSubmit = async() =>{
        if(subData.assigned_marks > aDetails.max_marks){
            alert("Entered marks should be less than Max marks")
            return;
        }
        
        // const formData = new FormData();
        // for (const key in propsData) {
        //     formData.append(key, propsData[key]);
        // }
        // for (const key in subData) {
        //     formData.append(key, subData[key]);
        // }

        // console.log(formData)
        console.log(subData, propsData);

        try {
            await fetch("http://localhost:8000/teacher/evaluate", {
                method: "POST",
                body: JSON.stringify({...subData, ...propsData}),
                headers: {
                    'Content-Type': 'application/json', // Set the content type to JSON
                },
            });

            alert("Result added");
        } catch (error) {
            console.error("Error adding result:", error);
            alert("Error adding result");
        }
    }

    React.useEffect(() => {
        // Adjust the URL based on your Express server and file path
        const fileUrl = `http://localhost:8000/teacher/submission-file-content/${propsData.a_id}/${propsData.sub_id}`;

        // Fetch the content of the code file
        fetch(fileUrl)
        .then(response => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            return response.json(); // Assuming your server always sends JSON
          })
          .then(data => {
            if (data.filename) {
              // Handle JSON response
              // Set your jsonState with data.filename
            //   console.log('Received filename:', data.filename);
              setFilename(data.filename)
            } 
            if(data.code) {
              // Handle file content response
              // Set your fileContentState with the file content
            //   console.log('Received File Content:', data.code);
              setCodeContent(data.code)
            }
          })
          .catch(error => {
            console.error('Error:', error);
            // Handle error state if necessary
          });
    }, []);

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
    
    return(
        <div className="submission_view_page">
            <h3 className="submission_view_page_h3">Submission of {propsData.name}</h3>
            <h5 className="submission_view_page_h5">SRN : {propsData.s_id}</h5>
            <h3 className="h3_post_assignment">Submitted File : </h3>
            { (fileExtension !== "pdf") ?
                    <div>
                    <p>{filename}</p>
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
                <div>    <p>{filename}</p>
                    <iframe
                        title="File Viewer"
                        src={`http://localhost:8000/files/${propsData.a_id}/${filename}`}
                        width="600"
                        height="400"
                    ></iframe>
                </div>
                }

                { propsData.obtained_marks ? <h5 className="submission_view_page_h5">Assigned Marks : {propsData.obtained_marks} </h5> : <h5 className="submission_view_page_h5">Assign Marks : </h5>}
                <input className="input_marks" type="text" value={subData.assigned_marks} onChange={handleChange}
                    name = "assigned_marks" placeholder="Enter Marks Here"
                />
                {propsData.feedback ? <h5 className="submission_view_page_h5">Provided Feedback : {propsData.feedback}</h5> 
                : <h5 className="submission_view_page_h5">Feedback if any : </h5>}
                <textarea className="input_feedback" rows={4} cols={50} value={subData.assigned_feedback} onChange={handleChange}
                    name = "assigned_feedback" placeholder="Enter Feedback Here"
                />
                <br></br>
                <button type="submit" className="input_teacher_form" onClick={handleSubmit}>
                    Submit
                </button>

        </div>
    )
}