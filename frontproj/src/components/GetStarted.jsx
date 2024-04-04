import React from "react"
import ReactDOM from 'react-dom/client';
import Lottie from 'react-lottie';
import assignment_logo from "../images/assignment.json"
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom';
import Login from "./Login";

export default function GetStarted(){

    const defaultOptions = {
        loop: true,
        autoplay: true,
        animationData: assignment_logo,
        rendererSettings: {
          preserveAspectRatio: "xMidYMid slice"
        }
      };

    return(
        <div className="getstarted_page">
            <div className="info">
                <h1 className="Title">ScholarSub</h1>
                <h1 className="SubTitle"> Submissons made easy. It's a website which helps both the teachers and 
                    students in posting assignments and submitting the assignments. 
                </h1>
                <Link to="/login"><button type="Submit" className="getstarted_button">Get Started</button></Link>
            </div>
            <div className="animation_logo"> 
                <Lottie
                    options={defaultOptions}
                    height={450}
                    width={500}
                />
            </div>

        </div>
    )
}