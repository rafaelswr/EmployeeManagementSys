
import { ref, uploadBytes, getDownloadURL, deleteObject, getStorage } from "firebase/storage";
import { app } from "./firebaseConfig";
import api from "../api/axiosConfig.js";

const storage = getStorage(app); 


const uploadPhotoPerfil = async (photo, fn, ln)=>{
    try{
       const imagesRef = ref(storage, `employees/${fn}_${ln}`);
       await uploadBytes(imagesRef, photo); 
       console.log("Photo successfully uploaded");
    } catch (err) {
        console.log("error on upload employee photo in storage: ", err.message);
    }
    
} 

const getPhotoPerfil = async (onSuccess, fn, ln) => {
    try {
        const storageRef = ref(storage, `employees/${fn}_${ln}`);
        const url  = await getDownloadURL(storageRef); 
        console.log("Imagem obtida com sucesso");
        onSuccess(url); 
    
    } catch (error) {
        console.error("Error on getting photo: ", error.message); 
    }

}

const deletePhotoPerfil = async (fn, ln)=>{
    try {
        const storageRef = ref(storage, `employees/${fn}_${ln}`); 
        await deleteObject(storageRef).then(()=>{
            console.log("photo removed");
        })
    } catch (error) {
        console.log("Error on deleting photo:", error.message); 
    }
}

const onDeleteEmployee = async (employee)=>{
    try {
       deletePhotoPerfil(employee.firstName, employee.lastName);
       await api.delete(`/employees/delete/${employee.employeeId}`);
       console.log("employee deleted successfully");
    } catch (error) {
        console.error("Error on deleting employee: ", error.message);
    }
}


export {
    uploadPhotoPerfil,
    onDeleteEmployee,
    getPhotoPerfil, 

}

