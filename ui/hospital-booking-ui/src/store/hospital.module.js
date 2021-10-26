const state = {
    hospitals: [],
};
const getters = {
    allHospitals: (state) => state.hospitals,
};

const actions = {
    loadAllHospitals({commit}, data) {

        // data = data.filter(data => data.isActive == true);
        commit('loadAllHospitalMutation', data);
    }
};


const mutations = {
    loadAllHospitalMutation(store, data) {
        store.hospitals = data;
    }
};


export const hospitalStore = {
    state,
    getters,
    actions,
    mutations
};
