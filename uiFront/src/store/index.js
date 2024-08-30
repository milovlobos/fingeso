import { createStore } from 'vuex';
import createPersistedState from 'vuex-persistedstate'
import axios from 'axios';

export default createStore({
  state: {
    isLog: false,
    userLogged:null,
  },
  getters: {
    getIsLog(state){
      return state.isLog;
    },
    getUserLogged(state){
      return state.userLogged;
    },
  },
  mutations: {
    setIsLog(state, value) {
      state.isLog = value;
    },
    setUserLogged(state, value) {
      state.userLogged = value;
    },
  },
  actions: {
    setLogStatus({ commit }, value) {
      commit('setIsLog', value);
    },
    async fetchUser({ commit }, value) {
      try {
        const response = await axios.get(import.meta.env.VITE_BASE_URL + "api/usuario/getusuario",{ params: { "email": value } });
        commit('setUserLogged', response.data); // Guardar el objeto en el estado
        console.log(response.data.email);
      } catch (error) {
        console.log("Error en conseguir el usuario",error);
      }
    },
  },
  plugins: [createPersistedState({
    storage: window.sessionStorage,
  })],
});