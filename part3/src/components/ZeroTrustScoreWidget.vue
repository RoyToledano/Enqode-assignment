<template>
  <v-container>
    <!-- Loop through each company to display the widget -->
    <v-card
      v-for="(company, index) in companies"
      :key="index"
      class="pa-4 mb-5"
      elevation="4"
      style="border-radius: 16px;"
    >
      <v-row justify="center">
        <v-col cols="12" sm="6" class="text-center">
          <v-card-title class="font-weight-bold mb-2" style="font-size: 1.5rem;">
            {{ company.companyName }}
          </v-card-title>
          <v-card-subtitle class="mb-4 text-subtitle-1">Overall Zero-Trust Score</v-card-subtitle>
          <v-progress-circular
            :value="company.ZeroTrustScore"
            :size="120"
            :width="15"
            color="primary"
            rotate="270"
            class="mx-auto"
          >
            <span style="font-size: 1.25rem;">{{ company.ZeroTrustScore }}</span>
          </v-progress-circular>
        </v-col>
      </v-row>

      <!-- Metrics Section -->
      <v-card class="pa-4 mt-4" style="background-color: #f7f9fc; border-radius: 12px;">
        <v-card-title class="text-h6 font-weight-bold">Metrics</v-card-title>
        <v-divider class="mb-4"></v-divider>
        <v-row>
          <v-col cols="12" v-for="(metric, index) in getMetrics(company)" :key="index">
            <v-row justify="space-between" class="align-center mb-3">
              <span>{{ metric.label }}: <strong>{{ metric.value.toFixed(0) }}%</strong></span>
              <v-progress-linear
                :value="metric.value"
                color="blue lighten-1"
                striped
                height="15"
                style="width: 60%; border-radius: 8px;"
              ></v-progress-linear>
            </v-row>
          </v-col>
        </v-row>
      </v-card>

      <!-- Risk Category -->
      <v-card class="pa-4 mt-4">
        <v-card-title class="text-h6 font-weight-bold">Risk Category</v-card-title>
        <v-alert
          :type="getRiskCategoryColor(company.riskCategory)"
          border="left"
          prominent
          class="ma-2"
          dense
          outlined
          style="font-size: 1.15rem;"
        >
          {{ company.riskCategory }}
        </v-alert>
      </v-card>
    </v-card>
  </v-container>
</template>

<script>
export default {
  data() {
  }
};
</script>

<style scoped>
//implement
</style>
