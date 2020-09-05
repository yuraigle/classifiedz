<template>
  <section class="hero is-light is-fullheight">
    <div class="hero-body">
      <div class="container">
        <div class="columns is-centered">
          <div class="column is-5-tablet is-4-desktop is-3-widescreen">
            <form action class="box">
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
                <b-button expanded type="is-primary">Login</b-button>
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
}
</script>
