import axios from "axios";

export default axios.create({
    baseURL:"https://4351-2001-8a0-f75a-d300-9db0-a3b5-a66a-e222.ngrok-free.app", 
    headers:{"ngrok-skip-browser-warning":"true"}
})


