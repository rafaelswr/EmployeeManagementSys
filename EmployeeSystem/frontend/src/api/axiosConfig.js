import axios from "axios";

export default axios.create({
    baseURL:"https://5f45-2001-8a0-f75b-e00-d89c-c7c8-c8ed-7598.ngrok-free.app", 
    headers:{"ngrok-skip-browser-warning":"true"}
})