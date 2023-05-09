"use strict";

import Vue from 'vue';
import { MessageBox, Message } from 'element-ui'
// import router from '../router/index'
import axios from "axios";
import router from '../router';

// Full config:  https://github.com/axios/axios#request-config
// axios.defaults.baseURL = process.env.baseURL || process.env.apiUrl || '';
// axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

let config = {
    // headers: {
    //     // 'Content-Type': "application/json;charset=utf-8",
    'Access-control-Allow-Origin': "Origin",
    'Access-Control-Allow-Headers': "Access-Control-Allow-Headers",
    // },
    // baseURL: process.env.baseURL || process.env.apiUrl || ""
    timeout: 60 * 1000, // Timeout
    // withCredentials: true, // Check cross-site Access-Control
};

const _axios = axios.create(config);

_axios.interceptors.request.use(
    function(config) {
        // Do something before request is sent
        const token = localStorage.getItem('JWT')
        console.log('token', token);
        token && (config.headers.Authorization = 'Bearer ' + token);
        return config;
    },
    function(error) {
        // Do something with request error
        return Promise.reject(error);
    }
);

// Add a response interceptor
_axios.interceptors.response.use(
    function(response) {

        // Do something with response data
        console.log(response);
        return response;
    },
    function(error) {
        console.log(error.response.status);
        if (error.response.status) {
            switch (error.response.status) {
                // 401: 未登录
                // 未登录则跳转登录页面，并携带当前页面的路径
                // 在登录成功后返回当前页面，这一步需要在登录页操作。
                case 401:
                    Message({
                        message: '请重新登录',
                        duration: 1000,
                        forbidClick: true
                    });
                    // localStorage.removeItem('Cartoken');
                    router.replace({
                        path: '/',
                    });

                    break;

                    // 403 token过期
                    // 登录过期对用户进行提示
                    // 清除本地token和清空vuex中token对象
                    // 跳转登录页面
                case 403:
                    Message({
                        message: '登录过期，请重新登录',
                        duration: 1000,
                        forbidClick: true
                    });
                    // 清除token
                    // localStorage.removeItem('Cartoken');
                    // 跳转登录页面，并将要浏览的页面fullPath传过去，登录成功后跳转需要访问的页面
                    setTimeout(() => {
                        router.replace({
                            path: '/',
                        });
                    }, 1000);
                    break;

                    // 404请求不存在
                case 404:
                    Message({
                        message: '网络请求不存在',
                        duration: 1500,
                        forbidClick: true
                    });
                    break;
                    // 其他错误，直接抛出错误提示
                default:
                    // Message({
                    //     message: error.response.data.message,
                    //     duration: 1500,
                    //     forbidClick: true
                    // });
            }
            return Promise.reject(error.response);
        }
        // console.log(error.indexOf('401'));

        // Do something with response error
        return Promise.reject(error);
    }
);

Plugin.install = function(Vue, options) {
    Vue.axios = _axios;
    window.axios = _axios;
    Object.defineProperties(Vue.prototype, {
        axios: {
            get() {
                return _axios;
            }
        },
        $axios: {
            get() {
                return _axios;
            }
        },
    });
};

Vue.use(Plugin)

export default Plugin;
