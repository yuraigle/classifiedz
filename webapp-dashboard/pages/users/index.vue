<template>
  <div>
    <h3 class="title is-4">USERS</h3>

    <div>
      <ul v-if="!loading">
        <li v-for="u in users" :key="u.id">
          <nuxt-link :to="`/users/${u.id}/`">
            {{ u.id }}.
            {{ u.name }}
          </nuxt-link>
        </li>
      </ul>
      <div v-else class="has-text-centered has-text-grey-light">
        <b-icon icon="sync-alt" custom-class="fa-spin" size="is-large"></b-icon>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  fetch() {
    this.$store.dispatch('users/fetchUsers')
  },

  computed: {
    ...mapState({
      users: (state) => state.users.list,
      loading: (state) => state.users.loading,
    }),
  },
}
</script>
