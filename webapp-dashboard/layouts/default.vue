<template>
  <div class="container-fluid">
    <div class="columns">
      <div class="column is-2">
        <aside class="menu has-background-light mt-4 mb-4 ml-4">
          <p class="menu-label">Administration</p>
          <ul class="menu-list">
            <li>
              <nuxt-link :to="{ path: '/' }" exact>
                <b-icon icon="tachometer-alt"></b-icon>
                Dashboard
              </nuxt-link>
            </li>
            <li>
              <nuxt-link :to="{ path: '/users/' }">
                <b-icon icon="users"></b-icon>
                Users
              </nuxt-link>
            </li>
          </ul>
        </aside>
      </div>

      <div class="column is-10">
        <b-navbar type="is-white">
          <template slot="end">
            <div class="navbar-item">
              {{ this.$auth.user.email }}
            </div>
            <div class="navbar-item">
              <div class="buttons">
                <a class="button is-light" @click="handleLogout">
                  <b-icon icon="sign-out-alt"></b-icon>
                </a>
              </div>
            </div>
          </template>
        </b-navbar>

        <div class="content pt-4 pb-4 pl-4">
          <nuxt />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  computed: {
    ...mapState({
      warnings: (state) => state.warnings.list,
    }),
  },

  methods: {
    async handleLogout() {
      await this.$auth.logout()
      this.$router.push('/login/')
      this.showWarn('Bye!')
    },
  },
}
</script>

<style scoped>
.content {
  background-color: #fff;
  min-height: 700px;
}
</style>
