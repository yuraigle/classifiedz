import Vue from 'vue'

Vue.mixin({
  methods: {
    showMessage(message, type) {
      this.$buefy.notification.open({
        message,
        type,
        position: 'is-bottom',
        'auto-close': true,
        closable: false,
        queue: false,
        duration: 5000,
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
