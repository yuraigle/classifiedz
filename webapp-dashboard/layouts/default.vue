<template>
  <div>
    <b-navbar v-if="this.$auth.loggedIn" type="is-dark">
      <template slot="brand">
        <div class="navbar-item">CLASSIFIEDZ</div>
      </template>
      <template slot="start">
        <b-navbar-item :to="{ path: '/' }"> Home </b-navbar-item>
      </template>

      <template slot="end">
        <div class="navbar-item">{{ this.$auth.user.email }}</div>
        <div class="navbar-item">
          <div class="buttons">
            <a class="button is-dark" @click="handleLogout">Log out</a>
          </div>
        </div>
      </template>
    </b-navbar>

    <div>
      <nuxt />
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    async handleLogout() {
      await this.$auth.logout()
      this.$buefy.toast.open({
        message: 'Bye!',
        type: 'is-warning',
        position: 'is-bottom-right',
      })
      this.$router.push('/login')
    },
  },
}
</script>
