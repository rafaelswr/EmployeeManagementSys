
import axios from "axios";

export default axios.create({
    baseURL:"https://c3f9-2001-8a0-f76b-1400-1460-12d0-b063-6fa4.ngrok-free.app", 
    headers:{"ngrok-skip-browser-warning":"true"}
})