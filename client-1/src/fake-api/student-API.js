import { FAKE_API } from "./fake";

export const studentAPI = () => {
    return new Promise(res =>{
        setTimeout(() => {
            res(FAKE_API)
        },1000)
    })
}
