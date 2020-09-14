import Vue from 'vue'

Vue.mixin({
  methods: {
    showMessage(message, type) {
      this.$buefy.notification.open({
        message,
        type,
        position: 'is-bottom-right',
        queue: false,
        duration: 5000,
        closable: false,
      })
    },

    showInfo(msg) {
      this.showMessage(msg, 'is-info')
    },

    showWarn(msg) {
      this.showMessage(msg, 'is-warning')
    },

    showError(err) {
      this.showMessage(err, 'is-danger')
    },
  },
})
