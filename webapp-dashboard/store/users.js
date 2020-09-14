export const state = () => ({
  list: [],
  loading: false,
})

export const mutations = {
  setList(state, p) {
    state.list = p
  },

  setLoading(state, p) {
    state.loading = p
  },
}

export const actions = {
  fetchUsers({ commit, dispatch }) {
    commit('setList', [])
    commit('setLoading', true)

    this.$axios
      .$get('/api/users')
      .then((data) => commit('setList', data.content))
      .catch((err) => dispatch('warnings/handleApiError', err, { root: true }))
      .finally(() => commit('setLoading', false))
  },
}
