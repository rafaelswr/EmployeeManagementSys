import {Outlet} from "react-router-dom";
import React from "react";

const Layout = () => {
    return (
        <main style={{margin:50}}>
            <Outlet/>
        </main>

    )

}

export default Layout;