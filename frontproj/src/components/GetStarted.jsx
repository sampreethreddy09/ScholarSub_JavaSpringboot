import React from "react"
import Lottie from 'react-lottie';
import assignment_logo from "../images/assignment.json"
import { Link} from 'react-router-dom';

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
                <h1 className="SubTitle"> Assignment Submissons made easy! We are the platform which facilitates teachers with creating and evaluating assignments and 
                    students in submitting the assignments and viewing the results 
                </h1>
                
            </div>
            <div className="animation_logo"> 
                <Lottie
                    options={defaultOptions}
                    height={450}
                    width={500}
                />
            </div>
            <Link to="/login"><button type="Submit" className="getstarted_button">Get Started</button></Link>

        </div>
    )
}