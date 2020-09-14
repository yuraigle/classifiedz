import { NotificationProgrammatic as Notification } from 'buefy'

export const actions = {
  handleApiError(ctx, { response: { status, data } }) {
    if (status >= 400 && data && Array.isArray(data)) {
      data.forEach((err1) => {
        Notification.open({
          message: err1.message,
          type: 'is-danger',
          position: 'is-bottom-right',
          queue: false,
          duration: 5000,
          closable: false,
        })
      })
    }
  },
}
