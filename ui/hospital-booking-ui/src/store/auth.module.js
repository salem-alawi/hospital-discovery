// import AuthService from '../services/auth.service';
// import axios from 'axios'
// const user = JSON.parse(localStorage.getItem('user'));
// const token = JSON.parse(localStorage.getItem('token'));
// const initialState = user
//     ? {loggedIn: true, user, token}
//     : {loggedIn: false, user: null, token: null};
//
//
// if(token){
//     axios.interceptors.request.use(
//         config => {
//             config.headers = {
//                 'x-authorization': `Bearer ${token}`
//             };
//             return config;
//         });
//
// }
// export const auth = {
//     state: initialState,
//     actions: {
//         loginSuccessAction({commit}, data) {
//             commit('loginSuccess', data);
//         },
//         logout({commit}) {
//             localStorage.removeItem('user');
//             localStorage.removeItem('token');
//             commit('logout');
//         },
//         register({commit}, user) {
//             return AuthService.register(user).then(
//                 response => {
//                     commit('registerSuccess');
//                     return Promise.resolve(response.data);
//                 },
//                 error => {
//                     commit('registerFailure');
//                     return Promise.reject(error);
//                 }
//             );
//         }
//     },
//     getters: {
//         isLogin: (state) => state.loggedIn,
//         token: (state) => state.token
//     },
//     mutations: {
//         loginSuccess(state, data) {
//             state.loggedIn = true;
//             state.user = data.user;
//             state.token = data.token;
//         },
//         loginFailure(state) {
//             state.status.loggedIn = false;
//             state.user = null;
//         },
//         logout(state) {
//             state.loggedIn = false;
//             state.user = null;
//             state.token = null;
//         },
//         registerSuccess(state) {
//             state.status.loggedIn = false;
//         },
//         registerFailure(state) {
//             state.status.loggedIn = false;
//         }
//     }
// };
