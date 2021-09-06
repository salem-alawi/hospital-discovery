import Vue from "vue";
import VueRouter from "vue-router";
import HospitalDetailScreenAdmin
    from "../screen/AdminHome/HospitalListScreenAdmin/HospitalDetailScreenAdmin/HospitalDetailScreenAdmin";
import AdminHome from "../screen/AdminHome/AdminHome";
import HospitalListScreenAdmin from "../screen/AdminHome/HospitalListScreenAdmin/HospitalListScreenAdmin";
import SectionListScreenAdmin from "../screen/AdminHome/SectionListScreenAdmin/SectionListScreenAdmin";
import EditHospitalScreen
    from "@/screen/AdminHome/HospitalListScreenAdmin/HospitalDetailScreenAdmin/EditHospitalScreen/EditHospitalScreen";

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
                component: SectionListScreenAdmin
            },
            {
                path: "hospitals/:id/edit",
                name: "editHospital",
                component: EditHospitalScreen,
                props: true
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
