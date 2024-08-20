import { createStore } from 'vuex';

const store = createStore({
  state: {
    isLog: true
  },
  getters: {
    isLog: (state) => state.isLog
  },
  mutations: {
    setIsLog(state, value) {
      state.isLog = value;
    }
  },
  actions: {
    toggleIsLog({ commit, state }) {
      commit('setIsLog', !state.isLog);
    },
    setLogStatus({ commit }, value) {
      commit('setIsLog', value);
    }
  }
});

export default store;