import React ,{useState , useEffect} from "react";
import { useLocation } from "react-router-dom";

export default function Submission_view_teacher(props){

    const location = useLocation();
    const propsData = location.state;
    console.log("props", propsData)
    // console.log(propsData.id)

    const [subData, setSubData] = useState({
        assigned_marks : propsData.obtained_marks,
        assigned_feedback : propsData.feedback,
    })
    // console.log("subdata", subData)
    const [aDetails, setADetails] = useState([])
    // console.log("as_details",aDetails)

    const [codeContent, setCodeContent] = useState("");
    const [filename, setFilename] = useState("");

    const getAssignmentDetails = async() =>{
        var res =await fetch(`http://localhost:8080/api/assignment/${propsData.assignmentId}`,{
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

    useEffect(()=>{
        getAssignmentDetails();
        getSubmissionFile();
    },[])

    useEffect(() => {
        if (filename === "") return;
        // Adjust the URL based on your Express server and file path
        const fileUrl = `http://localhost:8080/file-content/${propsData.assignmentId}/${filename}`;

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

            var res = await fetch("http://localhost:8080/api/teacher/evaluate", {
                method: "PUT",
                body: JSON.stringify({...subData, ...propsData}),
                headers: {
                    'Content-Type': 'application/json', // Set the content type to JSON
                },
            });

            var reply = await res.text();

            if(reply){
                alert("Result added");
            }else if(reply.error){
                console.log(reply.error)
            } 
        } 

    const getSubmissionFile = async () => {
        var res = await fetch(`http://localhost:8080/api/submission/file/${propsData.id}`, {
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
    };

    var fileExtension = extractExtension(filename);
    
    return(
        <div className="submission_view_page">
            <h3 className="submission_view_page_h3">Submission for {aDetails.name}</h3>
            <h5 className="submission_view_page_h5">By Student {propsData.studentId}</h5>
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
                        src={`http://localhost:8080/files/${propsData.assignmentId}/${filename}`}
                        width="600"
                        height="400"
                    ></iframe>
                </div>
                }

                <h5 className="submission_view_page_h5">Max marks : {aDetails.maxMarks}</h5>

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