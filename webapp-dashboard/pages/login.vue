<template>
  <section class="hero is-dark is-fullheight">
    <div class="hero-body">
      <div class="container">
        <div class="columns is-centered">
          <div class="column is-5-tablet is-4-desktop is-3-widescreen">
            <form class="box" @submit.prevent="handleLogin">
              <b-field
                label="Email"
                label-position="on-border"
                :type="fieldType('email')"
                :message="fieldMsg('email')"
              >
                <b-input
                  v-model="email"
                  type="email"
                  icon="envelope"
                  @input="$v.$touch()"
                ></b-input>
              </b-field>
              <b-field
                label="Password"
                label-position="on-border"
                :type="fieldType('password')"
                :message="fieldMsg('password')"
              >
                <b-input
                  v-model="password"
                  type="password"
                  icon="lock"
                  @input="$v.$touch()"
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
import { required, email, minLength, maxLength } from 'vuelidate/lib/validators'

export default {
  data: () => ({
    email: '',
    password: '',
  }),

  validations: {
    email: { required, email },
    password: { required, minLength: minLength(6), maxLength: maxLength(20) },
  },

  methods: {
    handleLogin() {
      this.$v.$touch()
      if (this.$v.$invalid) {
        return
      }

      const data = { email: this.email, password: this.password }
      this.$auth
        .loginWith('local', { data })
        .then(() => this.showInfo('Hello!'))
        .catch((err) => {
          const { status, data } = err.response
          if (status === 400 && Array.isArray(data)) {
            data.forEach(({ message }) => this.showError(message))
          }
        })
    },
  },
}
</script>
