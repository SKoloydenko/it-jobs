import React, {useEffect} from 'react';
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import {useActions} from "./hooks/useActions";
import {useTypedSelector} from "./hooks/useTypedSelector";
import {LandingPage, LoginPage, NotFoundPage, RegisterPage} from "./pages";
import {Loader} from "./components";

const App: React.FC = () => {
    const {user, loading} = useTypedSelector((state) => state.user);
    const {getCurrentUser} = useActions();

    useEffect(() => {
        if (!user) {
            getCurrentUser();
        }
    }, []);

    if (loading) {
        return (
            <div style={{ position: "absolute", top: "40%", left: "50%" }}>
                <Loader />
            </div>
        );
    }

    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<LandingPage/>}/>
                <Route
                    path="/login"
                    element={!user ? <LoginPage/> : <Navigate to="/"/>}
                />
                <Route
                    path="/register"
                    element={!user ? <RegisterPage/> : <Navigate to="/"/>}
                />
                <Route path="*" element={<NotFoundPage/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
