
import { initializeApp } from 'firebase/app'; 
import { getStorage } from 'firebase/storage';

const firebaseConfig = {
  apiKey: "AIzaSyBlk_nJxXvtcv-quaGUS9012wf9Itn5E3Y",
  authDomain: "employeesmanagementsys.firebaseapp.com",
  projectId: "employeesmanagementsys",
  storageBucket: "employeesmanagementsys.appspot.com",
  messagingSenderId: "278083587814",
  appId: "1:278083587814:web:3bc89d92beef00d53f3527"
};

export const app = initializeApp(firebaseConfig);
export const storage = getStorage(app);