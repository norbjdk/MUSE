import { Outlet } from "react-router-dom";
import Navbar from "../components/Navbar";

function MainLayout() {
    return (
        <div className={`bg-[#F0EDE8] flex flex-col min-h-screen justify-between items-center gap-8`}>
            <Navbar />
            <div className={`container mt-10`}>
                <Outlet />
            </div>
        </div>
    );
}

export default MainLayout;