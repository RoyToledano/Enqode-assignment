<template>
  <v-app>
    <v-main>
      <zero-trust-score-widget />
    </v-main>
  </v-app>
</template>

<script>
import ZeroTrustScoreWidget from './components/ZeroTrustScoreWidget.vue';

export default {
  name: 'App',

  components: {
    ZeroTrustScoreWidget,
  },

  data: () => ({
    //
  }),
}
</script>
