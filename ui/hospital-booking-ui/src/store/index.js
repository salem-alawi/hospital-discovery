import Vue from 'vue';
import Vuex from 'vuex';

// import { auth } from './auth.module';

import { hospitalStore} from "./hospital.module";
import {sectionStore} from "./sections.module";


Vue.use(Vuex);


export default  new Vuex.Store({

    modules: {
        hospitalStore,
        sectionStore
    },
    state: {
        baseUrl: 'http://127.0.0.1:8081',
    },
    getters: {}
});
