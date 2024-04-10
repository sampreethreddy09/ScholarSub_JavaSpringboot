import React from "react"

import { Link, useLocation } from "react-router-dom";

export default function CreateAssignment() {

    const location = useLocation();
    const propsData = location.state;

    const [postData, setPostData] = React.useState({
        aname: "", 
        selectedOption: "", 
        isRytNow: true, 
        startTime: "", 
        endTime: "", 
        isInput: true, 
        description: "", 
        constraints: "", 
        allowLateSubmission: 0,
        max_marks : 0
    })
    const [file, setFile] = React.useState(null);
    const [section, setSections] = React.useState([])

    console.log(postData)
    console.log(file)
    console.log("sectiojns", section)


    const fetchSections = async () => {
        var res = await fetch(`http://localhost:8000/teacher/section/${propsData.uname}`, {
            method: "GET",
        })

        var reply = await res.json()
        console.log("reply", reply)

        if (reply) {
            setSections(reply.sections)
        } else if (reply.error) {
            alert(reply.error);
        }
    }

    React.useEffect(() => {
        fetchSections()
    }, [])


    function handleChange(event) {
        setPostData((prevPostData) => {
            const { name, value, type, checked } = event.target
            return {
                ...prevPostData,
                [name]: type === "checkbox" ? checked : value
            }
        }
        )
    }

    const handleFileChange = (event) => {
        const selectedFile = event.target.files[0];
        setFile(selectedFile);
    };

    const handleSubmit = () => {

        const formData = new FormData();
        formData.append("file", file);
    
        // Append other form data as well
        for (const key in postData) {
          formData.append(key, postData[key]);
        }
    

        console.log("formdata", formData.file)

        fetch("http://localhost:8000/teacher/CreateAssignment", {
            method: "POST",
            body: formData,
            // headers: {
            //    'content-type' : "multipart/form-data"
            // }

        })
        .then(response => {
            if (!response.ok) {
              throw new Error(`Error! : ${response.message}`);
            }
            return response.json();
          })
            .then((data) => {
                alert("Assignment uploaded successfully");
            })
            .catch((error) => {
                console.error("Error uploading assignment", error);
                // Handle the error
            });
    };

    // const sectionList = section.map((s) => (
    //     <option key={s.sec_id} value={s.sec_id}>
    //         {s.name}
    //     </option>
    // ));

    return (
        <div className="post_page">
            <h3 className="h3_post_assignment"> Assignment Name </h3>
            <input name="aname" type="text" className="inputs"
                placeholder="Name of the Assignment"
                onChange={handleChange}
            />
            <h3 className="h3_post_assignment"> Section </h3>
            <select name="selectedOption" value={postData.selectedOption} onChange={handleChange}>
                <option value="select">Select a section</option>
                {section.map((sectionItem) => (
                    <option key={sectionItem.sec_id} value={sectionItem.sec_id}>
                        {sectionItem.name}
                    </option>
                ))}
            </select>
            <br></br><br></br>


            <label htmlFor="isRytNow" className="h3_post_assignment"> Schedule right now ? </label>
            <input id="isRytNow" type="checkbox" checked={postData.isRytNow} onChange={handleChange} name="isRytNow"></input> <br></br>
            {!postData.isRytNow ? <h3 className="h3_post_assignment">Start Time </h3> : ""}
            {!postData.isRytNow ? <input type="text" placeholder="ex : 2024-10-11 09:00:00.000" name="startTime" onChange={handleChange}></input> : ""}


            <h3 className="h3_post_assignment">End Time </h3>
            <input type="text" placeholder="ex : 2024-10-11 09:00:00" name="endTime" onChange={handleChange}></input>


            <br></br>
            <br></br>
            <label htmlFor="isInput" className="h3_post_assignment"> Are there any Manuals/Description Files ? </label>
            <input id="isInput" type="checkbox" checked={postData.isInput} onChange={handleChange} name="isInput"></input> <br></br>
            {postData.isInput ? <h3 className="h3_post_assignment">Add File</h3> : ""}
            {postData.isInput ? <input type="file" onChange={handleFileChange} /> : ""}

            <br></br>


            <h3 className="h3_post_assignment"> Asignment Description</h3>
            <textarea name="description" placeholder="Enter description here" rows={4} cols={50} onChange={handleChange}></textarea>

            <h3 className="h3_post_assignment"> Submission Constraints</h3>
            <textarea name="constraints" placeholder="Enter submission constraints here" rows={4} cols={50} onChange={handleChange}></textarea>
            

            <h3 className="h3_post_assignment"> Max Marks </h3>
            <input name="max_marks" type="text" className="input_marks"
                placeholder=""
                onChange={handleChange}
            />
            <br></br>
            <br></br>

            <label htmlFor="allowlate" className="h3_post_assignment"> Allow Late Submission ? </label>
            <input id="allowlate" type="checkbox" checked={postData.allowLateSubmission} onChange={handleChange} name="allowLateSubmission"></input> 
            
            <br></br>
            <br></br>

            <button type="submit" className="login_button" onClick={handleSubmit}>
                Post
            </button>
        </div>
    )
}