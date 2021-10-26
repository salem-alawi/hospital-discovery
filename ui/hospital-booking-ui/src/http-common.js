import axios from "axios";

export default axios.create({
    baseURL: "http://52.36.50.145:8444/",
    headers: {
        "Content-type": "application/json"
    }
});
