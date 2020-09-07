import Vue from 'vue'
import Vuelidate from 'vuelidate'
Vue.use(Vuelidate)

Vue.mixin({
  methods: {
    fieldType($name) {
      return { 'is-danger': this.$v[$name] && this.$v[$name].$error }
    },

    fieldMsg($name) {
      const v = this.$v[$name]

      if (!v || !v.$dirty) {
        return []
      }
      if (v.required === false) {
        return ['Required']
      }
      if (v.email === false) {
        return ['Invalid email address']
      }
      if (v.minLength === false) {
        return [`No shorter than ${v.$params.minLength.min}`]
      }
      if (v.maxLength === false) {
        return [`No longer than ${v.$params.maxLength.max}`]
      }
      if (v.rx === false) {
        return [v.$params.rx.type]
      }

      return []
    },
  },
})
