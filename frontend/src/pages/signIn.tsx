import axios from "axios";
import React from "react";
import { useEffect, useState } from "react";
import { Button, Container, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";




const SignIn=()=>{
    const[email,setEmail]=useState('');
    const [password,setPassword]=useState('');
    const navigate=useNavigate();

   
    const handleLogin=(e)=>{
        e.preventDefault();
         axios.post("http://localhost:8080/comptes/verifierConnexion",{email,password})
         .then(response=>{
         if(response.status===200){
            localStorage.setItem("isAuth", true);
            localStorage.setItem("role",response.data.role)
            localStorage.setItem("chefId", response.data.id);  
            localStorage.setItem("compteId",response.data.id);
            navigate("/");
         }
        })
         .catch(()=>
         {
            localStorage.setItem("isAuth",false);
            alert("email et/ou password incorrect");
         })
    };

    return(
        <Container className="d-flex  flex-column align-items-center vh-100">
          <img src="/logoBleu.png" style={{width:'200px',marginBottom: '20px'}} alt=""Nihel Logo/>
   <br />
   <h2 className="text-center mb-3" style={{fontFamily:'Helvetica',fontSize:'30px' }} > Bienvenue </h2>
   <h2 className="text-center mb-3" style={{fontFamily:'Helvetica' ,fontSize:'30px'}} > Connectez-vous! </h2>

    <br />
    <div className="bg-light p-4 rounded border shadow-sm" style={{width:'30%'}} >
      <Form onSubmit={handleLogin}>
       <Form.Group className="mb-3">
       <Form.Label style={{fontFamily:'Helvetica'}}>Email</Form.Label>
       <Form.Control 
       type="email"
       name="email"
       onChange={(e)=>setEmail(e.target.value)}
       />
       </Form.Group>

       <Form.Group>
       <Form.Label style={{fontFamily:'Helvetica'}}>Password</Form.Label>
       <Form.Control 
       type="password"
       name="password"
       onChange={(e)=>setPassword(e.target.value)}
       style={{fontSize:'1rem'}}
       />
       </Form.Group>
         <div className="text-center mt-3">
       <Button  type="submit" style={{ backgroundColor: "#1C1C63", fontSize: '0.875rem', padding: '0.375rem 0.75rem' ,width:'50%'}} >Connexion</Button>
       </div>
    </Form>
    </div>
    </Container>
    )
}
export default SignIn;