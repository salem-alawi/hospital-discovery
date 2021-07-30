const state = {
    hospitals: [],
};
const getters = {
    allHospitals: (state) => state.hospitals,
};

const actions = {
    loadAllHospitals({commit}, data) {

        data = data.filter(data => data.isActive == true);
        commit('loadAllHospitalMutation', data);
    },
    updateOneHospital
        ({commit}, data) {
        commit('updateOneHospitalMutation', data);
    },
    insertOneHospital({commit}, data) {

        commit('insertOneHospitalMutation', data);
    },
    deleteOneHospital({commit}, id) {
        commit('deleteOneHospitalMutation', id);
    }
};


const mutations = {
    loadAllHospitalMutation(store, data) {
        store.hospitals = data;
    }
    ,
    updateOneHospitalMutation(state, data) {
        state.hospitals.splice(data.index, 1, data.item);
    },
    deleteOneHospitalMutation(state, id) {

        const index = state.hospitals.findIndex(hospital => hospital.id === id);
        state.hospitals.splice(index, 1,);
    },
    insertOneHospitalMutation(state, data) {

        state.hospitals.splice(0, 0, data);
    }

};


export const hospitalStore = {
    state,
    getters,
    actions,
    mutations
};
