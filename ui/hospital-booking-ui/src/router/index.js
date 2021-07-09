import Vue from "vue";
import VueRouter from "vue-router";
import HospitalDetailScreenAdmin from "../screen/AdminHome/HospitalDetailScreenAdmin/HospitalDetailScreenAdmin";
import AdminHome from "../screen/AdminHome/AdminHome";
import HospitalListScreenAdmin from "../screen/AdminHome/HospitalListScreenAdmin/HospitalListScreenAdmin";
import SectionScreenAdmin from "../screen/AdminHome/SectionScreenAdmin/SectionScreenAdmin";

Vue.use(VueRouter);

const routes = [
    {
        path: "/",
        name: "Home",
        redirect: '/admin/hospitals',
    },
    {
        path: '/admin',
        name: 'Admin Home',
        component: AdminHome,
        children: [
            {
                path: 'sections',
                name: 'sections',
                component: SectionScreenAdmin
            },
            {
                path: "hospitals/:id",
                name: "hospitalsDetail",
                component: HospitalDetailScreenAdmin,
                props: true
            },
            {
                path: 'hospitals',
                name: 'admin_hospital_list',
                component: HospitalListScreenAdmin
            }
        ]
    },

];
const router = new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes
});

export default router;
