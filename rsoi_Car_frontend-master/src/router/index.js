import Vue from 'vue'
import VueRouter from 'vue-router'
import CarService from "@/views/CarService";
import Car from "@/views/Car";
import IndexMenuUser from "@/views/IndexMenuUser";
import IndexMenuAdmin from "@/views/IndexMenuAdmin";
import AddCar from "@/views/AddCar";
import CarUpdate from "@/views/CarUpdate";
import Login from "@/views/Login";
import SearchCars from "@/views/SearchCars";
import ReportService from "@/views/ReportService";

Vue.use(VueRouter) //通过全局方法 Vue.use() 使用插件
const routes = [{
        path: '/',
        name: 'Login',
        component: Login,
    },
    {
        path: '/user',
        name: 'View Cars',
        component: IndexMenuUser,
        children: [

            {
                path: "/user/car",
                name: 'Cars',
                component: CarService
            }

        ]
    },

    {
        path: '/user',
        name: 'Search Cars',
        component: IndexMenuUser,
        children: [
            {
                path: "/user/searchCars",
                name: 'Search',
                component: SearchCars
            }
        ]
    },
    {
        path: '/admin',
        name: 'Admin Service',
        component: IndexMenuAdmin,
        children: [

            {
                path: "/admin/car",
                name: 'Car',
                component: Car
            },
            {
                path: "/admin/addCar",
                name: 'AddCar',
                component: AddCar
            },
            {
                path: "/admin/carUpdate",
                name: 'CarUpdate',
                component: CarUpdate
            },

            {
                path: "/admin/report",
                name: 'ReportService',
                component: ReportService
            }
        ]
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})


export default router //导出router组件


