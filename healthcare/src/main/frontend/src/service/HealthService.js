export async function getCategories(){
    let categories = "";
    try {
        const response = await fetch("http://localhost:8081/api/health/getCategories",
            {
                method: "GET",
                headers:{
                    "Authorization":window.sessionStorage.getItem("token")
                }
            }
        );
        if(response.ok){
            const data = (await response.text()).toString();
            categories =  data.split(",");
        }
    } catch (error) {
        console.log(error);
    }
    return categories;
}