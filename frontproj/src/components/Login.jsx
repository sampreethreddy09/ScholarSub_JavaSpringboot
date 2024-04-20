// Login.jsx

import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./css/Login.css" 

export default function Login() {
  const [formData, setFormData] = useState({
    uname: "",
    pswd: "",
    role: -1,
  });

  function handleChange(event) {
    const name = event.target.name;
    const value =
      name === "role"
        ? parseInt(event.target.getAttribute("data-value"), 10)
        : event.target.value;

    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  }

  const navigate = useNavigate();

  const handleSubmit = async () => {
    try {
      console.log(formData )
      var res = await fetch("http://localhost:8080/login", {
        method: "POST",
        body: JSON.stringify(formData),
        headers: {
          'Content-Type': 'application/json',
        },
      });

      // var reply = await res.json()
      const data = await res.text(); // Parse response body as JSON
      console.log(data); // Log the parsed JSON data

      if (res.ok) {
        alert("Login Successful")
        navigate(`/${role}`, { state: formData });
        return;
      } 
      else if (res.status === 401) {
        alert(data);
      }
    } catch (e) {
      alert(`Login Failed ${e}`);
    }
  };

  let role = formData.role ?  "teacher" : "student" ;

  return (

    <div className="login_page">
      <div className="login_container">
        <div className="title">
          <h1 className="header">ScholarSub</h1>
        </div>

        <div className="credential_input">
          <div className="role">
            <button
              name="role"
              data-value={0}
              className={`role_button ${formData.role === 0 && "selected"}`}
              onClick={handleChange}
            >
              Student
            </button>
            <button
              name="role"
              data-value={1}
              className={`role_button ${formData.role === 1 && "selected"}`}
              onClick={handleChange}
            >
              Teacher
            </button>
          </div>
          <div className="uname_pswd">
            <h3 className="sidetitle">Username ( SRN / TRN )</h3>
            <input
              type="text"
              className="inputs"
              name="uname"
              placeholder="PES1UGxxyyzzz"
              onChange={handleChange}
            />

            <h3 className="sidetitle">Password</h3>
            <input
              type="password"
              className="inputs"
              name="pswd"
              placeholder="Password"
              onChange={handleChange}
            />
          </div>
        </div>
        <div className="outer_login_button">
          <button type="submit" className="login_button" onClick={handleSubmit}>
            Login
          </button>
        </div>
      </div>
    </div>

  );
}
