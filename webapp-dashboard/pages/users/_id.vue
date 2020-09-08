<template>
  <div>
    <nuxt-link to="/users/">
      <b-icon icon="chevron-left"></b-icon>
      Back
    </nuxt-link>

    <div v-if="user">
      <h3 class="title is-4">USER #{{ id }}</h3>
      <p>{{ user.name }}</p>
      <p>
        <a :href="`mailto:${user.email}`">{{ user.email }}</a>
      </p>
      <p>Registered {{ $dayjs(user.createdAt).format('YYYY-MM-DD') }}</p>
    </div>
    <div v-else>
      <h3 class="title is-4">404</h3>
    </div>
  </div>
</template>

<script>
export default {
  fetch() {
    this.$axios.$get(`/api/users/${this.id}`).then((data) => {
      this.user = data
    })
  },

  data() {
    return {
      id: this.$route.params.id,
      user: null,
    }
  },

  methods: {},
}
</script>
