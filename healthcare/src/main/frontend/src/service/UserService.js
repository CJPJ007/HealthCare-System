export async function fetchUserDetails(email,password){
    const response = await fetch("http://localhost:8081/api/users/getUser",
        {
            method:"POST",
            body:JSON.stringify({
                "email":email
            }),
            headers:{
                "Authorization":`Basic ${window.btoa(`${email}:${password}`)}`,
                "Content-type":"application/json"
            }
        }
    );
    if(response.ok){
        const result = await response.json();
        window.sessionStorage.setItem("token",response.headers.get("Authorization"));
        console.log("Result of login is : {}",result);
        return result;
    }
    console.log("Failure received from repsonse");
}

export async function registerUserDetails(userDetails){
    const response = await fetch("http://localhost:8081/api/users/createUser",
        {
            method:"POST",
            body:JSON.stringify(userDetails),
            headers:{
                "Content-type":"application/json"
            }
        }
    );
    if(response.ok){
        const result = await response.json();
        window.sessionStorage.setItem("token",response.headers.get("Authorization"));
        console.log("Result of login is : {}",result);
        return result;
    }
    console.log("Failure received from repsonse");
}