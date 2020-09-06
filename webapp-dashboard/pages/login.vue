<template>
  <section class="hero is-dark is-fullheight">
    <div class="hero-body">
      <div class="container">
        <div class="columns is-centered">
          <div class="column is-5-tablet is-4-desktop is-3-widescreen">
            <form class="box" @submit.prevent="handleLogin">
              <b-field label="Email">
                <b-input v-model="email" type="email" icon="envelope"></b-input>
              </b-field>
              <b-field label="Password">
                <b-input
                  v-model="password"
                  type="password"
                  icon="lock"
                ></b-input>
              </b-field>
              <b-field>
                <b-button expanded type="is-info" @click="handleLogin">
                  Login
                </b-button>
              </b-field>
            </form>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  data: () => ({
    email: '',
    password: '',
  }),

  methods: {
    handleLogin() {
      const data = { email: this.email, password: this.password }
      try {
        this.$auth
          .loginWith('local', { data })
          .then(() => {
            this.$buefy.toast.open({
              message: 'Hello!',
              type: 'is-warning',
              position: 'is-bottom-right',
            })
          })
          .catch((err) => {
            console.log(err)
          })
      } catch (err) {
        console.log(err)
        this.$buefy.toast.open({ message: err, type: 'is-danger' })
        // TODO чет сообщения не ловятся
      }
    },
  },
}
</script>
