import * as React from "react";
import LoginForm from "../../molecules/form/LoginForm";
import BootstrapButton from "../../atoms/button/bootstrap";
import {redirectToRouteWithCurrent, redirectToRouteWithRoot} from "../../../utils/history";
import {useToasts} from "react-toast-notifications";
import {useDispatch} from "react-redux";
import {setUser} from "../../../store/Reducers/login/types/action.function.types";
import axios from "axios";

const style = require("./style/style.module.css");

const LoginContainer = () => {

    const { addToast } = useToasts();
    const dispatch = useDispatch();

    const tryLogin = (username: string, password: string) => {
        axios.post(window.location.href, {'username': username, 'password': password})
            .then((response) => {
                console.log(response);
                if (response.status >= 200 && response.status < 300) {
                    let data = response.data;
                    addToast("User: {" + username + "}  Successfully logged in", {
                        appearance: 'success',
                        autoDismiss: true,
                    });
                    dispatch(setUser({username: data.username, role: data.role, level: data.level}));
                    redirectToRouteWithRoot("/game-modes", null);
                }
            })
            .catch((error) => {
                addToast(
                    `User: ${username} failed to log in - ${error.response.data.message}`, {
                    appearance: 'error',
                    autoDismiss: true,
                });
        });
    };

    return (
        <div className={style["login-template-wrapper"]}>
            <div className={style["login-template"]}>
                <span className={style["login-template-title"]}> Code Wars </span>
                <LoginForm text={"Login"} checkCredentials={(username: string, password: string) => tryLogin(username, password)}/>
                <BootstrapButton text={"Create New Account"} click={() => redirectToRouteWithCurrent("/create", {})}/>
            </div>
        </div>
    );
};

export default LoginContainer;