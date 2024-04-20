import React from "react"

import { useLocation } from "react-router-dom";
import "../css/createassgn.css"

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
        max_marks: 0
    })
    const [file, setFile] = React.useState(null);
    const [section, setSections] = React.useState([])

    console.log(postData)
    console.log(file)
    console.log("sectiojns", section)


    const fetchSections = async () => {
        var res = await fetch(`http://localhost:8080/api/sections/${propsData.uname}`, {
            method: "GET",
        })

        var reply = await res.json()
        console.log("reply", reply)

        if (reply) {
            setSections(reply)
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


        console.log("formdata", formData)

        fetch("http://localhost:8080/api/teacher/postassignment", {
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
                return response.text();
            })
            .then((data) => {
                alert("Assignment uploaded successfully");
            })
            .catch((error) => {
                console.error("Error uploading assignment", error);
                // Handle the error
            });
    };

    const autoExpand = (event) => {
        const textarea = event.target;
        textarea.style.height = 'auto'; // Reset textarea height to allow it to shrink back if necessary
        textarea.style.height = (textarea.scrollHeight + 10) + 'px'; // Set the new height to fit the content, plus some padding
    };

    // const sectionList = section.map((s) => (
    //     <option key={s.sec_id} value={s.sec_id}>
    //         {s.name}
    //     </option>
    // ));

    return (
        <main className="toppage">
            <div className="assgnpage">
                <h1 className="centerheading">Create Assignment</h1>
                <h3 className="h3_post_assignment"> Assignment Name </h3>
                <input name="aname" type="text" className="inputbox"
                    placeholder="Name of the Assignment"
                    onChange={handleChange}
                />
                <h3 className="h3_post_assignment"> Section </h3>
                <select name="selectedOption" value={postData.selectedOption} onChange={handleChange}>
                    <option value="select">Select a section</option>
                    {section.map((sectionItem) => (
                        <option key={sectionItem.secId} value={sectionItem.secId}>
                            {sectionItem.secName}
                        </option>
                    ))}
                </select>
                <br></br><br></br>



                <h3 className="checkboxes"> Schedule right now ? </h3>
                <input id="isRytNow" type="checkbox" checked={postData.isRytNow} onChange={handleChange} className="checkbx" name="isRytNow"></input> <br></br>
                {!postData.isRytNow ? <h3 className="h3_post_assignment">Start Time </h3> : ""}
                {!postData.isRytNow ? <input type="text" placeholder="ex : 2024-10-11 09:00:00" name="startTime" className="inputbox" onChange={handleChange}></input> : ""}


                <h3 className="h3_post_assignment">End Time </h3>
                <input type="text" placeholder="ex : 2024-10-11 09:00:00" name="endTime" className="inputbox" onChange={handleChange}></input>


                <br></br>
                <br></br>
                <h3 className="checkboxes"> Are there any Manuals/Description Files ? </h3>
                <input id="isInput" type="checkbox" checked={postData.isInput} onChange={handleChange} className="checkbx" name="isInput"></input> <br></br>
                {postData.isInput ? <h3 className="h3_post_assignment">Add File</h3> : ""}
                {postData.isInput ? <input type="file" onChange={handleFileChange} /> : ""}

                <br></br>


                <h3 className="h3_post_assignment"> Assignment Description</h3>
                <textarea name="description" placeholder="Enter description here" onInput={autoExpand} className="textarea" onChange={handleChange}></textarea>

                <h3 className="h3_post_assignment"> Submission Constraints</h3>
                <textarea name="constraints" placeholder="Enter submission constraints here" onInput={autoExpand} className="textarea" onChange={handleChange}></textarea>


                <h3 className="h3_post_assignment"> Max Marks </h3>
                <input name="max_marks" type="text" className="inputbox"
                    placeholder=""
                    onChange={handleChange}
                />
                <br></br>
                <br></br>

                <h3 className="checkboxes"> Allow Late Submission ? </h3>
                <input id="allowlate" type="checkbox" checked={postData.allowLateSubmission} className="checkbx" onChange={handleChange} name="allowLateSubmission"></input>

                <br></br>
                <br></br>

                <div className="buttondiv">
                    <button type="submit" className="postbutton" onClick={handleSubmit}>
                        Post
                    </button>
                </div>
            </div>
        </main>
    )
}