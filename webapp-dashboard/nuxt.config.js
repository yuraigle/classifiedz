export default {
  /*
   ** Nuxt rendering mode
   ** See https://nuxtjs.org/api/configuration-mode
   */
  mode: 'spa',
  /*
   ** Nuxt target
   ** See https://nuxtjs.org/api/configuration-target
   */
  target: 'static',
  /*
   ** Headers of the page
   ** See https://nuxtjs.org/api/configuration-head
   */
  head: {
    title: 'Dashboard',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      {
        hid: 'description',
        name: 'description',
        content: process.env.npm_package_description || '',
      },
    ],
    link: [
      { rel: 'icon', type: 'image/x-icon', href: '/dashboard/favicon.ico' },
      {
        rel: 'stylesheet',
        href: '/dashboard/fontawesome/css/fontawesome.min.css',
      },
      { rel: 'stylesheet', href: '/dashboard/fontawesome/css/solid.min.css' },
    ],
  },
  /*
   ** Global CSS
   */
  css: ['@/assets/theme.scss'],
  /*
   ** Plugins to load before mounting the App
   ** https://nuxtjs.org/guide/plugins
   */
  plugins: [{ src: '~/plugins/vuelidate' }, { src: '~/plugins/messages' }],
  /*
   ** Auto import components
   ** See https://nuxtjs.org/api/configuration-components
   */
  components: true,
  /*
   ** Nuxt.js dev-modules
   */
  buildModules: [
    // Doc: https://github.com/nuxt-community/eslint-module
    '@nuxtjs/eslint-module',
  ],
  /*
   ** Nuxt.js modules
   */
  modules: [
    // Doc: https://axios.nuxtjs.org/usage
    '@nuxtjs/axios',
    '@nuxtjs/auth',
    '@nuxtjs/dayjs',
    'nuxt-buefy',
  ],

  router: {
    base: '/dashboard/',
    middleware: ['auth'],
    trailingSlash: true,
    linkActiveClass: 'is-active',
  },

  axios: {
    baseURL: 'http://localhost:8081',
  },

  auth: {
    localStorage: false,
    cookie: {
      options: {
        expires: 7,
      },
    },
    strategies: {
      local: {
        token: { property: 'token' },
        autoFetchUser: true,
        endpoints: {
          login: { url: '/api/auth/login', method: 'post' },
          user: { url: '/api/auth/me', method: 'get', propertyName: 'user' },
          logout: false,
        },
      },
    },
    redirect: {
      login: '/login/',
      logout: '/',
      callback: '/login/',
      home: '/',
    },
  },

  buefy: {
    css: false,
    materialDesignIcons: false,
    defaultIconPack: 'fas',
  },

  /*
   ** Build configuration
   ** See https://nuxtjs.org/api/configuration-build/
   */
  build: {
    // analyze: true,
    extractCSS: true,
  },

  generate: {
    dir: '../src/main/resources/dashboard-dist',
  },
}
