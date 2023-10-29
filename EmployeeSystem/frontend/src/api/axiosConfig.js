
import axios from "axios";

export default axios.create({
    baseURL:"https://e181-2001-8a0-f76b-1400-19be-be16-f0ad-dee8.ngrok-free.app", 
    headers:{"ngrok-skip-browser-warning":"true"}
})