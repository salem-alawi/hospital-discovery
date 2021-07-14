const state = {
    sections: [],
};
const getters = {
    allSections: (state) => state.sections,
};

const actions = {
    loadAllSections({commit}, data) {
        commit('loadAllSectionMutation', data);
    },
    updateOneSection
        ({commit}, data) {
        commit('updateOneSectionMutation', data);
    },
    insertOneSection({commit}, data) {

        commit('insertOneSectionMutation', data);
    },
    deleteOneSection({commit}, id) {
        commit('deleteOneSectionMutation', id);
    }
};


const mutations = {
    loadAllSectionMutation(store, data) {
        store.sections = data;
    }
    ,
    updateOneSectionMutation (state, data) {
        state.sections.splice(data.index, 1, data.item);
    },
    deleteOneSectionMutation(state, id) {

        const index = state.sections.findIndex(section => section.id === id);
        state.sections.splice(index, 1,);
    },
    insertOneSectionMutation(state, data) {

        state.sections.splice(0, 0, data);
    }

};


export const sectionStore = {
    state,
    getters,
    actions,
    mutations
};
