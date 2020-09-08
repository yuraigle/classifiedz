<template>
  <div>
    <h3 class="title is-4">USERS</h3>

    <ul>
      <li v-for="u in users" :key="u.id">
        <nuxt-link :to="`/users/${u.id}/`">
          {{ u.name }}
        </nuxt-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  fetch() {
    this.$axios.$get(`/api/users`).then((data) => {
      this.users = data._embedded.users
    })
  },

  data: () => ({
    users: [],
  }),
}
</script>
