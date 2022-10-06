import { Route, Routes } from "react-router-dom";

import Login from "pages/login/Login";

const RootRouter = () => {
    return (
        <Routes>
            <Route path="/" element={<Login />}></Route>
        </Routes>
    );
}

export default RootRouter;