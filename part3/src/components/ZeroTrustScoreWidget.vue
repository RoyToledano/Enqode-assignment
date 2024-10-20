<template>
  <v-container>
    <!-- Loop through each company to display the widget -->
    <v-card
      v-for="(company, index) in companies"
      :key="index"
      class="pa-2 mb-3"
      elevation="2"
      style="border-radius: 12px; max-width: 500px;"
    >
      <v-row justify="center">
        <v-col cols="12" sm="6" class="text-center">
          <v-card-title class="font-weight-bold mb-1" style="font-size: 1.2rem; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
            {{ company.companyName }}
          </v-card-title>
          <v-card-subtitle class="mb-2 text-subtitle-2">Overall Zero-Trust Score</v-card-subtitle>
          <v-progress-circular
            v-model="company.ZeroTrustScore"
            :size="80"
            :width="10"
            color="primary"
            rotate="270"
            class="mx-auto"
          >
            <span style="font-size: 1rem;">{{ company.ZeroTrustScore }}</span>
          </v-progress-circular>
        </v-col>
      </v-row>

      <!-- Metrics Section -->
      <v-card class="pa-2 mt-2" style="background-color: #f7f9fc; border-radius: 8px;">
        <v-card-title class="text-h6 font-weight-bold style=font-size: 1rem;">Metrics</v-card-title>
        <v-divider class="mb-2"></v-divider>
        <v-row>
          <v-col cols="12" v-for="(metric, index) in getMetrics(company)" :key="index">
            <v-row justify="space-between" class="align-center mb-2">
              <span style="display: block; margin-bottom: 4px; margin-top: 4px; margin-left: 10px;">
                {{ "- " + metric.label }}: <strong>{{ metric.value.toFixed(0) }}%</strong>
              </span>
              <v-progress-linear
                v-model="metric.value"
                color="blue lighten-1"
                striped
                height="10"
                class="ml-2"
                rounded 
                style="width: 90%;"
              ></v-progress-linear>
            </v-row>
          </v-col>
        </v-row>
      </v-card>

      <!-- Risk Category -->
      <v-card class="pa-2 mt-2">
        <v-card-title class="text-h6 font-weight-bold" style="font-size: 1rem;">Risk Category</v-card-title>
        <v-alert
          :type="getRiskCategoryColor(company.riskCategory)"
          border="left"
          prominent
          class="ma-1"
          dense
          outlined
          style="font-size: 0.9rem;"
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
    return {
      companies: null,
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      try {
        const response = await fetch('/data/zero-trust-data.json');
        if (!response.ok) throw new Error('Failed to load JSON data');
        const jsonData = await response.json();
        this.companies = jsonData.companies;
      } catch (error) {
        console.error('Error loading data:', error);
      }
    },
    getMetrics(company) {
      return [
        {
          label: "Shannon Entropy Score",
          value: (company.metrics.ShannonEntropyScore / 10) * 100,
        },
        {
          label: "Certificate Bit Strength",
          value: (company.metrics.certificateBitStrength / 4096) * 100,
        },
        {
          label: "Open Ports Detected",
          value: (1 - company.metrics.openPortsDetected / 100) * 100,
        },
        {
          label: "Firewall Detected",
          value: company.metrics.firewallDetected ? 100 : 0,
        },
        {
          label: "DNSsec Enabled",
          value: company.metrics.DNSsecEnabled ? 100 : 0,
        },
        {
          label: "TLS Version",
          value: company.metrics.tlsVersion === "1.3" ? 100 : 50,
        },
      ];
    },
    getRiskCategoryColor(riskCategory) {
      switch (riskCategory) {
        case "Low Risk":
          return "success";
        case "Moderate Risk":
          return "warning";
        case "High Risk":
          return "error";
        default:
          return "info";
      }
    }
  },
};
</script>

<style scoped>
.v-container {
  padding-top: 10px;
}

.v-card {
  background-color: white;
}

.v-progress-linear {
  transition: width 0.6s ease-in-out;
}

.v-card-title {
  font-weight: bold;
}
</style>
