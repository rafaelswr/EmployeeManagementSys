import axios from "axios";

export default axios.create({
    baseURL:"https://2406-2001-8a0-f75b-e00-b96f-63ce-8ac4-ae12.ngrok-free.app", 
    headers:{"ngrok-skip-browser-warning":"true"}
})


