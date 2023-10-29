
import axios from "axios";

export default axios.create({
    baseURL:"https://d182-2001-8a0-f76b-1400-de1f-a414-b95-6c7f.ngrok-free.app", 
    headers:{"ngrok-skip-browser-warning":"true"}
})