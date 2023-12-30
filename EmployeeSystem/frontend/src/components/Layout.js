import {Outlet} from "react-router-dom";
import React from "react";

const Layout = () => {
    return (
        <main style={{margin:"0 auto", padding:20, maxWidth:1280,borderColor:"red", borderWidth:2, borderStyle:"dotted"}}>
            <Outlet/>
        </main>
    )

}

export default Layout;